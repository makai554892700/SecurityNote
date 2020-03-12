package www.mys.com.security.node.utils.encrypt;

import www.mys.com.security.node.utils.ToolUtils;

public class EncryptUtils {

    public static String encrypt(String data) {
        return RC4Utils.enCode(ToolUtils.getSecurityCode(), data);
    }

    public static String decrypt(String data) {
        return RC4Utils.deCode(ToolUtils.getSecurityCode(), data);
    }

    public static String encrypt(String key, String data) {
        return RC4Utils.enCode(key, data);
    }

    public static String decrypt(String key, String data) {
        return RC4Utils.deCode(key, data);
    }

}
