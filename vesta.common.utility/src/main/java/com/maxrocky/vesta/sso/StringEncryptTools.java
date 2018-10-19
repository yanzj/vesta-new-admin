package com.maxrocky.vesta.sso;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tom on 2016/2/25 14:21.
 * Describe:
 */
public class StringEncryptTools {
    public static String Encrypt(byte[] bt, String encName)
    {
        MessageDigest md = null;
        String strDes = null;
        try
        {
            if ((encName == null) || (encName.equals(""))) {
                encName = "SHA-256";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytes2Hex(md.digest());
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
        return strDes;
    }

    private static String bytes2Hex(byte[] bts)
    {
        String des = "";
        String tmp = null;
        for (int i = 0; i < bts.length; i++)
        {
            tmp = Integer.toHexString(bts[i] & 0xFF);
            if (tmp.length() == 1) {
                des = des + "0";
            }
            des = des + tmp;
        }
        return des;
    }
}
