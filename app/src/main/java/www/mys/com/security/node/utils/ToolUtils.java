package www.mys.com.security.node.utils;

import android.Manifest;
import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;

import androidx.core.app.ActivityCompat;

import www.mys.com.security.node.base.StaticParam;

public class ToolUtils {

    public static ClipboardManager getClipboardManager(Context context) {
        return (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
    }

    public static String copyText(ClipboardManager clipboard, String text) {
        ClipData clipData = ClipData.newPlainText(null, text);
        clipboard.setPrimaryClip(clipData);
        return getText(clipboard);
    }

    public static String getText(ClipboardManager clipboard) {
        ClipData clipData = clipboard.getPrimaryClip();
        if (clipData != null && clipData.getItemCount() > 0) {
            return clipData.getItemAt(0).getText().toString();
        }
        return null;
    }


    public static String getSDCardPath() {
        try {
            if (Environment.getExternalStorageDirectory() != null
                    && Environment.getExternalStorageDirectory().exists()) {
                return Environment.getExternalStorageDirectory().getAbsolutePath();
            }
        } catch (Exception e) {
            LogUtils.log("getSDCardPath error.e=" + e);
        }
        return "/sdcard";
    }

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    public static void verifyStoragePermissions(Activity activity) {
        try {
            int permission = ActivityCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isFirstIn() {
        return !MySettings.getInstance().getBooleanSetting(StaticParam.INIT_ED);
    }

    public static void saveSecurityCode(String securityCode) {
        MySettings.getInstance().saveSetting(StaticParam.SECURITY_CODE, securityCode);
    }

    public static String getSecurityCode() {
        String result = MySettings.getInstance().getStringSetting(StaticParam.SECURITY_CODE);
        if (StringUtils.isEmpty(result)) {
            result = getNewSecurityCode();
            saveSecurityCode(result);
        }
        return result;
    }

    public static String getNewSecurityCode() {
        return MD5Utils.MD5(StringUtils.getRandomStr(32), false);
    }

}
