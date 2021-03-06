package com.egintra.common.utils.bank;


import cfca.sadk.algorithm.common.Mechanism;
import cfca.sadk.algorithm.sm2.SM2PrivateKey;
import cfca.sadk.lib.crypto.bcsoft.BCSoftLib;
import cfca.sadk.x509.certificate.X509Cert;
import com.lsy.baselib.crypto.algorithm.SM2;
import com.lsy.baselib.crypto.exception.CipherUtilException;
import com.lsy.baselib.crypto.util.StoreUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.Signature;
import java.security.cert.CRLException;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.asn1.DERBMPString;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.ocsp.OCSPObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.x509.BasicConstraints;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.KeyUsage;
import org.bouncycastle.asn1.x509.X509Extension;
import org.bouncycastle.asn1.x509.X509Extensions;
import org.bouncycastle.asn1.x509.X509Name;
import org.bouncycastle.jce.PKCS10CertificationRequest;
import org.bouncycastle.jce.X509Principal;
import org.bouncycastle.jce.interfaces.PKCS12BagAttributeCarrier;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.ocsp.BasicOCSPResp;
import org.bouncycastle.ocsp.BasicOCSPRespGenerator;
import org.bouncycastle.ocsp.CertificateID;
import org.bouncycastle.ocsp.CertificateStatus;
import org.bouncycastle.ocsp.OCSPReq;
import org.bouncycastle.ocsp.OCSPReqGenerator;
import org.bouncycastle.ocsp.OCSPResp;
import org.bouncycastle.ocsp.OCSPRespGenerator;
import org.bouncycastle.x509.X509V3CertificateGenerator;
import org.bouncycastle.x509.extension.AuthorityKeyIdentifierStructure;
import org.bouncycastle.x509.extension.SubjectKeyIdentifierStructure;

public class CryptUtil {
    static {
        if (Security.getProvider("BC") == null)
            Security.addProvider(new BouncyCastleProvider());
    }

