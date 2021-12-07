package com.egintra.common.utils.bank;

import com.alibaba.fastjson.JSONObject;
import com.citicbank.cbframework.common.security.CBRSA;
import com.citicbank.cbframework.common.util.CBBase64;
import com.egintra.common.utils.DateUtils;
import com.lsy.baselib.crypto.util.Base64;
import com.lsy.baselib.crypto.util.FileUtil;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 对接参数转化为json格式
 *
 * @author liushihao
 * @date 2021/8/12
 */
public class BankAutographUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAutographUtils.class);

    /**
     * 银行对接接口签名验签明文处理，json格式
     * 基础字段的json格式
     *
     * @param opentransCode 填写对应接口交易码，例如：IFOPF001
     * @param openmerCode   中信银行开放银行分配的合作方代码（需分配）
     * @param openmerName   上送合作方名称
     * @return 结果
     */
    public static JSONObject baseJson(String opentransCode, String openmerCode, String openmerName) {
        JSONObject object = new JSONObject();
        object.put("OPENTRANSCODE", "IFOPF001");
        object.put("OPENMERCODE", "CITICIFOP0000008");
        object.put("OPENMERNAME", "测试商户");
        object.put("OPENVER", "1.0.0");
        object.put("OPENBUSITYPE", "105");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        object.put("OPENLAUNCHDATE", sdf.format(new Date()));
        SimpleDateFormat sdf1 = new SimpleDateFormat("HHmmss");
        object.put("OPENLAUNCHTIME", sdf1.format(new Date()));
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        object.put("OPENMERFLOWID", uuid + sdf2.format(new Date()));

        return object;
    }

    /**
     * 银行对接接口签名验签明文处理，json格式
     * 基础字段的上添加响应接口特需其他字段
     *
     * @param bean          数据对象
     * @param opentransCode 填写对应接口交易码，例如：IFOPF001
     * @param openmerCode   中信银行开放银行分配的合作方代码（需分配）
     * @param openmerName   上送合作方名称
     * @return 结果
     * @throws Exception
     */
    public static String getSignData(Object bean, String opentransCode, String openmerCode, String openmerName)
            throws Exception {
        // 获得基础字段内容
        JSONObject object = baseJson(opentransCode, openmerCode, openmerName);

        // 开始添加具体接口所需字段
        Map p = BeanUtils.describe(bean);
        Set s = p.keySet();
        Iterator i = s.iterator();
        while (i.hasNext()) {
            Object key = i.next();
            if (key.equals("class")) {
            } else {
                Object value = p.get(key);
                object.put(key.toString(), value);
            }
        }

        // 转String
        String signData = object.toString();

        System.out.println(signData);
        // 返回
        return signData;
    }

    /**
     * 签名明文数据得到签名密文
     *
     * @param data json
     * @return 结果
     */
    public static String getSignatureSecrets(String data) {

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

    public static PrivateKey getPrivateKey()
            throws Exception {
        String keyfile = "D:\\signkey\\server_sign.key";

        byte[] base64EncodedPrivatekey = FileUtil.read4file(keyfile);

        String pwdfile = "D:\\signkey/server_sign.pwd";
        byte[] base64EncodedPrivatekeyPass = FileUtil.read4file(pwdfile);

        char[] keyPassword = new String(base64EncodedPrivatekeyPass, "UTF-8")
                .toCharArray();

        PrivateKey signerPrivatekey = CryptUtil.decryptPrivateKey(Base64.decode(base64EncodedPrivatekey), keyPassword);
        return signerPrivatekey;
    }

    /**
     * 验证签名数据
     *
     * @param signData json格式数据
     * @param sign     签名明文数据得到签名密文
     * @return 结果
     */
    public static Boolean checkSign(String signData, String sign) {
        try {
            //公钥文件路径
            String publicKeyFile = "/usr/cert/publicKeyCert.crt";
            //或者：publicKeyString.getBytes("UTF-8");
            byte[] base64EncodedPublickey = FileUtil.read4file(publicKeyFile);
            Boolean isSign;
            X509Certificate signerCertificate = CryptUtil.generateX509Certificate(Base64.decode(base64EncodedPublickey));
            PublicKey signpublicKey = signerCertificate.getPublicKey();
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initVerify(signpublicKey);
            signature.update(signData.getBytes("UTF-8"));
            isSign = signature.verify(Base64.decode(sign.getBytes("UTF-8")));
            return isSign;
        } catch (Exception e) {
            return false;
        }


    }

    /**
     * 银行数据加密
     *
     * @return 结果
     */
    public static String getEncodeData(String data) {
        // String data = "xml格式或json格式的业务报文数据，对整个报文加解密";
        String encryptBusiness;
        try {
            String encryptKey = AESUtil.getRandomAESKey();
            byte[] encryptBusinessDataByte = AESUtil.encrypt(data, encryptKey);
            encryptBusiness = new String(CBBase64.encode(encryptBusinessDataByte), "UTF-8");

            //公钥文件路径
            String publicKeyFile = "D:\\signkey\\ifoppre_encrypt.crt";
            byte[] base64EncodedPublickey = FileUtil.read4file(publicKeyFile);   //或者：publicKeyString.getBytes("UTF-8");

            X509Certificate signerCertificate = CryptUtil.generateX509Certificate(com.lsy.baselib.crypto.util.Base64.decode(base64EncodedPublickey));
            PublicKey signpublicKey = signerCertificate.getPublicKey();

            byte[] encryptKeyByte = CBRSA.encrypt((RSAKey) signpublicKey, encryptKey.getBytes("UTF-8"));
            String encryptKeyString = new String(CBBase64.encode(encryptKeyByte), "UTF-8");

            //加密后的报文，放入encryptBody
            encryptBusiness = encryptBusiness + "@@" + encryptKeyString;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return encryptBusiness;
    }

    public static String encryptBusiness(String business) throws Exception {
        String encryptKey = AESUtil.getRandomAESKey();
        byte[] encryptBusinessDataByte = AESUtil.encrypt(business, encryptKey);

        String encryptBusiness = new String(
                Base64.encode(encryptBusinessDataByte), "UTF-8");
        String publicKeyFile = "D:\\signkey\\ifoppre_encrypt.crt";
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
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 银行数据解密
     *
     * @return 结果
     */
    public static String getDecrypt(String encryptBusiness) {

        String decryptedBusinessData = null;
        String[] encryptBusinessDataArr = encryptBusiness.split("@@");
        String encryptKey = encryptBusinessDataArr[1];
        try {
            //私钥文件路径
            String keyfile = "/usr/ cert/privateKeyCert.key";
            byte[] base64EncodedPrivatekey = FileUtil.read4file(keyfile);
            //私钥密码文件路径
            String pwdfile = "/usr/cert/privateKeyCert.pwd";
            byte[] base64EncodedPrivatekeyPass = FileUtil.read4file(pwdfile);
            char[] keyPassword = new String(base64EncodedPrivatekeyPass, "UTF-8").toCharArray();
            RSAPrivateKey privateKey = (RSAPrivateKey) CryptUtil.decryptPrivateKey(
                    Base64.decode(base64EncodedPrivatekey), keyPassword);
            byte[] keyByte = CBRSA.decrypt(privateKey, CBBase64.decode(encryptKey.getBytes("UTF-8")));
            String encryptKeystr = new String(keyByte, "UTF-8");

            byte[] decryptedBusinessDataBytes = AESUtil.decrypt(CBBase64.decode(
                    encryptBusinessDataArr[0].getBytes("UTF-8")), encryptKeystr);
            //得到解密后的明文字符串
            decryptedBusinessData = new String(decryptedBusinessDataBytes, "UTF-8");
        } catch (Exception e) {
            LOGGER.error(String.valueOf(e.getStackTrace()), e);
        }

        return decryptedBusinessData;
    }


}
