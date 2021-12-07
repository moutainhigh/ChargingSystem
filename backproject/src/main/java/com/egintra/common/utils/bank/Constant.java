package com.egintra.common.utils.bank;

import com.alibaba.fastjson.JSONObject;
import com.citicbank.cbframework.common.security.CBRSA;
import com.citicbank.cbframework.common.util.CBConverter;
import com.egintra.common.utils.DateUtils;
import com.lsy.baselib.crypto.util.Base64;
import com.lsy.baselib.crypto.util.FileUtil;

import javax.crypto.Cipher;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Date;

public class Constant {
    private static JSONObject sendBusinesstoServer(String urlstr, String business) throws Exception {
        URL url = new URL(urlstr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setConnectTimeout(5000);
        conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        conn.setRequestProperty("connection", "keep-alive");
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(true);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.connect();
        OutputStream os = conn.getOutputStream();
        os.write(business.getBytes("UTF-8"));
        os.flush();
        os.close();
        InputStream is = conn.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String respstr = null;
        StringBuilder sbr = new StringBuilder();
        while ((respstr = br.readLine()) != null) {
            sbr.append(respstr);
        }
        br.close();
        is.close();

        String responString = sbr.toString();
        System.out.println(responString);

        JSONObject jsonObject = JSONObject.parseObject(responString);

        String respbusiness = jsonObject.getString("encryptBody");
        String signStr = jsonObject.getString("sign");

        String[] encryptBusinessDataArr = respbusiness.split("@@");
        String responencryptKey = encryptBusinessDataArr[1];

        RSAPrivateKey privateKey = (RSAPrivateKey)getPrivateKeyForAes();
        byte[] keyByte = CBRSA.decrypt(privateKey, CBConverter.base64ToBytes(responencryptKey));
        String encryptKeystr = new String(keyByte, "UTF-8");
        System.out.println(encryptKeystr);
        byte[] decryptedBusinessDataBytes = AESUtil.decrypt(CBConverter.base64ToBytes(encryptBusinessDataArr[0]), encryptKeystr);
        String decryptedBusinessData = new String(decryptedBusinessDataBytes, "UTF-8");

        boolean issign = verifySign(decryptedBusinessData.getBytes("UTF-8"), signStr).booleanValue();
        System.out.println("sign is " + issign);

        JSONObject resultMsg = new JSONObject();
        resultMsg.put("decryptedBusinessData", decryptedBusinessData);
        resultMsg.put("issign", Boolean.valueOf(issign));
        return resultMsg;
    }

    public static Boolean verifySign(byte[] msg, String signData) throws Exception {
        String publicKey = "MIIDITCCAgmgAwIBAgIBMDANBgkqhkiG9w0BAQUFADArMQswCQYDVQQGEwJDTjENMAsGA1UECwwEUFROUjENMAsGA1UEAwwEdGVzdDAeFw0xNzA2MDEwMjMzNDZaFw0yNzA1MzAwMjMzNDZaMCsxCzAJBgNVBAYTAkNOMQ0wCwYDVQQLDARQVE5SMQ0wCwYDVQQDDAR0ZXN0MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAto3/T7lVxrlHOo1XcpwniHmCLxnnMU+9TMglpiy+Z6JeDzgHj1XX1dyf5fOXT/lV3F8A226rXD6WQS7M9vv4mcBlf0nbCCz/m+2wJPMhvpDetewaKn0tGnXudqM/vEWKzk4ePs+12elePsDr58I+ffXOTzZALrutY0rn4tsHkqBP7jWYY7UpERR+aFptbIMzTR3iMJY4LDbNu13DVMpbKKWLMheB2i4FJr8kZhC0Fnk3sltkN0XNOi4tRhzqZqIttXWWhTEnj3JKd+ZA3H2BovujpkNayPiyFFl+Jhd6wfnPLgBSCq4CjNm3OYIuIFb6zMEyWRRFbYSBa789hWSCPQIDAQABo1AwTjAdBgNVHQ4EFgQU9FIGuf/328a525OrV+7B4owIRPcwHwYDVR0jBBgwFoAU9FIGuf/328a525OrV+7B4owIRPcwDAYDVR0TBAUwAwEB/zANBgkqhkiG9w0BAQUFAAOCAQEAs+iRoOBndGaSdH8s3KZyy8uzNQKRF0wazQkKdFDuvzxrylbjZUzSS6cpkwuUgBAulLxpNrWzqyejRQ2yz6iiArtixBFiGhv9B380g1szKnCoVExFumdudCuQPMaUScyAr8Z9d17/EhQLTn8IAy1Sa1iLzOTONnMessZpf/PaguGZLwpkaU55syL4P3PAhLveHp/t7q6bTY/iAQrcc0Oqbngpk4vX8+9BqUrjIRs4cIjhU8K8+yHYPqogNC9FD2NL8JkMzpa/ekKR0knbdCaAB1rcuenFbGQAnS/JscndJv/xqW2z/psqb5XS9ivTRD5f67G0Bluo5ZdUrzrgkrjSrQ==";

        byte[] base64EncodedSenderCert = publicKey.getBytes("UTF-8");

        X509Certificate signerCertificate = CryptUtil.generateX509Certificate(
                Base64.decode(base64EncodedSenderCert));
        PublicKey signpublicKey = signerCertificate.getPublicKey();
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initVerify(signpublicKey);
        signature.update(msg);
        return Boolean.valueOf(signature.verify(Base64.decode(signData.getBytes("UTF-8"))));
    }

    private static String encryptBusiness(String business) throws Exception {
        String encryptKey = AESUtil.getRandomAESKey();
        byte[] encryptBusinessDataByte = AESUtil.encrypt(business, encryptKey);

        String encryptBusiness = new String(
                Base64.encode(encryptBusinessDataByte), "UTF-8");

        String publicKey = "MIIDQTCCAimgAwIBAgIBMDANBgkqhkiG9w0BAQUFADA6MQswCQYDVQQGEwJDTjENMAsGA1UECwwEQ05DQjEcMBoGA1UEAwwTYjJjLmJhbmsuZWNpdGljLmNvbTAgFw0xODEwMjIwMjU0MjZaGA8yMTE4MDkyODAyNTQyNlowOjELMAkGA1UEBhMCQ04xDTALBgNVBAsMBENOQ0IxHDAaBgNVBAMME2IyYy5iYW5rLmVjaXRpYy5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCJe5k8J5oxkRbfrNHHbN8MEce9lkV/HWrKtpEtknRICpyT24Rx3xiCkyFfC2Zn0sYofJAcAvKElDupIIuYJHMvwWgakYHVHAbsp8XfGDToDeZdabCPSaV/tC+pbcZjnF+Zdlmy/TL9Yzxe/lSqhAnjk2wIeBQUy27CpcOKO7GXMVFE9TgogqDND+hYYzyaj+8gh73DRu2xSAq+ZTvJuW+bPvuALEOK/Ex8MO9u0oV1nn3OwkPkE/98jSs8BWBRDDGuh9OaZrUoNsF7II/e10Ad93lPf7UTEAiosO7ZiyJ+MBGy+cbkCwjpe5OOrkFr+9GNI6pjcDYul2KgI6XXdX0bAgMBAAGjUDBOMB0GA1UdDgQWBBTFTb+SBCIKxAdjuaOlferd/wKWYTAfBgNVHSMEGDAWgBTFTb+SBCIKxAdjuaOlferd/wKWYTAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBQUAA4IBAQA4zYjdRonussCKJGAK+KKI1Ov8ptG9lxuUEjDFCYGG0hgyDfUxuNAIWI3GaDIxfreR/YMXPEOzEl4yORzFEu/ekh5gQvNT674x29BaA1iOKW1w5OJ7N1msJ76J66V4o1FQuFxZFO3U5nIx10eMe1VUezBIRrVO0YXMnCwA4Ckc9m1+5vEWhB78oLLSLgNhUcIwDyXyyb48TjeUO9TGCDECaadLLPsKSEiQ/fp5Nbo2VFrrx3IUMBJDxu6LiXAEZwQ8/cc8O//sT7t5ezu4joLNKGU8Sl6MSW5k2cee76g52MaA0Zt1x3G2viAMtkj5w1hJs8rsMA7+aty6tnbELWZe";
        byte[] base64EncodedSenderCert = publicKey.getBytes("UTF-8");

        X509Certificate signerCertificate =
                CryptUtil.generateX509Certificate(
                        Base64.decode(base64EncodedSenderCert));
        PublicKey signpublicKey = signerCertificate.getPublicKey();

        byte[] encryptKeyByte = encrypt((RSAKey)signpublicKey,
                encryptKey.getBytes("UTF-8"));
        String encryptKeyString = new String(Base64.encode(encryptKeyByte),
                "UTF-8");

        encryptBusiness = encryptBusiness + "@@" + encryptKeyString;

        return encryptBusiness;
    }

    private static byte[] encrypt(RSAKey key, byte[] data) {
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding", "BC");
            cipher.init(1, (Key)key);
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
            e.printStackTrace();
        }
        return null;
    }

