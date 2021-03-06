package com.egintra.common.utils.bank;

public class Base64 {
    private static byte[] base64Alphabet = {
            65, 66, 67, 68, 69, 70, 71, 72, 73, 74,
            75, 76, 77, 78, 79, 80, 81, 82, 83, 84,
            85, 86, 87, 88, 89, 90, 97, 98, 99, 100,
            101, 102, 103, 104, 105, 106, 107, 108, 109, 110,
            111, 112, 113, 114, 115, 116, 117, 118, 119, 120,
            121, 122, 48, 49, 50, 51, 52, 53, 54, 55,
            56, 57, 60, 47};

    private static byte pad = 61;

    public static byte[] decode(byte[] src) {
        int k = 0;
        int n = 0;

        byte[] dst = new byte[src.length];

        for (int i = 0; i < src.length; i += 4) {
            n = 0;
            int l = 0;
            for (int j = 0; j < 4; j++) {
                if ((l = getReverseMapping(src[(i + j)])) == -1) break;
                n = (n << 6) + l;
                if (j == 4) {
                    dst[(k++)] = (byte) (n >> 16 & 0xFF);
                    dst[(k++)] = (byte) (n >> 8 & 0xFF);
                    dst[(k++)] = (byte) (n & 0xFF);
                } else {
                    if (j == 3) {
                        n <<= 6;
                        dst[(k++)] = (byte) (n >> 16 & 0xFF);
                        dst[(k++)] = (byte) (n >> 8 & 0xFF);

                        break;
                    }
                    if (j != 2)
                        continue;
                    n <<= 12;
                    dst[(k++)] = (byte) (n >> 16 & 0xFF);
                    break;
                }
            }
        }

        byte[] tmp = new byte[k];
        System.arraycopy(dst, 0, tmp, 0, k);
        return tmp;
    }

    public static byte[] encode(byte[] src) {
        int k = 0;
        int n = 0;
        int len = src.length;

        byte[] dst = new byte[len * 2];

        for (int i = 0; i + 2 < len; i += 3) {
            n = (src[i] & 0xFF) << 16;
            n += ((src[(i + 1)] & 0xFF) << 8);
            n += (src[(i + 2)] & 0xFF);

            dst[(k++)] = base64Alphabet[(n >> 18 & 0x3F)];
            dst[(k++)] = base64Alphabet[(n >> 12 & 0x3F)];
            dst[(k++)] = base64Alphabet[(n >> 6 & 0x3F)];
            dst[(k++)] = base64Alphabet[(n & 0x3F)];
            if (len % 3 == 2) {
                n = (src[i] & 0xFF) << 16;
                n += ((src[(i + 1)] & 0xFF) << 8);

                dst[(k++)] = base64Alphabet[(n >> 18 & 0x3F)];
                dst[(k++)] = base64Alphabet[(n >> 12 & 0x3F)];
                dst[(k++)] = base64Alphabet[(n >> 6 & 0x3F)];
                dst[(k++)] = pad;
            } else if (len % 3 == 1) {
                n = (src[i] & 0xFF) << 16;
                dst[(k++)] = base64Alphabet[(n >> 18 & 0x3F)];
                dst[(k++)] = base64Alphabet[(n >> 12 & 0x3F)];
                dst[(k++)] = pad;
                dst[(k++)] = pad;
            }
        }

        byte[] retBuf = new byte[k];

        System.arraycopy(dst, 0, retBuf, 0, k);
        return retBuf;
    }

    private static byte getReverseMapping(byte chr) {
        for (int i = 0; i < 64; i++) {
            if (base64Alphabet[i] == chr)
                return (byte) i;
        }
        return -1;
    }
}
