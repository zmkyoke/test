package com.proper.phip.core.utils;

/**
 * 加密解密工具类
 */
public class CryptoUtils {

    /**
     * HIS加密字符串
     * @param text String 原字符串
     * @return String 加密后字符串
     */
    public static String hisEncrypt(String text) {
        String encryptStr = "";
        try {
            byte[] textBytes = text.getBytes("UTF-16BE");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < textBytes.length; i++) {
                textBytes[i] = (byte)(~textBytes[i]);
                sb.append(Integer.toHexString(textBytes[i]).substring(6));
            }
            encryptStr = sb.toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return encryptStr;
    }

    /**
     * HIS解密字符串
     * @param text String 源字符串
     * @return String 解密后字符串
     */
    public static String hisDecrypt(String text) {
        String decryptStr = "";
        try {
            byte[] textBytes = new byte[text.length() / 2];
            for (int i = 0; i < text.length(); i += 2) {
                textBytes[i / 2] = (byte)(Integer.parseInt(text.substring(i, i + 2), 16) + -256);
                textBytes[i / 2] = (byte)(~textBytes[i / 2]);
            }
            decryptStr = new String(textBytes, "UTF-16BE");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return decryptStr;
    }
}
