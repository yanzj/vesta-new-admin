package com.maxrocky.vesta.sso;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

class GuidTool {
    public String valueBeforeMD5 = "";
    public String valueAfterMD5 = "";
    private static Random myRand;
    private static SecureRandom mySecureRand = new SecureRandom();
    private static String s_id;

    static {
        long secureInitializer = mySecureRand.nextLong();
        myRand = new Random(secureInitializer);

        try {
            s_id = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException var3) {
            SsoLogger.error(var3);
        }

    }

    public GuidTool() {
        this.getRandomGUID(false);
    }

    public GuidTool(boolean secure) {
        this.getRandomGUID(secure);
    }

    private void getRandomGUID(boolean secure) {
        MessageDigest md5 = null;
        StringBuffer sbValueBeforeMD5 = new StringBuffer();

        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException var12) {
            SsoLogger.error(var12);
        }

        try {
            long e = System.currentTimeMillis();
            long rand = 0L;
            if(secure) {
                rand = mySecureRand.nextLong();
            } else {
                rand = myRand.nextLong();
            }

            sbValueBeforeMD5.append(s_id);
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(e));
            sbValueBeforeMD5.append(":");
            sbValueBeforeMD5.append(Long.toString(rand));
            this.valueBeforeMD5 = sbValueBeforeMD5.toString();
            md5.update(this.valueBeforeMD5.getBytes());
            byte[] array = md5.digest();
            StringBuffer sb = new StringBuffer();

            for(int j = 0; j < array.length; ++j) {
                int b = array[j] & 255;
                if(b < 16) {
                    sb.append('0');
                }

                sb.append(Integer.toHexString(b));
            }

            this.valueAfterMD5 = sb.toString();
        } catch (Exception var13) {
            SsoLogger.error(var13);
        }

    }

    public String toString() {
        String raw = this.valueAfterMD5.toUpperCase();
        StringBuffer sb = new StringBuffer();
        sb.append(raw.substring(0, 8));
        sb.append("-");
        sb.append(raw.substring(8, 12));
        sb.append("-");
        sb.append(raw.substring(12, 16));
        sb.append("-");
        sb.append(raw.substring(16, 20));
        sb.append("-");
        sb.append(raw.substring(20));
        return sb.toString();
    }
}

