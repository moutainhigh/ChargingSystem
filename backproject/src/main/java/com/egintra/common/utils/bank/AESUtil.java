package com.egintra.common.utils.bank;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class AESUtil {
    private static int KeySizeAES128 = 16;

    private static Cipher getCipher(int mode, String key) {
        byte[] keyPtr = new byte[KeySizeAES128];
        IvParameterSpec ivParam = new IvParameterSpec(keyPtr);
        byte[] passPtr = key.getBytes();
        try {
            Cipher mCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            for (int i = 0; i < KeySizeAES128; i++) {
                if (i < passPtr.length)
                    keyPtr[i] = passPtr[i];
                else
                    keyPtr[i] = 0;
            }
            SecretKeySpec keySpec = new SecretKeySpec(keyPtr, "AES");
            mCipher.init(mode, keySpec, ivParam);
            return mCipher;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] encrypt(String content, String password) {
        try {
            Cipher cipher = getCipher(1, password);
            byte[] result = cipher.doFinal(content.getBytes("UTF-8"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] decrypt(byte[] content, String password) {
        try {
            Cipher cipher = getCipher(2, password);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getRandomAESKey() {
        int $aes_ken_len = 16;
        String aes_key_str = "";
        char[] e = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
                .toCharArray();
        int index = 0;
        Random r = new Random();
        for (int i = 0; i < $aes_ken_len; i++) {
            index = r.nextInt(64);
            aes_key_str = aes_key_str + e[index];
        }
        return aes_key_str;
    }
}
