#!/system/bin/sh
MODDIR="$(dirname "$(realpath "$0")")"
if [ ! -f "$MODDIR/settings/NOONEUPLUSONBOOT" ]; then
  sh "$MODDIR/one-uplus.sh" lss
fi