    public static KeyPair generateKeyPair(String algorithm, String length, String provider) {
        KeyPair kp = null;
        try {
            if ("SM2".equalsIgnoreCase(algorithm)) {
                kp = SM2.generateKeyPair();
            } else {
                KeyPairGenerator keyGen = null;
                if (provider != null)
                    keyGen = KeyPairGenerator.getInstance(algorithm, provider);
                else {
                    keyGen = KeyPairGenerator.getInstance(algorithm);
                }
                keyGen.initialize(Integer.valueOf(length).intValue());
                kp = keyGen.generateKeyPair();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kp;
    }

    public static SecretKey generateKey(String algorithm, String length, String provider) {
        Key sk = null;
        try {
            if ("SM4".equalsIgnoreCase(algorithm)) {
                sk = new BCSoftLib().generateKey(new Mechanism("SM4"));
            } else {
                KeyGenerator kg = null;
                if (provider != null)
                    kg = KeyGenerator.getInstance(algorithm, provider);
                else {
                    kg = KeyGenerator.getInstance(algorithm);
                }
                kg.init(Integer.valueOf(length).intValue());
                sk = kg.generateKey();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (SecretKey) sk;
    }

    public static java.security.cert.X509Certificate generateSelfSignedCertificate(KeyPair kp, String subject, byte[] serial, String algorithm, String validity, String provider)
            throws Exception {
        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
        certGen.reset();
        certGen.setSerialNumber(new BigInteger(serial));

        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(subject, ",");
        if (st.hasMoreElements()) {
            sb.append(st.nextElement());
        }
        while (st.hasMoreElements()) {
            sb.insert(0, ",");
            sb.insert(0, st.nextElement());
        }
        certGen.setIssuerDN(new X509Name(sb.toString()));

        certGen.setNotBefore(new Date(System.currentTimeMillis()));
        certGen.setNotAfter(
                new Date(System.currentTimeMillis() + 86400000L *
                        Integer.valueOf(validity).intValue()));
        certGen.setSubjectDN(new X509Name(sb.toString()));
        certGen.setPublicKey(kp.getPublic());
        certGen.setSignatureAlgorithm(algorithm);

        certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false,
                new SubjectKeyIdentifierStructure(kp.getPublic()));
        certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false,
                new AuthorityKeyIdentifierStructure(kp.getPublic()));
        certGen.addExtension(X509Extensions.BasicConstraints, false,
                new BasicConstraints(true));

        java.security.cert.X509Certificate cert = null;
        if (provider != null)
            cert = certGen.generate(kp.getPrivate(), provider,
                    new SecureRandom());
        else {
            cert = certGen.generate(kp.getPrivate(), new SecureRandom());
        }

        cert.checkValidity(new Date());
        cert.verify(kp.getPublic());

        PKCS12BagAttributeCarrier bagAttr = (PKCS12BagAttributeCarrier) cert;
        bagAttr.setBagAttribute(PKCSObjectIdentifiers.pkcs_9_at_friendlyName,
                new DERBMPString("CITIC"));

        return generateX509Certificate(cert.getEncoded());
    }

    public static PKCS10CertificationRequest generatePKCS10CertificateRequest(KeyPair kp, String userdn, String algorithm, String provider)
            throws Exception {
        PKCS10CertificationRequest certRequest = null;

        if (provider != null)
            certRequest = new PKCS10CertificationRequest(algorithm,
                    new X509Name(userdn), kp.getPublic(), null,
                    kp.getPrivate(), provider);
        else {
            certRequest = new PKCS10CertificationRequest(algorithm,
                    new X509Name(userdn), kp.getPublic(), null, kp.getPrivate());
        }

        return certRequest;
    }

    public static PKCS10CertificationRequest generatePKCS10CertificateRequest(byte[] data) throws Exception {
        return new PKCS10CertificationRequest(data);
    }

    public static java.security.cert.X509Certificate generateX509Certificate(String subject, PKCS10CertificationRequest request, java.security.cert.X509Certificate cacer, PrivateKey caprk, String serial, int days, String provider)
            throws Exception {
        X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();
        certGen.reset();
        certGen.setSerialNumber(new BigInteger(serial));

        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(cacer.getSubjectDN().getName()
                .toString(), ",");
        if (st.hasMoreElements()) {
            sb.append(st.nextElement());
        }
        while (st.hasMoreElements()) {
            sb.insert(0, ",");
            sb.insert(0, st.nextElement());
        }
        certGen.setIssuerDN(new X509Name(sb.toString()));

        certGen.setNotBefore(new Date(System.currentTimeMillis()));
        certGen.setNotAfter(
                new Date(System.currentTimeMillis() + 86400000L *
                        days));

        sb = new StringBuffer();
        st = new StringTokenizer(subject, ",");
        if (st.hasMoreElements()) {
            sb.append(st.nextElement());
        }
        while (st.hasMoreElements()) {
            sb.insert(0, ",");
            sb.insert(0, st.nextElement());
        }
        certGen.setSubjectDN(new X509Name(sb.toString()));

        certGen.setPublicKey(request.getPublicKey());
        certGen.setSignatureAlgorithm(cacer.getSigAlgName());

        certGen.addExtension(X509Extensions.SubjectKeyIdentifier, false,
                new SubjectKeyIdentifierStructure(request.getPublicKey()));
        certGen.addExtension(X509Extensions.AuthorityKeyIdentifier, false,
                new AuthorityKeyIdentifierStructure(cacer));
        certGen.addExtension(X509Extensions.BasicConstraints, false,
                new BasicConstraints(false));
        certGen.addExtension(X509Extensions.KeyUsage, false, new KeyUsage(184));

        java.security.cert.X509Certificate cert = null;
        if (provider != null)
            cert = certGen.generate(caprk, provider, new SecureRandom());
        else {
            cert = certGen.generate(caprk, new SecureRandom());
        }
        cert.checkValidity(new Date());
        cert.verify(cacer.getPublicKey());

        PKCS12BagAttributeCarrier bagAttr = (PKCS12BagAttributeCarrier) cert;
        bagAttr.setBagAttribute(PKCSObjectIdentifiers.pkcs_9_at_friendlyName,
                new DERBMPString("reserving ..."));
        return generateX509Certificate(cert.getEncoded());
    }

    public static PublicKey generatePublicKey(byte[] bytes, String keypairAlgorithm) throws Exception {
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
        KeyFactory keyFactory = KeyFactory.getInstance(keypairAlgorithm);
        PublicKey publickey = keyFactory.generatePublic(keySpec);

        return publickey;
    }

    public static PrivateKey generatePrivateKey(byte[] bytes, String keypairAlgorithm) throws Exception {
        PrivateKey privatekey = null;
        if ("RSA".equalsIgnoreCase(keypairAlgorithm)) {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
            KeyFactory keyFactory = KeyFactory.getInstance(keypairAlgorithm);
            privatekey = keyFactory.generatePrivate(keySpec);
        } else if ("SM2".equalsIgnoreCase(keypairAlgorithm)) {
            privatekey = SM2.generatePrivateKey(bytes);
        }

        return privatekey;
    }

    public static PrivateKey generatePrivateKey(InputStream is, String keypairAlgorithm) throws Exception {
        PrivateKey privatekey = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            byte[] data = new byte[63];
            while (is.read(data) > 0) {
                baos.write(data);
            }
            privatekey = generatePrivateKey(baos.toByteArray(),
                    keypairAlgorithm);
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (baos != null)
                    baos.close();
            } catch (Exception localException1) {
            }
            try {
                if (baos != null)
                    baos.close();
            } catch (Exception localException2) {
            }
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (Exception localException3) {
            }
        }
        return privatekey;
    }

