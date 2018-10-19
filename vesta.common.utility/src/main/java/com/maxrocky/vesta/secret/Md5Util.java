package com.maxrocky.vesta.secret;

import com.maxrocky.vesta.utility.StringUtil;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Util {

    /**
     *
     * @param source
     * @return
     */
    public static byte[] getMD5(byte[] source) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            md.update(source);
            byte[] output = new byte[16];
            int len = md.digest(output, 0, output.length);
            if (len != output.length) {
                System.err.println("md5 error");
                return null;
            }
            return output;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param b
     * @return
     */
    public static String byte2hex(byte[] b) {
        String stmp = "";
        StringBuilder builder = new StringBuilder();
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                builder.append('0');
            }
            builder.append(stmp);
        }
        return builder.toString();
    }

    /**
     *
     * @param source
     * @return
     */
    public static String getMd5String(byte[] source) {
        byte[] md5 = getMD5(source);
        return byte2hex(md5);
    }

    /**
     *
     * @param str
     * @return
     */
    public static String getMd516String(String str) {
        String result = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString().substring(8, 24);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param inStr
     * @return
     */
    public static String getMd5String(String inStr) {
        if(StringUtil.isEmpty(inStr)){
            return getMd5String(inStr.getBytes());
        }
        return inStr;
      }



}
