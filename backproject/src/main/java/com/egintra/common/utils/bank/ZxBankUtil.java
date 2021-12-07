package com.egintra.common.utils.bank;

import com.alibaba.fastjson.JSONObject;
import com.citicbank.cbframework.common.security.CBRSA;
import com.citicbank.cbframework.common.util.CBConverter;
import com.lsy.baselib.crypto.protocol.PKCS7Signature;
import com.lsy.baselib.crypto.util.Base64;
import com.lsy.baselib.crypto.util.FileUtil;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.crypto.Cipher;

public class ZxBankUtil
{
    public Boolean verifySign(byte[] msg, String sign, String sender_cer_file_nm)
            throws Exception
    {
        byte[] base64EncodedSenderCert = (byte[])null;
        try {
            base64EncodedSenderCert = FileUtil.read4file(sender_cer_file_nm);
        }
        catch (Exception e) {
            System.out.println("异步通知验签：" + e.getMessage());
        }
        X509Certificate signerCertificate = null;
        try {
            signerCertificate = CryptUtil.generateX509Certificate(Base64.decode(base64EncodedSenderCert));
        } catch (Exception e) {
            System.out.println("异步通知验签：" + e.getMessage());
        }
        PublicKey senderPubKey = signerCertificate.getPublicKey();
        return Boolean.valueOf(PKCS7Signature.verifyDetachedSignature(msg, Base64.decode(sign.getBytes()), senderPubKey));
    }



    public String encryptBusiness(String path, String business) throws Exception
    {
        String encryptKey = AESUtil.getRandomAESKey();
        byte[] encryptBusinessDataByte = AESUtil.encrypt(business, encryptKey);

        String encryptBusiness = new String(
                Base64.encode(encryptBusinessDataByte), "UTF-8");
        String publicKeyFile = path + "signkey/ifoppre_encrypt.crt";
        byte[] base64EncodedSenderCert = FileUtil.read4file(publicKeyFile);

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

    public static Boolean verifySign(String path, byte[] msg, String signData)
            throws Exception
    {
        String publicKeyFile = path + "signkey/snsd.cer";
        byte[] base64EncodedSenderCert = FileUtil.read4file(publicKeyFile);
        X509Certificate signerCertificate = CryptUtil.generateX509Certificate(
                Base64.decode(base64EncodedSenderCert));
        PublicKey signpublicKey = signerCertificate.getPublicKey();
        Signature signature = Signature.getInstance("SHA1WithRSA");
        signature.initVerify(signpublicKey);
        signature.update(msg);
        return Boolean.valueOf(signature.verify(Base64.decode(signData.getBytes("UTF-8"))));
    }

    public JSONObject sendBusinesstoServer(String urlstr, String business, String path)
    {
        JSONObject resultMsg = new JSONObject();
        try {
            URL url = new URL(urlstr);
            System.out.println("国安0");
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(5000);
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setUseCaches(false);
            conn.setInstanceFollowRedirects(true);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            System.out.println("国安1222" + urlstr + business + path);

            System.out.println("国安1" + urlstr + business + path);
            OutputStream os = conn.getOutputStream();
            os.write(business.getBytes("UTF-8"));
            os.flush();
            os.close();
            System.out.println("国安1");
            InputStream is = conn.getInputStream();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(is, "utf-8"));
            String respstr = null;
            StringBuilder sbr = new StringBuilder();
            while ((respstr = br.readLine()) != null) {
                sbr.append(respstr);
            }
            br.close();
            is.close();
            System.out.println("国安2");
            String responString = sbr.toString();
            System.out.println(responString);

            JSONObject jsonObject = JSONObject.parseObject(responString);

            String respbusiness = jsonObject.getString("encryptBody");
            String signStr = jsonObject.getString("sign");

            String[] encryptBusinessDataArr = respbusiness.split("@@");
            String responencryptKey = encryptBusinessDataArr[1];

            RSAPrivateKey privateKey = (RSAPrivateKey)getPrivateKeyForAes(path);
            byte[] keyByte = CBRSA.decrypt(privateKey, CBConverter.base64ToBytes(responencryptKey));
            String encryptKeystr = new String(keyByte, "UTF-8");
            System.out.println(encryptKeystr);
            byte[] decryptedBusinessDataBytes = AESUtil.decrypt(CBConverter.base64ToBytes(encryptBusinessDataArr[0]), encryptKeystr);
            String decryptedBusinessData = new String(decryptedBusinessDataBytes, "UTF-8");

            boolean issign = verifySign(path, decryptedBusinessData.getBytes("UTF-8"), signStr).booleanValue();
            System.out.println("sign is " + issign);

            resultMsg.put("decryptedBusinessData", decryptedBusinessData);
            resultMsg.put("issign", Boolean.valueOf(issign));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("调用银行" + ex.getMessage());
        }
        return resultMsg;
    }

