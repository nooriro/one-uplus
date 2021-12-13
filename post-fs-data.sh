#!/system/bin/sh
MODDIR="${0%/*}"
if [ ! -f "$MODDIR/settings/NOGOOGLECARRIERONBOOT" ]; then
  # See: Unsetting persistent system properties
  # https://stackoverflow.com/questions/16440945/unsetting-persistent-system-properties
  DIR="/data/property"
  KEY="persist.sys.timezone"

  API="$(getprop ro.build.version.sdk)"
  if [ "$API" -lt 28 ]; then
    TIMEZONE="$(cat $DIR/$KEY)"
  else
    # See: Protocol Buffer schema file (persistent_properties.proto)
    # https://cs.android.com/android/platform/superproject/+/master:system/core/init/persistent_properties.proto;bpv=1

    # See: Protocol Buffer format
    # https://developers.google.com/protocol-buffers/docs/encoding#structure
    # https://developers.google.com/protocol-buffers/docs/encoding#strings
    # https://developers.google.com/protocol-buffers/docs/encoding#embedded

    # See: Save command output on variable and check exit status
    # https://stackoverflow.com/questions/36921658/save-command-output-on-variable-and-check-exit-status

    # Use toybox grep. Do not use Magisk's built-in busybox grep. 
    if RET="$(/system/bin/grep -Fbo $KEY $DIR/persistent_properties)"; then
      OFFSET="$(echo $RET | sed 's/:persist\.sys\.timezone//')"
      #  crosshatch:/ # xxd -s $(( $OFFSET - 4 )) -l 36 $DIR/persistent_properties
      #  0000015c: 0a22 0a14 7065 7273 6973 742e 7379 732e  ."..persist.sys.
      #  0000016c: 7469 6d65 7a6f 6e65 120a 4173 6961 2f53  timezone..Asia/S
      #  0000017c: 656f 756c                                eoul
      #
      #  0a 22 --> field_number=1, wire_type=2, length=34  ( 0a = 0000 1010 = 00001 010 )
      #  0a 14 --> field_number=1, wire_type=2, length=20  ( 0a = 0000 1010 = 00001 010 )
      #  12 0a --> field_number=2, wire_type=2, length=10  ( 12 = 0001 0010 = 00010 010 )    
      LENHEX="$(xxd -s $(( $OFFSET + 21 )) -l 1 -p $DIR/persistent_properties)"
      LEN="$(( 0x$LENHEX ))"
      # TIMEZONE="$(xxd -s $(( $OFFSET + 22 )) -l $LEN -p $DIR/persistent_properties | xxd -p -r)"
      TIMEZONE="$(dd bs=1 skip=$(( $OFFSET + 22 )) count=$LEN if=$DIR/persistent_properties status=none)"
    fi
  fi
  
  [ -n "$TIMEZONE" ] && export TZ="$TIMEZONE"
  sh "$MODDIR/scripts/google-carrier.sh" pfd
fi