    private static String signData(String data) {
        try {
            byte[] msg = data.getBytes("UTF-8");

            PrivateKey privateKey = getPrivateKey();
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(privateKey);
            signature.update(msg);
            byte[] bytarr = signature.sign();

            String signData = new String(Base64.encode(bytarr), "UTF-8");
            signData = signData.replaceAll("\r|\n", "");
            return signData;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PrivateKey getPrivateKey() throws Exception
    {
        String keyfile =  "D:\\signkey\\server_sign.key";
        byte[] base64EncodedPrivatekey = FileUtil.read4file(keyfile);

        String pwdfile =  "D:\\signkey\\server_sign.pwd";
        byte[] base64EncodedPrivatekeyPass = FileUtil.read4file(pwdfile);

        char[] keyPassword = new String(base64EncodedPrivatekeyPass, "UTF-8")
                .toCharArray();

        PrivateKey signerPrivatekey = CryptUtil.decryptPrivateKey(Base64.decode(base64EncodedPrivatekey), keyPassword);
        return signerPrivatekey;
    }

    private static PrivateKey getPrivateKeyForAes() throws Exception
    {
        String keyfile = "D:\\signkey\\server_cert.key";
        byte[] base64EncodedPrivatekey = FileUtil.read4file(keyfile);

        String pwdfile ="D:\\signkey\\server_cert.pwd";
        byte[] base64EncodedPrivatekeyPass = FileUtil.read4file(pwdfile);

        char[] keyPassword = new String(base64EncodedPrivatekeyPass, "UTF-8")
                .toCharArray();

        PrivateKey signerPrivatekey = CryptUtil.decryptPrivateKey(Base64.decode(base64EncodedPrivatekey), keyPassword);
        return signerPrivatekey;
    }

    public static void main(String[] args) {
        PayDto payDto = new PayDto();
        payDto.setMchno("105000086510414");
        payDto.setMerjnlno("2D00613110691K000999");
        payDto.setOrderno("2D00613110691K000236");
        payDto.setTranamt(0.01);
        payDto.setCurrency("156");
        payDto.setFronturl("http://192.168.19.8:90/egintra-fee/callBack/callBack");
        payDto.setNotifyurl("http://192.168.19.8:90/egintra-fee/callBack/callBack");
        payDto.setRisklevel("01");

        String signData = null;
        try {
            signData = BankAutographUtils.getSignData(payDto, "IFOPF001", "105000086510414", "安之畅收费系统");
            String encryptBody = encryptBusiness(signData);
//            //验签
            String sign = signData(signData);
            sign=java.net.URLEncoder.encode(sign, "UTF-8");
            JSONObject busijson = new JSONObject();
            busijson.put("encryptBody", encryptBody);
            busijson.put("sign", sign);
            String redirectURL = "https://ifop.citicbank.com/pay";
            JSONObject resultMsg = sendBusinesstoServer(redirectURL, busijson.toJSONString());
            System.out.println(resultMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
