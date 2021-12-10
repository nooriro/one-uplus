#!/system/bin/sh
MODDIR="$(dirname "$(realpath "$0")")"
LOGDIR="$MODDIR/logs"
[ -f "$LOGDIR" ] && rm "$LOGDIR"
[ -d "$LOGDIR" ] || mkdir -p "$LOGDIR"

DATETIME="$(date "+%y%m%d-%H%M%S-%3N")"
# Nougat's `date` command does not support nanoseconds format `%N`. Bypass it.
if [ ${#DATETIME} -ne 17 ]; then
  TIME="$EPOCHREALTIME"
  if [ -n "$TIME" ]; then
    # See: Split string by delimiter and get N-th element
    # https://unix.stackexchange.com/questions/312280/split-string-by-delimiter-and-get-n-th-element/312284#312284
    S="${TIME%%.*}"   # integral part of $TIME    (seconds)
    T="${TIME#*.}"    # fractional part of $TIME  (microseconds)
    # Bypass Nougat's `date` command bug: `-d @UNIXTIME` option prints GMT time instead of local time
    # See: Accessing last x characters of a string in Bash
    # https://stackoverflow.com/questions/19858600/accessing-last-x-characters-of-a-string-in-bash/46292380
    if [ "${S: -2}" = "${DATETIME:11:2}" ]; then
      DATETIME="${DATETIME:0:13}-${T:0:3}"
    else
      DATETIME="$(date "+%y%m%d-%H%M%S")-${T:0:3}"
    fi
  else
    DATETIME="${DATETIME:0:13}-000"
  fi
fi

LOGFILE="$DATETIME-OU"
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

[ -f "$MODDIR/settings/DONTADDVOLTETOXML" ] || OPTION="-v"

DEX="$MODDIR/classes.dex"
API="$(getprop ro.build.version.sdk)"
if [ "$API" -ge 26 ]; then
  /system/bin/app_process -cp "$DEX" "$MODDIR" OneUplus $OPTION > "$LOG" 2>&1
else
  CLASSPATH="$DEX" /system/bin/app_process "$MODDIR" OneUplus $OPTION > "$LOG" 2>&1
fi

echo "------------------------------------" >> "$LOG"
echo "MODDIR=$MODDIR" >> "$LOG"
echo "LOGDIR=$LOGDIR" >> "$LOG"
echo "LOG=$LOG" >> "$LOG"
echo "API=$API" >> "$LOG"
echo "ASH_STANDALONE=$ASH_STANDALONE" >> "$LOG"
echo "PWD=$PWD" >> "$LOG"
echo "PATH=$PATH" >> "$LOG"

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
