package com.egintra.common.utils.bank;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.interfaces.RSAKey;

public class CBRSA {
    public static byte[] encrypt(RSAKey key, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding", "BC");
            cipher.init(1, (Key) key);
            int step = key.getModulus().bitLength() / 8;
            int n = data.length / step;
            if (n > 0) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                for (int i = 0; i < n; i++) {
                    baos.write(cipher.doFinal(data, i * step, step));
                }
                if ((n = data.length % step) != 0) {
                    baos.write(cipher.doFinal(data, data.length - n, n));
                }
                return baos.toByteArray();
            }
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new Exception("MPCM033");
        }
    }

    public static byte[] decrypt(RSAKey key, byte[] raw) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding", "BC");
            cipher.init(2, (Key) key);
            int step = key.getModulus().bitLength() / 8;
            int n = raw.length / step;
            if (n > 0) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                for (int i = 0; i < n; i++) {
                    baos.write(cipher.doFinal(raw, i * step, step));
                }
                return baos.toByteArray();
            }
            return cipher.doFinal(raw);
        } catch (Exception e) {
            throw new Exception("MPCM033");
        }
    }
}