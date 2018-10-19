package com.maxrocky.vesta.pay.bill;

import sun.misc.BASE64Encoder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.*;

/**
 * Created by Tom on 2016/2/22 13:55.
 * Describe:快钱支付PKI工具
 */
public class PKITools {

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";
    public static final String INPUT_CHARSET = "utf-8";

    /**
     * RSA签名
     * @param content 待签名数据
     * @param pfxPath pfx证书路径
     * @param keyPassword pfx密码
     * @return 签名值
     */
    public static String getSign(String content, String pfxPath, String keyPassword, String keyName) {

        System.out.println("content--->>"+ content);

        String sign = "";
        try {
            /* 秘钥仓库 */
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            /* 读取秘钥仓库 */
            FileInputStream keyFileInputStream = new FileInputStream(pfxPath);
            BufferedInputStream keyBufferedInputStream = new BufferedInputStream(keyFileInputStream);

            char[] keyPwd = keyPassword.toCharArray();
            keyStore.load(keyBufferedInputStream, keyPwd);
            /* 从秘钥仓库中得到私钥 */
            PrivateKey privateKey = (PrivateKey)keyStore.getKey(keyName, keyPwd);
            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(privateKey);
            signature.update(content.getBytes(INPUT_CHARSET));
            BASE64Encoder encoder = new BASE64Encoder();
            sign = encoder.encode(signature.sign());

            System.out.println("signMsg--->>"+sign);

        } catch (KeyStoreException e) {
            e.printStackTrace();
            System.out.println("KeyStore.getInstance is exception.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("FileInputStream is exception, pfxPath-->" + pfxPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sign;
    }

}
