package com.maxrocky.vesta.pay;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Tom on 2016/1/26 17:21.
 * Describe:
 */
public class RSA {

    public static final String  SIGN_ALGORITHMS = "SHA1WithRSA";

    /**
     * RSA签名
     * @param content 待签名数据
     * @param ali_private_key 商户私钥
     * @param input_charset 编码格式
     * @return 签名值
     */
    public static String getSign(String content, String ali_private_key, String input_charset){
        try {
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(Base64.decode(ali_private_key));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(privateKey);
            signature.update(content.getBytes(input_charset));

            byte[] signed = signature.sign();

            return Base64.encode(signed);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * RSA验签名检查
     * @param content 待签名数据
     * @param sign 签名值
     * @param ali_public_key 支付宝公钥
     * @param input_charset 编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String ali_public_key, String input_charset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(ali_public_key);
            PublicKey publicKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initVerify(publicKey);
            signature.update(content.getBytes(input_charset));

            byte[] signByte = Base64.decode(sign);

            return signature.verify(signByte);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }

    /**
     * 得到私钥
     * @param key 密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {

        byte[] keyBytes;

        keyBytes = Base64.decode(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        return privateKey;
    }

}
