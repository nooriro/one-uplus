#!/system/bin/sh
THIS="$(realpath "$0")"
THISDIR="${THIS%/*}"
DEX="$THISDIR/classes.dex"
API="$(getprop ro.build.version.sdk)"

MODDIR="${THISDIR%/*}"
LOGDIR="$MODDIR/logs"
[ -f "$LOGDIR" ] && rm "$LOGDIR"
[ -d "$LOGDIR" ] || mkdir -p "$LOGDIR"

DATETIMEFULL="$("$THISDIR/dtf")"
LOGFILE="${DATETIMEFULL:2:17}-GC"
[ -z ${1+x} ] || LOGFILE="$LOGFILE-$1"  # DO NOT USE -n to flip the condition
[ "${MODDIR%/*}" = /data/adb/modules_update ] && LOGFILE="$LOGFILE-update"
LOGFILE="$LOGFILE.txt"
LOG="$LOGDIR/$LOGFILE"
echo "$DATETIMEFULL" > "$LOG"

rm -r "$MODDIR/system"

if [ -d /product/etc/CarrierSettings ]; then
  OUTDIR="$MODDIR/system/product/etc/CarrierSettings"
  mkdir -p "$OUTDIR"
  # find "$MODDIR/system" -type d -exec chmod 0755 {} \;

  [ -f "$MODDIR/settings/DONTADDVOLTETOPB" ] || OPTION="-v"

  if [ "$API" -ge 26 ]; then
    /system/bin/app_process -cp "$DEX" "$THISDIR" OneUplusGoogleCarrier $OPTION "$OUTDIR" >> "$LOG" 2>&1
  else
    CLASSPATH="$DEX" /system/bin/app_process "$THISDIR" OneUplusGoogleCarrier $OPTION "$OUTDIR" >> "$LOG" 2>&1
  fi
fi

echo "------------------------------------" >> "$LOG"
printf "0=%s\n"               "$0"               >> "$LOG"
printf "THIS=%s\n"            "$THIS"            >> "$LOG"
printf "THISDIR=%s\n"         "$THISDIR"         >> "$LOG"
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
