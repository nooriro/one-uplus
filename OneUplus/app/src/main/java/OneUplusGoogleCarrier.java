import android.util.Pair;

import com.google.protobuf.InvalidProtocolBufferException;
import com.nooriro.oneuplus.proto.googlecarrier.CarrierList;
import com.nooriro.oneuplus.proto.googlecarrier.CarrierMap;
import com.nooriro.oneuplus.proto.googlecarrier.MultiCarrierSettings;
import com.nooriro.oneuplus.proto.googlecarrier.CarrierSettings;
import com.nooriro.oneuplus.proto.googlecarrier.CarrierConfig;
import com.nooriro.oneuplus.proto.googlecarrier.Config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;


public class OneUplusGoogleCarrier {
    private static final String DIR_CARRIERSETTINGS = "/product/etc/CarrierSettings";
    private static final String FILE_PB_CARRIER_LIST = "carrier_list.pb";
    private static final String FILE_PB_DEFAULT = "default.pb";
    private static final String FILE_PB_OTHERS = "others.pb";
    private static final String MCCMNC_SKT = "45005";
    private static final String MCCMNC_KT = "45008";
    private static final String MCCMNC_LGU = "45006";
    private static final String CN_SKT;
    private static final String CN_KT;
    private static final String CN_LGU;
    private static final String CN_DEFAULT = "default";
    private static final Map<String, byte[]> map = new HashMap<>();
    private static boolean bAddVolte = false;
    private static final List<String> outputDirs = new ArrayList<>();

