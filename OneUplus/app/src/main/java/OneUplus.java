import android.annotation.SuppressLint;
import android.os.Build;
import android.os.PersistableBundle;
import com.android.internal.util.FastXmlSerializer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


public class OneUplus {
    public static final String KEY_CARRIER_CONFIG_VERSION_STRING = "carrier_config_version_string";
    public static final String KEY_CARRIER_NAME_OVERRIDE_BOOL = "carrier_name_override_bool";
    public static final String KEY_CARRIER_NAME_STRING = "carrier_name_string";
    public static final String KEY_CARRIER_VOLTE_AVAILABLE_BOOL = "carrier_volte_available_bool";
    public static final String KEY_EDITABLE_ENHANCED_4G_LTE_BOOL = "editable_enhanced_4g_lte_bool";
    public static final String KEY_CARRIER_SETTINGS_VERSION_STRING = "_carrier_settings_version_string";
    public static final String KEY_VERSION = "__carrier_config_package_version__";
    //                MCCMNC   ICCID              Carrier ID (Android 9+)
    //  SK Telecom    45005    898205[0-9]{13}    1891
    //          KT    45008    898230[0-9]{13}    1890
    //       LG U+    45006    898206[0-9]{13}    1892
    private static final String REGEX1 = "^carrierconfig-(com\\.android\\.carrierconfig|com\\.google\\.android\\.carrier)-898205[0-9]{13}(-1891)?\\.xml$";
    private static final String REGEX2 = "^carrierconfig-(com\\.android\\.carrierconfig|com\\.google\\.android\\.carrier)-898230[0-9]{13}(-1890)?\\.xml$";
    private static final String REGEX3 = "^carrierconfig-(com\\.android\\.carrierconfig|com\\.google\\.android\\.carrier)-898206[0-9]{13}(-1892)?\\.xml$";
    private static final String SEP = "------------------------------------";
    public static final String TAG_BUNDLE = "bundle_data";
    public static final String TAG_DOCUMENT = "carrier_config";
    public static final String TAG_VERSION = "package_version";
    private static final String USERDE_FILES_DIR = "/data/user_de/0/com.android.phone/files";
    private static final String USER_FILES_DIR = "/data/data/com.android.phone/files";
    private static String packageVersion;
    private static boolean bAddVolte = false;


