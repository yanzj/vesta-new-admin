package com.maxrocky.vesta.sso;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

class Base64Tools {
    Base64Tools() {
    }

    public static String getBASE64(byte[] b) {
        String s = null;
        if(b != null) {
            s = (new BASE64Encoder()).encode(b);
        }

        return s;
    }

    public static byte[] getFromBASE64(String s) {
        Object b = null;
        if(s != null) {
            BASE64Decoder decoder = new BASE64Decoder();

            try {
                byte[] b1 = decoder.decodeBuffer(s);
                return b1;
            } catch (Exception var4) {
                SsoLogger.error(var4);
            }
        }

        return (byte[])b;
    }
}
