#!/system/bin/sh
MODDIR="${0%/*}"
if [ ! -f "$MODDIR/settings/NOONEUPLUSONBOOT" ]; then
  sh "$MODDIR/scripts/one-uplus.sh" lss
fi