    public static java.security.cert.X509Certificate generateX509Certificate(byte[] derX509Crt) throws Exception {
        java.security.cert.X509Certificate x509Crt = null;

        if (derX509Crt == null) {
            throw new Exception(
                    "generateX509Certificate parameter derX509Crt is null");
        }

        ByteArrayInputStream bais = null;
        try {
            X509Cert cfcaCrt = new X509Cert(derX509Crt);
            String signatureAlgrithm = cfcaCrt.getSignatureAlgName();
            if (signatureAlgrithm.indexOf("RSA") > 0) {
                bais = new ByteArrayInputStream(derX509Crt);
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                x509Crt = (java.security.cert.X509Certificate) cf
                        .generateCertificate(bais);
            } else if (signatureAlgrithm.indexOf("SM2") > 0) {
                x509Crt = new com.lsy.baselib.crypto.protocol.X509Certificate(
                        cfcaCrt);
            }
        } finally {
            try {
                if (bais != null)
                    bais.close();
            } catch (Exception localException) {
            }
        }
        return x509Crt;
    }

    public static java.security.cert.X509Certificate generateX509Certificate(InputStream isX509Crt) throws Exception {
        java.security.cert.X509Certificate x509Crt = null;

        if (isX509Crt == null) {
            throw new Exception(
                    "generateX509Certificate parameter isX509Crt is null");
        }

        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            byte[] data = new byte[63];
            while (isX509Crt.read(data) > 0) {
                baos.write(data);
            }
            x509Crt = generateX509Certificate(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (baos != null)
                    baos.close();
            } catch (Exception localException1) {
            }
            try {
                if (baos != null)
                    baos.close();
            } catch (Exception localException2) {
            }
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (Exception localException3) {
            }
        }
        return x509Crt;
    }

    public static byte[] generatePKCS12(Certificate[] chain, PrivateKey privatekey, String alias, char[] keypassword, char[] keystorepassword, String provider)
            throws Exception {
        KeyStore store = KeyStore.getInstance("PKCS12", provider);
        store.load(null, null);
        if (privatekey == null)
            store.setCertificateEntry(alias, chain[0]);
        else {
            store.setKeyEntry(alias, privatekey, keypassword, chain);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        store.store(baos, keystorepassword);

        return baos.toByteArray();
    }

    public static byte[] generateJKS(Certificate[] chain, PrivateKey privatekey, String alias, char[] keypassword, char[] keystorepassword, String provider)
            throws Exception {
        KeyStore store = KeyStore.getInstance("JKS");
        store.load(null, null);
        if (privatekey == null) {
            store.setCertificateEntry(alias, chain[0]);
        } else {
            for (int i = 0; i < chain.length; i++) {
                System.out.println("chain[" +
                        i +
                        "] [" +
                        ((java.security.cert.X509Certificate) chain[i])
                                .getSubjectDN().getName() + "]");
            }
            store.setKeyEntry(alias, privatekey, keypassword, chain);
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        store.store(baos, keystorepassword);

        return baos.toByteArray();
    }

    public static KeyStore generatePKCS12(byte[] bytes, char[] password, String alias, String provider) throws Exception {
        KeyStore store = KeyStore.getInstance("PKCS12", provider);
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        store.load(bais, password);

        return store;
    }

    public static KeyStore generateJKS(byte[] bytes, char[] password, String alias, String provider) throws Exception {
        KeyStore store = KeyStore.getInstance("JKS");
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        store.load(bais, password);

        return store;
    }

    public static OCSPReq generateOCSPRequest(java.security.cert.X509Certificate reqcert, java.security.cert.X509Certificate cacert, java.security.cert.X509Certificate[] chain, PrivateKey privatekey, String requestorDN, boolean flag, String provider)
            throws Exception {
        OCSPReqGenerator gen = new OCSPReqGenerator();
        CertificateID id = new CertificateID("1.3.14.3.2.26", cacert,
                reqcert.getSerialNumber());
        gen.addRequest(id);

        if (requestorDN != null) {
            gen.setRequestorName(
                    new GeneralName(4,
                            new X509Principal(requestorDN)));
        }

        if (flag) {
            Vector oids = new Vector();
            Vector values = new Vector();

            oids.addElement(OCSPObjectIdentifiers.id_pkix_ocsp_nonce);
            values.addElement(
                    new X509Extension(false,
                            new DEROctetString(new byte[16])));
            gen.setRequestExtensions(new X509Extensions(oids, values));
        }

        OCSPReq req = null;
        if (privatekey != null)
            req = gen.generate(chain[0].getSigAlgName(), privatekey, chain,
                    provider);
        else {
            req = gen.generate();
        }
        return req;
    }

    public static OCSPReq generateOCSPRequest(byte[] bytes) throws Exception {
        return new OCSPReq(bytes);
    }

    public static OCSPResp generateOCSPResponse(int respstatus, CertificateID certID, CertificateStatus certstatus, java.security.cert.X509Certificate[] chain, PrivateKey pk, String provider)
            throws Exception {
        BasicOCSPRespGenerator brespgen = new BasicOCSPRespGenerator(
                chain[0].getPublicKey());
        brespgen.addResponse(certID, null);
        BasicOCSPResp bresp = brespgen.generate(chain[0].getSigAlgName(), pk,
                chain, new Date(System.currentTimeMillis()), provider);

        OCSPRespGenerator ocspgen = new OCSPRespGenerator();
        return ocspgen.generate(respstatus, bresp);
    }

    public static OCSPResp generateOCSPResponse(byte[] bytes) throws Exception {
        return new OCSPResp(bytes);
    }

    public static X509CRL generateX509CRL(byte[] data) throws CRLException {
        X509CRL crl = null;
        ByteArrayInputStream bais = null;
        if (data != null) {
            CertificateFactory cf = null;
            try {
                cf = CertificateFactory.getInstance("X.509");
                bais = new ByteArrayInputStream(data);
                crl = (X509CRL) cf.generateCRL(bais);
            } catch (Exception e) {
                e.printStackTrace();
                try {
                    if (bais != null)
                        bais.close();
                } catch (Exception localException1) {
                }
                try {
                    if (bais != null)
                        bais.close();
                } catch (Exception localException2) {
                }
            } finally {
                try {
                    if (bais != null)
                        bais.close();
                } catch (Exception localException3) {
                }
            }
        }
        return crl;
    }

    public static boolean checkValidity(java.security.cert.X509Certificate user, java.security.cert.X509Certificate issuer) {
        boolean result = false;

        Date datenow = new Date();
        try {
            user.checkValidity(datenow);
            user.verify(issuer.getPublicKey());
            result = true;
        } catch (CertificateExpiredException e) {
            System.out.println("Expired");
        } catch (CertificateNotYetValidException e) {
            System.out.println("Too early");
        } catch (Exception e) {
            System.out.println("not valid");
        }

        return result;
    }

    public static byte[] digest(byte[] message, String algorithm) throws Exception {
        MessageDigest messageDigest =
                MessageDigest.getInstance(algorithm, "BC");

        messageDigest.update(message);
        return messageDigest.digest();
    }

    public static byte[] encryptPrivateKey(PrivateKey privatekey, char[] password) throws CipherUtilException {
        byte[] encryptedData = (byte[]) null;

        if ((privatekey == null) || (password == null)) {
            throw new CipherUtilException("encryptPrivateKey parameter is null");
        }
        ByteArrayOutputStream baos = null;
        try {
            byte[] salt = new byte[8];
            Random random = new Random();
            random.nextBytes(salt);

            String pbeAlg = password.length < 8 ? "PBEWithSHAAndTwofish-CBC" :
                    "PBEWithMD5AndDES";
            PBEKeySpec keySpec = new PBEKeySpec(password);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(pbeAlg);
            SecretKey key = keyFactory.generateSecret(keySpec);

            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 1000);
            Cipher cipher = Cipher.getInstance(pbeAlg);
            cipher.init(1, key, paramSpec);

            byte[] keyBts = "SM2".equalsIgnoreCase(privatekey.getAlgorithm()) ? ((SM2PrivateKey) privatekey)
                    .getDByBytesWithPublicKey() : privatekey.getEncoded();
            byte[] encryptedPrivatekey = cipher.doFinal(keyBts);

            baos = new ByteArrayOutputStream();
            byte[] algorithm = privatekey.getAlgorithm().getBytes();
            baos.write((byte) algorithm.length);
            baos.write(algorithm);
            baos.write(salt);
            baos.write(encryptedPrivatekey);
            encryptedData = baos.toByteArray();
        } catch (Exception e) {
            throw new CipherUtilException("????????????????????????", e);
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (Exception localException1) {
            }
        }
        return encryptedData;
    }

    public static PrivateKey decryptPrivateKey(byte[] encryptedPrivatekey, char[] password) throws CipherUtilException {
        return decryptPrivateKey(new ByteArrayInputStream(encryptedPrivatekey),
                password);
    }

    public static PrivateKey decryptPrivateKey(InputStream isEncryptedPrivatekey, char[] password)
            throws CipherUtilException {
        PrivateKey privatekey = null;
        try {
            byte[] salt = new byte[8];
            byte[] algorithm = (byte[]) null;
            int l = 0;
            int size = 0;

            size = isEncryptedPrivatekey.available();

            isEncryptedPrivatekey.read(salt, 0, 1);
            l = salt[0];
            algorithm = new byte[l];
            isEncryptedPrivatekey.read(algorithm, 0, l);

            isEncryptedPrivatekey.read(salt, 0, 8);

            byte[] remainingCipherData = new byte[size - 8 - 1 - l];
            isEncryptedPrivatekey
                    .read(remainingCipherData, 0, size - 8 - 1 - l);

            String pbeAlg = password.length < 8 ? "PBEWithSHAAndTwofish-CBC" :
                    "PBEWithMD5AndDES";

            PBEKeySpec keySpec = new PBEKeySpec(password);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(pbeAlg);
            SecretKey key = keyFactory.generateSecret(keySpec);
            PBEParameterSpec paramSpec = new PBEParameterSpec(salt, 1000);
            Cipher cipher = Cipher.getInstance(pbeAlg);
            cipher.init(2, key, paramSpec);
            byte[] keyBts = cipher.doFinal(remainingCipherData);

            privatekey = generatePrivateKey(keyBts, new String(algorithm));
        } catch (Exception e) {
            throw new CipherUtilException("???????????????????????????????????????????????????", e);
        } finally {
            try {
                if (isEncryptedPrivatekey != null)
                    isEncryptedPrivatekey.close();
            } catch (Exception localException1) {
            }
        }
        return privatekey;
    }

    public static void encrypt(String infile, String outfile, PublicKey publickey, String provider) throws Exception {
        if ((infile == null) || (infile.equals("")) || (outfile == null) ||
                (outfile.equals("")))
            return;
        boolean flag = false;
        if (infile.equals(outfile)) {
            outfile = "~.tmp";
            flag = true;
        }

        DataOutputStream output = null;
        FileInputStream input = null;
        CipherOutputStream cos = null;
        try {
            output = new DataOutputStream(new FileOutputStream(outfile));

            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding",
                    provider);
            rsaCipher.init(1, publickey);

            KeyGenerator rijndaelKeyGenerator = KeyGenerator.getInstance(
                    "Rijndael", provider);
            rijndaelKeyGenerator.init(128);
            Key rijndaelKey = rijndaelKeyGenerator.generateKey();

            byte[] encodedKeyBytes = rsaCipher
                    .doFinal(rijndaelKey.getEncoded());
            output.writeInt(encodedKeyBytes.length);
            output.write(encodedKeyBytes);

            SecureRandom random = new SecureRandom();
            byte[] iv = new byte[16];
            random.nextBytes(iv);
            output.write(iv);

            IvParameterSpec spec = new IvParameterSpec(iv);
            Cipher symmetricCipher = Cipher.getInstance(
                    "Rijndael/CBC/PKCS5Padding", provider);
            symmetricCipher.init(1, rijndaelKey, spec);
            cos = new CipherOutputStream(output, symmetricCipher);

            input = new FileInputStream(infile);
            int theByte = 0;
            while ((theByte = input.read()) != -1)
                cos.write(theByte);
        } finally {
            try {
                if (input != null)
                    input.close();
            } catch (Exception localException) {
                try {
                    if (cos != null)
                        cos.close();
                } catch (Exception localException1) {
                }
                try {
                    if (cos != null)
                        cos.close();
                } catch (Exception localException2) {
                }
            } finally {
                try {
                    if (cos != null)
                        cos.close();
                } catch (Exception localException3) {
                }
            }
        }
        if (flag) {
            File f = new File(infile);
            f.delete();
            f = new File(outfile);
            f.renameTo(new File(infile));
        }
    }

    public static byte[] encrypt(byte[] data, PublicKey publickey, String provider) throws Exception {
        ByteArrayOutputStream output = null;
        DataOutputStream dos = null;
        CipherOutputStream cos = null;
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding",
                    provider);
            rsaCipher.init(1, publickey);

            KeyGenerator rijndaelKeyGenerator = KeyGenerator.getInstance(
                    "Rijndael", provider);
            rijndaelKeyGenerator.init(128);
            Key rijndaelKey = rijndaelKeyGenerator.generateKey();

            byte[] encodedKeyBytes = rsaCipher
                    .doFinal(rijndaelKey.getEncoded());
            output = new ByteArrayOutputStream();
            dos = new DataOutputStream(output);
            dos.writeInt(encodedKeyBytes.length);
            dos.write(encodedKeyBytes);

            SecureRandom random = new SecureRandom();
            byte[] iv = new byte[16];
            random.nextBytes(iv);
            dos.write(iv);

            IvParameterSpec spec = new IvParameterSpec(iv);
            Cipher symmetricCipher = Cipher.getInstance(
                    "Rijndael/CBC/PKCS5Padding", provider);
            symmetricCipher.init(1, rijndaelKey, spec);
            cos = new CipherOutputStream(dos, symmetricCipher);
            cos.write(data);
        } finally {
            try {
                if (cos != null)
                    cos.close();
            } catch (Exception localException) {
            }
        }
        return output.toByteArray();
    }

    public static void decrypt(String infile, String outfile, PrivateKey privatekey, String provider) throws Exception {
        if ((infile == null) || (infile.equals("")) || (outfile == null) ||
                (outfile.equals("")))
            return;
        boolean flag = false;
        if (infile.equals(outfile)) {
            outfile = "~.tmp";
            flag = true;
        }

        DataInputStream dis = null;
        CipherInputStream cis = null;
        FileOutputStream fos = null;
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding",
                    provider);
            rsaCipher.init(2, privatekey);

            dis = new DataInputStream(new FileInputStream(infile));

            byte[] encryptedKeyBytes = new byte[dis.readInt()];
            dis.readFully(encryptedKeyBytes);
            byte[] rijdaelKeyBytes = rsaCipher.doFinal(encryptedKeyBytes);
            SecretKey rijndaelKey = new SecretKeySpec(rijdaelKeyBytes,
                    "Rijndael");

            byte[] iv = new byte[16];
            dis.read(iv);

            IvParameterSpec spec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("Rijndael/CBC/PKCS5Padding",
                    provider);
            cipher.init(2, rijndaelKey, spec);
            cis = new CipherInputStream(dis, cipher);

            fos = new FileOutputStream(outfile);
            int theByte = 0;
            while ((theByte = cis.read()) != -1)
                fos.write(theByte);
        } finally {
            try {
                if (cis != null)
                    cis.close();
            } catch (Exception localException) {
                try {
                    if (fos != null)
                        fos.close();
                } catch (Exception localException1) {
                }
                try {
                    if (fos != null)
                        fos.close();
                } catch (Exception localException2) {
                }
            } finally {
                try {
                    if (fos != null)
                        fos.close();
                } catch (Exception localException3) {
                }
            }
        }
        if (flag) {
            File f = new File(infile);
            f.delete();
            f = new File(outfile);
            f.renameTo(new File(infile));
        }
    }

    public static byte[] decrypt(byte[] data, PrivateKey privatekey, String provider) throws Exception {
        ByteArrayInputStream is = null;
        DataInputStream dis = null;
        CipherInputStream cis = null;
        ByteArrayOutputStream os = null;
        try {
            Cipher rsaCipher = Cipher.getInstance("RSA/ECB/PKCS1Padding",
                    provider);
            rsaCipher.init(2, privatekey);
            is = new ByteArrayInputStream(data);
            dis = new DataInputStream(is);

            byte[] encryptedKeyBytes = new byte[dis.readInt()];
            dis.readFully(encryptedKeyBytes);
            byte[] rijdaelKeyBytes = rsaCipher.doFinal(encryptedKeyBytes);
            SecretKey rijndaelKey = new SecretKeySpec(rijdaelKeyBytes,
                    "Rijndael");

            byte[] iv = new byte[16];
            dis.read(iv);

            IvParameterSpec spec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance("Rijndael/CBC/PKCS5Padding",
                    provider);
            cipher.init(2, rijndaelKey, spec);
            cis = new CipherInputStream(dis, cipher);

            os = new ByteArrayOutputStream();
            int theByte = 0;
            while ((theByte = cis.read()) != -1)
                os.write(theByte);
        } finally {
            try {
                if (cis != null)
                    cis.close();
            } catch (Exception localException) {
            }
        }
        return os.toByteArray();
    }

    public static byte[] digest(byte[] message, String algorithm, String provider) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm,
                provider);

        messageDigest.update(message);
        return messageDigest.digest();
    }

    public static byte[] sign(byte[] data, PrivateKey privatekey, String algorithm, boolean attach, String provider) throws Exception {
        Signature sign = Signature.getInstance(algorithm, provider);
        sign.initSign(privatekey);
        sign.update(data);
        byte[] signature = sign.sign();

        if (!attach) {
            return signature;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        dos.writeInt(algorithm.length());
        dos.write(algorithm.getBytes());
        dos.writeInt(signature.length);
        dos.write(signature);
        dos.writeInt(data.length);
        dos.write(data);
        dos.close();
        return baos.toByteArray();
    }

    public static boolean verify(byte[] data, PublicKey publickey, String provider) throws Exception {
        DataInputStream dis = new DataInputStream(
                new ByteArrayInputStream(data));

        byte[] algorithm = new byte[dis.readInt()];
        dis.readFully(algorithm);

        byte[] signature = new byte[dis.readInt()];
        dis.readFully(signature);

        byte[] source = new byte[dis.readInt()];
        dis.readFully(source);

        return verify(source, signature, publickey, new String(algorithm),
                provider);
    }

    public static boolean verify(byte[] source, byte[] signature, PublicKey publickey, String algorithm, String provider)
            throws Exception {
        Signature sign = Signature.getInstance(algorithm, provider);
        sign.initVerify(publickey);
        sign.update(source);
        return sign.verify(signature);
    }

    public static byte[] getCertFromRSAPfx(byte[] pfxData, char[] password) throws Exception {
        byte[] result = (byte[]) null;
        KeyStore ks =
                StoreUtil.loadFromStore(pfxData, password, "PKCS12", "BC");
        String sAlias = null;
        Enumeration eAlias = ks.aliases();
        if (eAlias.hasMoreElements())
            sAlias = (String) eAlias.nextElement();
        result = ks.getCertificate(sAlias).getEncoded();
        return result;
    }
}