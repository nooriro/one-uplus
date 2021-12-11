#!/system/bin/sh
CCDIR='/data/user_de/0/com.android.phone/files'
REGEX='^carrierconfig-(com\.android\.carrierconfig|com\.google\.android\.carrier)-898206[0-9]{13}(-1892)?\.xml$'

CMD1='s/<string name="carrier_name_string">LG U\+<\/string>//g'
CMD2='s/<boolean name="carrier_name_override_bool" value="true" \/>//g'
CMD3='/^[[:space:]]*$/d'
CMDS="$CMD1; $CMD2; $CMD3"

ls "$CCDIR" | grep -E "$REGEX" | sed "s,.*,$CCDIR/&,g" | tr \\n \\0 | xargs -0 sed -i "$CMDS"