    public void ff(String path)
    {
        String respbusiness = "0iFVy3lAOpVy5Zlstd+Wahx3TRmRUI9u3HEFwLm7F9wfyal77QHopKXImNSKnXoWy7uZjfwI4tgnksAnjJmYOpzm0ygMfcxTqTiJ7vq83s4=@@Xv5prt7gsFg2yiRFQ3xTwvDdGD8/f3yH9kQdBGctgmAJs10X4HWiIljLX4Yf5oWTzm0qVtD/IDVWdLpANDYZAKnBhCjrrECe4R+nNImSrnd3rDKrmlc3Oiyr48B5qtngHhDOti0e12iD2WbeCXe3U0UDDRS0LH+mggThUbXy/Qb5A/KofZ5Fec7hzY5rwHY7dYMhhOw/jiryLEhZxK8qffSJ7GupYuPeVGOB648otH3S2wBeC1fENBLsvICuPaSK1JlRq61+DazBzxlLMHHkYhw+EzL/r3WFRrP+cy+NhiIh0ENZdG0t8iR5J8KkyY0G0SXqfRBEXxzxQoY9C4jlQg==";

        String[] encryptBusinessDataArr = respbusiness.split("@@");
        String responencryptKey = encryptBusinessDataArr[1];
        try
        {
            RSAPrivateKey privateKey = (RSAPrivateKey)getPrivateKeyForAes(path);

            byte[] keyByte = CBRSA.decrypt(privateKey, CBConverter.base64ToBytes(responencryptKey));
            String encryptKeystr = new String(keyByte, "UTF-8");
            System.out.println(encryptKeystr);
            byte[] decryptedBusinessDataBytes = AESUtil.decrypt(CBConverter.base64ToBytes(encryptBusinessDataArr[0]), encryptKeystr);
            String decryptedBusinessData = new String(decryptedBusinessDataBytes, "UTF-8");
            System.out.println(decryptedBusinessData);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private PrivateKey getPrivateKeyForAes(String path)
            throws Exception
    {
        String keyfile = path + "signkey/server_sign.key";
        byte[] base64EncodedPrivatekey = FileUtil.read4file(keyfile);

        String pwdfile = path + "signkey/server_sign.pwd";
        byte[] base64EncodedPrivatekeyPass = FileUtil.read4file(pwdfile);

        char[] keyPassword = new String(base64EncodedPrivatekeyPass, "UTF-8")
                .toCharArray();

        PrivateKey signerPrivatekey = CryptUtil.decryptPrivateKey(Base64.decode(base64EncodedPrivatekey), keyPassword);
        return signerPrivatekey;
    }
    private byte[] encrypt(RSAKey key, byte[] data) {
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

    public String signData(String data, String path) {
        try {
            byte[] msg = data.getBytes("UTF-8");

            PrivateKey privateKey = getPrivateKey(path);
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

    public PrivateKey getPrivateKey(String path)
            throws Exception
    {
        String keyfile = path + "signkey/server_cert.key";

        byte[] base64EncodedPrivatekey = FileUtil.read4file(keyfile);

        String pwdfile = path + "signkey/server_cert.pwd";
        byte[] base64EncodedPrivatekeyPass = FileUtil.read4file(pwdfile);

        char[] keyPassword = new String(base64EncodedPrivatekeyPass, "UTF-8")
                .toCharArray();

        PrivateKey signerPrivatekey = CryptUtil.decryptPrivateKey(Base64.decode(base64EncodedPrivatekey), keyPassword);
        return signerPrivatekey;
    }
}