    static {
        CarrierList cl = null;
        File file = new File(DIR_CARRIERSETTINGS + "/" + FILE_PB_CARRIER_LIST);
        if (file.isFile() && file.canRead()) {
            try (FileInputStream in = new FileInputStream(file)) {
                cl = CarrierList.parseFrom(in);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String cnSkt = "45005";
        String cnKt = "kt_kr";
        String cnLgu = "lguplus_kr";
        if (cl != null) {
            for (CarrierMap cm : cl.getEntryList()) {
                String mccMnc = cm.getCarrierId().getMccMnc();
                if (MCCMNC_SKT.equals(mccMnc)) {
                    cnSkt = cm.getCanonicalName();
                } else if (MCCMNC_KT.equals(mccMnc)) {
                    cnKt = cm.getCanonicalName();
                } else if (MCCMNC_LGU.equals(mccMnc)) {
                    cnLgu = cm.getCanonicalName();
                }
            }
        }
        CN_SKT = cnSkt;
        CN_KT = cnKt;
        CN_LGU = cnLgu;
    }

    public static void main(String[] args) {
        parseCmdLineArgs(args);

        Config cVolte1 = Config.newBuilder()
                .setKey("carrier_volte_available_bool")
                .setBoolValue(true)
                .build();
        Config cVolte2 = Config.newBuilder()
                .setKey("editable_enhanced_4g_lte_bool")
                .setBoolValue(true)
                .build();
        Config cOneUplus1 = Config.newBuilder()
                .setKey("carrier_name_override_bool")
                .setBoolValue(true)
                .build();
        Config cOneUplus2 = Config.newBuilder()
                .setKey("carrier_name_string")
                .setTextValue("LG U+")
                .build();

        // orderSkt
        // Pair<String, List<Config>> orderSkt = new Pair<String, List<Config>>(CN_SKT, new ArrayList<Config>());
        Pair<String, List<Config>> orderSkt = Pair.<String, List<Config>>create(CN_SKT, new ArrayList<Config>());
        if (bAddVolte) {
            orderSkt.second.add(cVolte1);
        }

        // orderKt
        // Pair<String, List<Config>> orderKt = new Pair<String, List<Config>>(CN_KT, new ArrayList<Config>());
        Pair<String, List<Config>> orderKt = Pair.<String, List<Config>>create(CN_KT, new ArrayList<Config>());
        if (bAddVolte) {
            orderKt.second.add(cVolte1);
        }

        // orderLgu
        // Pair<String, List<Config>> orderLgu = new Pair<String, List<Config>>(CN_LGU, new ArrayList<Config>());
        Pair<String, List<Config>> orderLgu = Pair.<String, List<Config>>create(CN_LGU, new ArrayList<Config>());
        if (bAddVolte) {
            orderLgu.second.add(cVolte1);
            orderLgu.second.add(cVolte2);
        }
        orderLgu.second.add(cOneUplus1);
        orderLgu.second.add(cOneUplus2);

        // orders
        List<Pair<String, List<Config>>> orders = new ArrayList<>();
        orders.add(orderSkt);
        orders.add(orderKt);
        orders.add(orderLgu);

        // modifying
        modifyConfig(orders);

        // saving
        if (saveModifiedConfig(outputDirs) > 0) {
            System.exit(1);
        }
    }

    private static void parseCmdLineArgs(String[] args) {
        boolean postDoubleDash = false;
        for (String arg : args) {
            char[] chars = arg.toCharArray();
            int len = chars.length;
            if (postDoubleDash || len < 2 || chars[0] != '-') {
                outputDirs.add(arg);
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

    private static int modifyConfig(List<Pair<String, List<Config>>> orders) {
        int mapUpdtedCount = 0;

        for (Pair<String, List<Config>> order : orders) {
            // order.first == canonical name of the carrier (String)
            // order.secnod == List of Config to be applied (List<Config>)
            String fileName = order.first + ".pb";
            {
                File file = new File(DIR_CARRIERSETTINGS + "/" + fileName);
                if (!file.isFile() || !file.canRead()) {
                    fileName = FILE_PB_OTHERS;
                    file = new File(DIR_CARRIERSETTINGS + "/" + fileName);
                    if (!file.isFile() || !file.canRead())
                        continue;
                }
            }

            MultiCarrierSettings mcs = null;
            int csIndex = -1;
            CarrierSettings cs = null;

            if (map.containsKey(fileName)) {
                byte[] byteArr = map.get(fileName);
                try {
                    if (FILE_PB_OTHERS.equals(fileName)) {
                        mcs = MultiCarrierSettings.parseFrom(byteArr);
                    } else {
                        cs = CarrierSettings.parseFrom(byteArr);
                    }
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            } else {
                try (FileInputStream in = new FileInputStream(DIR_CARRIERSETTINGS + "/" + fileName)) {
                    if (FILE_PB_OTHERS.equals(fileName)) {
                        mcs = MultiCarrierSettings.parseFrom(in);
                    } else {
                        cs = CarrierSettings.parseFrom(in);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (mcs != null) {
                for (int i = 0; i < mcs.getSettingCount(); i++) {
                    if (mcs.getSetting(i).getCanonicalName().equals(order.first)) {
                        cs = mcs.getSetting(i);
                        csIndex = i;
                        break;
                    }
                }
            }

            if (cs == null) continue;

            CarrierConfig.Builder ccBuilder = cs.getConfigs().toBuilder();
            boolean isDirty = false;
            for (Config c : order.second) {
                // IF c already exists in configList, DO NOT ADD c to ccBuilder
                if (ccBuilder.getConfigList().contains(c)) continue;
                // Before adding c, remove all the configs which has the same key as c's
                String key = c.getKey();
                for (int i = ccBuilder.getConfigCount() - 1; i >= 0; i--) {
                    if (key.equals(ccBuilder.getConfig(i).getKey())) {
                        ccBuilder.removeConfig(i);
                        isDirty = true;
                    }
                }
                // Insert c at alphabetical key position
                int count = ccBuilder.getConfigCount();
                int i;
                for (i = 0; i < count; i++) {
                    if (key.compareTo(ccBuilder.getConfig(i).getKey()) < 0) {
                        ccBuilder.addConfig(i, c);
                        isDirty = true;
                        break;
                    }
                }
                if (i == count) {
                    ccBuilder.addConfig(c);
                    isDirty = true;
                }
            }

            // If isDirty is true, put the contents(byte[]) of the updated file into the map
            // with the filename as the key.
            // Note: if the key already exists, the value is overwritten when put() is executed.
            if (isDirty) {
                CarrierSettings.Builder csBuilder = cs.toBuilder();
                csBuilder.setConfigs(ccBuilder);
                if (mcs != null) {
                    MultiCarrierSettings.Builder mcsBuilder = mcs.toBuilder();
                    mcsBuilder.setSetting(csIndex, csBuilder);
                    map.put(fileName, mcsBuilder.build().toByteArray());
                } else {
                    map.put(fileName, csBuilder.build().toByteArray());
                }
                mapUpdtedCount++;
            }
        }

        return mapUpdtedCount;
    }

    private static int saveModifiedConfig(List<String> outputDirs) {
        int failedCount = 0;
        for (String outputDir : outputDirs) {
            if (outputDir == null) continue;
            File dir = new File(outputDir);
            if (!dir.isDirectory()) {
                System.err.println("'" + outputDir + "': Not a directory");
                failedCount++;
                continue;
            }
            if (!dir.canWrite() || !dir.canExecute()) {
                System.err.println("'" + outputDir + "': Permission denied");
                failedCount++;
                continue;
            }

            for (Map.Entry<String, byte[]> entry : map.entrySet()) {
                try (OutputStream out = new FileOutputStream(outputDir + "/" + entry.getKey())) {
                    out.write(entry.getValue());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    failedCount++;
                } catch (IOException e) {
                    e.printStackTrace();
                    failedCount++;
                }
            }
        }
        return failedCount;
    }

}
