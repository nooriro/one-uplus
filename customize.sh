MINAPI=24
if [ "$API" -lt "$MINAPI" ]; then
  ui_print "! API: [${API}] < ${MINAPI}"
  abort "! This module is only for Android Nougat(7.0, API 24) or higher"
fi
ui_print "- API: [${API}] >= ${MINAPI}"

mkdir -p "$MODPATH/logs"
if [ -d /data/adb/modules/one-uplus/log -o -d /data/adb/modules/one-uplus/logs ]; then
  ui_print "- Migrating logs"
  cp -p /data/adb/modules/one-uplus/log/* "$MODPATH/logs/"
  cp -p /data/adb/modules/one-uplus/logs/* "$MODPATH/logs/"
fi

if [ -d /data/adb/modules/one-uplus/settings ]; then
  ui_print "- Migrating settings"
  cp -p /data/adb/modules/one-uplus/settings/* "$MODPATH/settings/"
fi

ui_print "- Creating symlink"
ABILIST="$(getprop ro.product.cpu.abilist)"
cd "$MODPATH/scripts"
for ABI in ${ABILIST//,/ }; do
  [ -f "dtf_$ABI" ] && { ln -sf "dtf_$ABI" dtf; break; }
done
cd - >/dev/null

ui_print "- Setting permissions"
chmod +x "$MODPATH/scripts/dtf_"*
chmod +x "$MODPATH/switches/on" "$MODPATH/switches/off" "$MODPATH/switches/refresh"

ui_print "- Running one-uplus.sh"
sh "$MODPATH/scripts/one-uplus.sh"

ui_print "- Killing com.android.phone"
kill "$(/system/bin/toybox ps -A -u radio | grep 'com\.android\.phone' | tr -s ' ' | cut -d ' ' -f 2)"

if [ -f "$MODPATH/settings/TEST_GOOGLECARRIERATONCE" ]; then
  rm "$MODPATH/settings/TEST_GOOGLECARRIERATONCE"
  ui_print "- Running google-carrier.sh"
  sh "$MODPATH/scripts/google-carrier.sh"
  : > "$MODPATH/settings/NOGOOGLECARRIERONBOOT"
fi

if [ -f "$MODPATH/settings/TEST_ONEUPLUSATONCE" ]; then
  rm "$MODPATH/settings/TEST_ONEUPLUSATONCE"
  : > "$MODPATH/settings/NOONEUPLUSONBOOT"
fi

if [ -f "$MODPATH/settings/TEST_REVERTONFINISHINSTALL" ]; then
  rm "$MODPATH/settings/TEST_REVERTONFINISHINSTALL"
  ADDITIONAL_DELAY=5
  ui_print "- Waiting ${ADDITIONAL_DELAY} seconds"
  sleep "$ADDITIONAL_DELAY"
  
  ui_print "- Running revert.sh"
  sh "$MODPATH/scripts/revert.sh"
fi
