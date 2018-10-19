package com.maxrocky.vesta.sso;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

class DES3Tools {
    private byte[] key = new byte[24];
    private byte[] keyiv = new byte[8];
    private byte[] des3r;

    DES3Tools() {
    }

    public void getKeyAndIv(byte[] cr1, byte[] sr1) {
        for(int i = 0; i < 8; ++i) {
            if(i < 6) {
                this.key[i * 4] = cr1[i];
                this.key[i * 4 + 1] = sr1[i * 3];
                this.key[i * 4 + 2] = sr1[i * 3 + 1];
                this.key[i * 4 + 3] = sr1[i * 3 + 2];
            } else {
                this.keyiv[(i - 6) * 4] = cr1[i];
                this.keyiv[(i - 6) * 4 + 1] = sr1[i * 3];
                this.keyiv[(i - 6) * 4 + 2] = sr1[i * 3 + 1];
                this.keyiv[(i - 6) * 4 + 3] = sr1[i * 3 + 2];
            }
        }

    }

    public byte[] intToByteArray(int integer) {
        int byteNum = (40 - numberOfLeadingZeros(integer < 0?~integer:integer)) / 8;
        byte[] byteArray = new byte[4];

        for(int n = 0; n < byteNum; ++n) {
            byteArray[3 - n] = (byte)(integer >>> n * 8);
        }

        return byteArray;
    }

    public static int numberOfLeadingZeros(int i) {
        if(i == 0) {
            return 32;
        } else {
            int n = 1;
            if(i >>> 16 == 0) {
                n += 16;
                i <<= 16;
            }

            if(i >>> 24 == 0) {
                n += 8;
                i <<= 8;
            }

            if(i >>> 28 == 0) {
                n += 4;
                i <<= 4;
            }

            if(i >>> 30 == 0) {
                n += 2;
                i <<= 2;
            }

            n -= i >>> 31;
            return n;
        }
    }

    public int byteArrayToInt(byte[] b, int offset) {
        int value = 0;

        for(int i = 0; i < 4; ++i) {
            int shift = (3 - i) * 8;
            value += (b[i + offset] & 255) << shift;
        }

        return value;
    }

    public byte[] tearDownArray(byte[] byteArray, byte[] cr1, int flag) {
        int num = byteArray.length / 16;
        byte[] over = new byte[byteArray.length];
        boolean turnNum = false;

        for(int i = 0; i < num; ++i) {
            byte[] temp = new byte[16];

            for(int j = 0; j < 16; ++j) {
                temp[j] = byteArray[i * 16 + j];
            }

            int var10;
            if(flag == 0) {
                var10 = this.computeEncryptDirection(cr1[i]);
            } else {
                var10 = this.computeDecryptDirection(cr1[i]);
            }

            temp = this.transpose(temp, var10);
            System.arraycopy(temp, 0, over, i * 16, 16);
        }

        return over;
    }

    private byte[] transpose(byte[] block, int direction) {
        for(int i = 0; i < direction; ++i) {
            byte[] tmpArr = (byte[])block.clone();
            byte len = 3;

            for(int x = 0; x < 4; ++x) {
                for(int y = 0; y < 4; ++y) {
                    block[y * 4 + x] = tmpArr[x * 4 + (len - y)];
                }
            }
        }

        return block;
    }

    private int computeEncryptDirection(byte factor) {
        int hight = (factor & 240) >> 4;
        int low = factor & 15;
        int frequenc = hight % 4;
        int direction = low % 2;
        return direction == 1?frequenc:4 - frequenc;
    }

    private int computeDecryptDirection(byte factor) {
        int hight = (factor & 240) >> 4;
        int low = factor & 15;
        int frequenc = hight % 4;
        int direction = low % 2;
        return direction == 0?frequenc:4 - frequenc;
    }

    public int bytesToInt(byte[] intByte) {
        int fromByte = 0;

        for(int i = 0; i < intByte.length; ++i) {
            int n = (intByte[i] < 0?intByte[i] + 256:intByte[i]) << 8 * i;
            fromByte += n;
        }

        return fromByte;
    }

    public void unMixedAlgorithm(byte[] tear_array) {
        byte[] token_len_byte = new byte[4];
        System.arraycopy(tear_array, 0, token_len_byte, 0, 2);
        int token_len = this.bytesToInt(token_len_byte);
        System.arraycopy(tear_array, 5, this.key, 0, 24);
        this.des3r = new byte[token_len];
        System.arraycopy(tear_array, 5 + this.key.length, this.des3r, 0, token_len);
        System.arraycopy(tear_array, 5 + this.key.length + token_len, this.keyiv, 0, this.keyiv.length);
    }

    public byte[] MixedAlgorithm() {
        byte[] key_sub = this.intToByteArray(this.key.length);
        byte[] keyiv_sub = this.intToByteArray(this.keyiv.length);
        byte[] data_sub = this.intToByteArray(this.des3r.length);
        byte[] matrix_sub = this.intToByteArray(16);
        int length = 5 + this.key.length + this.des3r.length + this.keyiv.length;
        int mod_len = 16 - length % 16;
        byte[] mixed_byte = new byte[length + mod_len];
        mixed_byte[0] = data_sub[3];
        mixed_byte[1] = data_sub[2];
        mixed_byte[2] = key_sub[3];
        mixed_byte[3] = keyiv_sub[3];
        mixed_byte[4] = matrix_sub[3];
        System.arraycopy(this.key, 0, mixed_byte, 5, this.key.length);
        System.arraycopy(this.des3r, 0, mixed_byte, 29, this.des3r.length);
        int mixed_len = 29 + this.des3r.length;
        System.arraycopy(this.keyiv, 0, mixed_byte, 29 + this.des3r.length, this.keyiv.length);
        mixed_len += this.keyiv.length;
        byte temp = 0;

        for(int i = 0; i < mod_len; ++i) {
            mixed_byte[mixed_len + i] = temp;
        }

        return mixed_byte;
    }

    public void des3EncodeCBC(byte[] data) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(this.key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        SecretKey deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(this.keyiv);
        cipher.init(1, deskey, ips);
        this.des3r = cipher.doFinal(data);
    }

    public byte[] des3DecodeCBC() throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(this.key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        SecretKey deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/CBC/PKCS5Padding");
        IvParameterSpec ips = new IvParameterSpec(this.keyiv);
        cipher.init(2, deskey, ips);
        byte[] bOut = cipher.doFinal(this.des3r);
        return bOut;
    }

    private static byte[] des3DecodeECB(byte[] key, byte[] data) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key);
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
        SecretKey deskey = keyfactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance("desede/ECB/PKCS5Padding");
        cipher.init(2, deskey);
        return cipher.doFinal(data);
    }

    public static String getECBDecodeStr(String str) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] keyByte = md5.digest(SsoClientUtils.KEY.getBytes("utf-8"));
        byte[] lastkeyByte = new byte[24];
        System.arraycopy(keyByte, 0, lastkeyByte, 4, keyByte.length);
        byte[] encodeByte = des3DecodeECB(lastkeyByte, Base64Tools.getFromBASE64(str));
        return new String(encodeByte, "utf-8");
    }

    public byte[] getDes3r() {
        return this.des3r;
    }

    public void setDes3r(byte[] des3r) {
        this.des3r = des3r;
    }

    public byte[] getKey() {
        return this.key;
    }

    public void setKey(byte[] key) {
        this.key = key;
    }

    public byte[] getKeyiv() {
        return this.keyiv;
    }

    public void setKeyiv(byte[] keyiv) {
        this.keyiv = keyiv;
    }
}

