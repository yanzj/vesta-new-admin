package com.maxrocky.vesta.sso;

import java.util.Random;

class NumberTools {
    NumberTools() {
    }

    public static String Rands(int length) {
        Random rd = new Random();
        String n = "";

        do {
            int rdGet;
            if (rd.nextInt() % 2 == 1) {
                rdGet = Math.abs(rd.nextInt()) % 10 + 48;
            } else {
                rdGet = Math.abs(rd.nextInt()) % 26 + 97;
            }

            char num1 = (char) rdGet;
            String dd = Character.toString(num1);
            n = n + dd;
        } while (n.length() < length);

        return n;
    }

    public static String getClientRandem(int length) {
        String rand = Rands(length);
        byte[] key = rand.getBytes();
        String temp = Base64Tools.getBASE64(key);
        temp = temp.replaceAll("\r", "");
        temp = temp.replaceAll("\n", "");
        return temp;
    }
}
