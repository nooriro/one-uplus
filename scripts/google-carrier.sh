#!/system/bin/sh
MODDIR="$(dirname "$(dirname "$(realpath "$0")")")"
LOGDIR="$MODDIR/logs"
[ -f "$LOGDIR" ] && rm "$LOGDIR"
[ -d "$LOGDIR" ] || mkdir -p "$LOGDIR"
DEX="$MODDIR/scripts/classes.dex"
API="$(getprop ro.build.version.sdk)"

DATETIME="$(date "+%y%m%d-%H%M%S-%3N")"
# Nougat's `date` command does not support nanoseconds format `%N`. Bypass it.
if [ ${#DATETIME} -ne 17 ]; then
  TIME="$EPOCHREALTIME"
  if [ -n "$TIME" ]; then
    # See: Split string by delimiter and get N-th element
    # https://unix.stackexchange.com/questions/312280/split-string-by-delimiter-and-get-n-th-element/312284#312284
    TV_SEC="${TIME%%.*}"   # integral part of $TIME    (seconds)
    TV_USEC="${TIME#*.}"   # fractional part of $TIME  (microseconds)
    # Bypass Nougat's `date` command bug: `-d @UNIXTIME` option prints GMT time instead of local time
    # See: Accessing last x characters of a string in Bash
    # https://stackoverflow.com/questions/19858600/accessing-last-x-characters-of-a-string-in-bash/46292380
    if [ "${TV_SEC: -1}" = "${DATETIME:12:1}" ]; then
      DATETIME="${DATETIME:0:13}-${TV_USEC:0:3}"
    else
      DATETIME="$(date "+%y%m%d-%H%M%S")-${TV_USEC:0:3}"
    fi
  else
    DATETIME="${DATETIME:0:13}-000"
  fi
fi

LOGFILE="$DATETIME-GC"
# See: How to check if a variable is set in Bash?
# https://stackoverflow.com/questions/3601515/how-to-check-if-a-variable-is-set-in-bash
#   function a {
#     # if $1 is set ?
#     if [ -z ${1+x} ]; then echo "\$1 is unset"; else echo "\$1 is set to '$1'"; fi
#   }
[ -z ${1+x} ] || LOGFILE="$LOGFILE-$1"  # DO NOT USE -n to flip the condition
[ "$(dirname "$MODDIR")" = /data/adb/modules_update ] && LOGFILE="$LOGFILE-update"
LOGFILE="$LOGFILE.txt"
LOG="$LOGDIR/$LOGFILE"

rm -r "$MODDIR/system"

if [ -d /product/etc/CarrierSettings ]; then
  OUTDIR="$MODDIR/system/product/etc/CarrierSettings"
  mkdir -p "$OUTDIR"
  # find "$MODDIR/system" -type d -exec chmod 0755 {} \;

  [ -f "$MODDIR/settings/DONTADDVOLTETOPB" ] || OPTION="-v"

  if [ "$API" -ge 26 ]; then
    /system/bin/app_process -cp "$DEX" "$MODDIR" OneUplusGoogleCarrier $OPTION "$OUTDIR" > "$LOG" 2>&1
  else
    CLASSPATH="$DEX" /system/bin/app_process "$MODDIR" OneUplusGoogleCarrier $OPTION "$OUTDIR" > "$LOG" 2>&1
  fi
fi

echo "------------------------------------" >> "$LOG"
printf "MODDIR=%s\n"          "$MODDIR"          >> "$LOG"
printf "LOGDIR=%s\n"          "$LOGDIR"          >> "$LOG"
printf "LOG=%s\n"             "$LOG"             >> "$LOG"
printf "DEX=%s\n"             "$DEX"             >> "$LOG"
printf "API=%s\n"             "$API"             >> "$LOG"
printf "PWD=%s\n"             "$PWD"             >> "$LOG"
printf "PATH=%s\n"            "$PATH"            >> "$LOG"
printf "ASH_STANDALONE=%s\n"  "$ASH_STANDALONE"  >> "$LOG"
printf "EPOCHREALTIME=%s\n"   "$EPOCHREALTIME"   >> "$LOG"

if [ -f "$MODDIR/settings/COPYLOG" ]; then
  LOGDIR2="/data/local/tmp/one-uplus-log"
  NOTICEFILE="this-folder-is-safe-to-delete"
  [ -f "$LOGDIR2" ] && rm "$LOGDIR2"
  [ -d "$LOGDIR2" ] || mkdir -p "$LOGDIR2"
  cp -p "$LOG" "$LOGDIR2"/
  : > "$LOGDIR2/$NOTICEFILE"

  chown shell:shell "$LOGDIR2" "$LOGDIR2/$LOGFILE" "$LOGDIR2/$NOTICEFILE"
  chmod 0777 "$LOGDIR2"
  chmod 0666 "$LOGDIR2/$LOGFILE" "$LOGDIR2/$NOTICEFILE"
fi
