package com.xj.util;

import java.security.MessageDigest;

/**
 * @author EdSherran
 */
public class Md5Util {

    static char[] hex = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    //加盐
    public static String doMD5s(String oragindate) {
        String data = "";
        String salt = "huang";
        data = Md5Util.doMD5(Md5Util.doMD5(oragindate) + salt);
        return data;
    }

    public static String doMD5(String oragindate) {
        String data = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(oragindate.getBytes());
            data = byte2str(md5.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }


    private static String byte2str(byte[] bytes) {
        int len = bytes.length;
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < len; i++) {
            byte byte0 = bytes[i];
            result.append(hex[byte0 >>> 4 & 0xf]);
            result.append(hex[byte0 & 0xf]);
        }
        return result.toString();
    }
} 