    @SuppressLint({"NewApi"})
    public static void main(String[] args) {
        parseCmdLineArgs(args);
        System.out.println("DEVICE: " + Build.MANUFACTURER + " " + Build.MODEL + " (" + Build.DEVICE + ")");
        System.out.println("BUILD#: " + Build.DISPLAY + " (" + Build.VERSION.INCREMENTAL + ")");
        System.out.println("API: " + Build.VERSION.SDK_INT);

        String filesDir;
        if (Build.VERSION.SDK_INT >= 24) {
            filesDir = USERDE_FILES_DIR;
        } else if (Build.VERSION.SDK_INT >= 21) {
            filesDir = USER_FILES_DIR;
        } else {
            return;
        }

        System.out.println(SEP);
        String cmd = "ls -aliZ " + filesDir;
        System.out.println("DIR: " + filesDir);
        try {
            exec(cmd);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(SEP);
        System.out.println("REGEX1: " + REGEX1);
        System.out.println("REGEX2: " + REGEX2);
        System.out.println("REGEX3: " + REGEX3);
        File[] fileList = new File(filesDir).listFiles();
        if (fileList != null) {
            for (File file : fileList) {
                if (file.isFile()) {
                    if (file.getName().matches(REGEX1)) {
                        modifyXml(file, 1);
                    } else if (file.getName().matches(REGEX2)) {
                        modifyXml(file, 2);
                    } else if (file.getName().matches(REGEX3)) {
                        modifyXml(file, 3);
                    }
                }
            }
        }

        System.out.println(SEP);
        System.out.println("DIR: " + filesDir);
        try {
            exec(cmd);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void parseCmdLineArgs(String[] args) {
        boolean postDoubleDash = false;
        for (String arg : args) {
            char[] chars = arg.toCharArray();
            int len = chars.length;
            if (postDoubleDash || len < 2 || chars[0] != '-') {
                System.err.println("invalid argument '" + arg + "'");
                System.exit(2);
            } else if (chars[1] != '-') {
                for (int i = 1; i < len; i++) {
                    char c = chars[i];
                    if (c == 'v') {
                        bAddVolte = true;
                    } else {
                        System.err.println("invalid option -- '" + c + "'");
                        System.exit(2);
                    }
                }
            } else if (len == 2) {
                postDoubleDash = true;
            } else {
                System.err.println("unrecognized option '" + arg + "'");
                System.exit(2);
            }
        }
    }

    @SuppressLint({"NewApi"})
    private static void modifyXml(File file, int type) {
        System.out.println(SEP);
        System.out.println("FILE: " + file.getPath());
        System.out.println("TYPE: " + type);

        PersistableBundle config = restoreConfigFromXml(file);
        String configVersion = null;
        if (config.get(KEY_CARRIER_CONFIG_VERSION_STRING) instanceof String) {
            configVersion = config.getString(KEY_CARRIER_CONFIG_VERSION_STRING);
        }
        String settingsVersion = null;
        if (config.get(KEY_CARRIER_SETTINGS_VERSION_STRING) instanceof String) {
            settingsVersion = config.getString(KEY_CARRIER_SETTINGS_VERSION_STRING);
        }
        System.out.println("   PACKAGE_VERSION: " + packageVersion);
        System.out.println("    CONFIG_VERSION: " + configVersion);
        System.out.println("  SETTINGS_VERSION: " + settingsVersion);
        System.out.println("            CONFIG: " + config.toString());

        boolean isDirty = false;
        // Config: VoLTE Availability (All carriers in KR)
        if ( (type == 1 || type == 2 || type == 3) && bAddVolte ) {
            if (!(config.get(KEY_CARRIER_VOLTE_AVAILABLE_BOOL) instanceof Boolean)
                    || true != config.getBoolean(KEY_CARRIER_VOLTE_AVAILABLE_BOOL)) {
                config.putBoolean(KEY_CARRIER_VOLTE_AVAILABLE_BOOL, true);
                isDirty = true;
            }
        }
        // Config: VoLTE Availability (LG U+ only)
        if ( type == 3 && bAddVolte ) {
            if (!(config.get(KEY_EDITABLE_ENHANCED_4G_LTE_BOOL) instanceof Boolean)
                    || true != config.getBoolean(KEY_EDITABLE_ENHANCED_4G_LTE_BOOL)) {
                config.putBoolean(KEY_EDITABLE_ENHANCED_4G_LTE_BOOL, true);
                isDirty = true;
            }
        }
        // Config: Carrier Name Override (LG U+ only)
        if ( type == 3 ) {
            if (!(config.get(KEY_CARRIER_NAME_OVERRIDE_BOOL) instanceof Boolean)
                    || true != config.getBoolean(KEY_CARRIER_NAME_OVERRIDE_BOOL)) {
                config.putBoolean(KEY_CARRIER_NAME_OVERRIDE_BOOL, true);
                isDirty = true;
            }
            if (!(config.get(KEY_CARRIER_NAME_STRING) instanceof String)
                    || !"LG U+".equals(config.getString(KEY_CARRIER_NAME_STRING))) {
                config.putString(KEY_CARRIER_NAME_STRING, "LG U+");
                isDirty = true;
            }
        }

        if (isDirty) {
            saveConfigToXml(config, file);
            System.out.println("------> CHANGED!!!!");
            System.out.println("        NEW CONFIG: " + config.toString());
        } else {
            System.out.println("------> NOT CHANGED");
        }
    }

    @SuppressLint({"NewApi"})
    private static PersistableBundle restoreConfigFromXml(File file) {
        packageVersion = null;
        PersistableBundle config = null;
        try (FileInputStream inFile = new FileInputStream(file)) {
            if (Build.VERSION.SDK_INT < 30) {
                XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
                parser.setInput(inFile, "utf-8");
                while (true) {
                    int event = parser.next();
                    if (event == 1) {
                        break;
                    }
                    if (event == 2 && TAG_VERSION.equals(parser.getName())) {
                        packageVersion = parser.nextText();
                    }
                    if (event == 2 && TAG_BUNDLE.equals(parser.getName())) {
                        config = PersistableBundle.restoreFromXml(parser);
                    }
                }
            } else {
                config = PersistableBundle.readFromStream(inFile);
                if (config.get(KEY_VERSION) instanceof String) {
                    packageVersion = config.getString(KEY_VERSION);
                }
            }
        } catch (FileNotFoundException e) {
            if (file != null) {
                System.err.println("File not found: " + file.getPath());
            }
            return config;
        } catch (IOException e) {
            e.printStackTrace();
            return config;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return config;
        }
        return config;
    }

    @SuppressLint({"NewApi"})
    private static void saveConfigToXml(PersistableBundle config, File file) {
        if (config == null || config.isEmpty())  return;

        try (FileOutputStream outFile = new FileOutputStream(file)) {
            if (Build.VERSION.SDK_INT >= 30) {
                config.writeToStream(outFile);
                outFile.flush();
            } else {
                FastXmlSerializer out = new FastXmlSerializer();
                out.setOutput(outFile, "utf-8");
                out.startDocument("utf-8", true);
                out.startTag((String) null, TAG_DOCUMENT);
                out.startTag((String) null, TAG_VERSION);
                out.text(packageVersion);
                out.endTag((String) null, TAG_VERSION);
                out.startTag((String) null, TAG_BUNDLE);
                config.saveToXml(out);
                out.endTag((String) null, TAG_BUNDLE);
                out.endTag((String) null, TAG_DOCUMENT);
                out.endDocument();
                out.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

    public static void exec(String command) throws IOException {
        // TODO: Modify String argument to String[] - Use ProcessBuilder
        Process p = Runtime.getRuntime().exec(command);
        // TODO: Forwarding stdout and stderr with blocking
        // See: https://stackoverflow.com/questions/14165517/processbuilder-forwarding-stdout-and-stderr-of-started-processes-without-blocki
        BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line = "";
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}