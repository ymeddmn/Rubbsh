package com.mage.permissionapplysettinggdemo;

import android.os.Build;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * author  :mayong
 * function:
 * date    :2020-06-04
 **/
public class Phone {
    private static final String TAG = "Rom";

    public static final String ROM_MIUI = "MIUI";
    public static final String ROM_EMUI = "EMUI";
    public static final String ROM_FLYME = "FLYME";
    public static final String ROM_OPPO = "OPPO";
    public static final String ROM_SMARTISAN = "SMARTISAN";
    public static final String ROM_VIVO = "VIVO";
    public static final String ROM_QIKU = "QIKU";

    private static final String KEY_VERSION_MIUI = "ro.miui.ui.version.name";
    private static final String KEY_VERSION_EMUI = "ro.build.version.emui";
    private static final String KEY_VERSION_OPPO = "ro.build.version.opporom";
    private static final String KEY_VERSION_SMARTISAN = "ro.smartisan.version";
    private static final String KEY_VERSION_VIVO = "ro.vivo.os.version";

    private static String sName;
    private static String sVersion;

    //华为
    public static boolean isEmui() {
        return check(ROM_EMUI);
    }

    //小米
    public static boolean isMiui() {
        return check(ROM_MIUI);
    }

    //vivo
    public static boolean isVivo() {
        return check(ROM_VIVO);
    }

    //oppo
    public static boolean isOppo() {
        return check(ROM_OPPO);
    }

    //魅族
    public static boolean isFlyme() {
        return check(ROM_FLYME);
    }

    //360手机
    public static boolean is360() {
        return check(ROM_QIKU) || check("360");
    }

    interface PhoneBrand {
        void isEmui();

        void isMiui();

        void isVivo();

        void isOppo();

        void isFlyme();

        void isHammer();//锤子

        void is360();

        void isNotHMOV();//非华米OV机型

        void isOther();//非上面列举机型
    }

    public static boolean isSmartisan() {
        return check(ROM_SMARTISAN);
    }

    public static String getName() {
        if (sName == null) {
            check("");
        }
        return sName;
    }

    public static String getVersion() {
        if (sVersion == null) {
            check("");
        }
        return sVersion;
    }

    private static boolean check(String rom) {
        if (sName != null) {
            return sName.equals(rom);
        }

        if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_MIUI))) {
            sName = ROM_MIUI;
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_EMUI))) {
            sName = ROM_EMUI;
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_OPPO))) {
            sName = ROM_OPPO;
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_VIVO))) {
            sName = ROM_VIVO;
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_SMARTISAN))) {
            sName = ROM_SMARTISAN;
        } else {
            sVersion = Build.DISPLAY;
            if (sVersion.toUpperCase().contains(ROM_FLYME)) {
                sName = ROM_FLYME;
            } else {
                sVersion = Build.UNKNOWN;
                sName = Build.MANUFACTURER.toUpperCase();
            }
        }
        return sName.equals(rom);
    }

    public static void checkBrand( PhoneBrand phoneBrand) {
        if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_MIUI))) {
            sName = ROM_MIUI;
            phoneBrand.isMiui();
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_EMUI))) {
            sName = ROM_EMUI;
            phoneBrand.isEmui();
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_OPPO))) {
            sName = ROM_OPPO;
            phoneBrand.isOppo();
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_VIVO))) {
            sName = ROM_VIVO;
            phoneBrand.isVivo();
        } else if (!TextUtils.isEmpty(sVersion = getProp(KEY_VERSION_SMARTISAN))) {
            sName = ROM_SMARTISAN;
            phoneBrand.isHammer();
        } else {
            sVersion = Build.DISPLAY;
            if (sVersion.toUpperCase().contains(ROM_FLYME)) {
                sName = ROM_FLYME;
                phoneBrand.isFlyme();
            } else {
                phoneBrand.isOther();
                sVersion = Build.UNKNOWN;
                sName = Build.MANUFACTURER.toUpperCase();
            }
        }
        if (!isEmui() && !isMiui() && !isOppo() && !isVivo()) {
            phoneBrand.isNotHMOV();
        }
//        return sName.equals(rom);
    }

    private static String getProp(String name) {
        String line = null;
        BufferedReader input = null;
        try {
            Process p = Runtime.getRuntime().exec("getprop " + name);
            input = new BufferedReader(new InputStreamReader(p.getInputStream()), 1024);
            line = input.readLine();
            input.close();
        } catch (IOException ex) {
//            Log.e(TAG, "Unable to read prop " + name, ex);
            return null;
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return line;
    }

    public static class PhoneBrandImpl implements PhoneBrand{

        @Override
        public void isEmui() {

        }

        @Override
        public void isMiui() {

        }

        @Override
        public void isVivo() {

        }

        @Override
        public void isOppo() {

        }

        @Override
        public void isFlyme() {

        }

        @Override
        public void isHammer() {

        }

        @Override
        public void is360() {

        }

        @Override
        public void isNotHMOV() {

        }

        @Override
        public void isOther() {

        }
    }
}
