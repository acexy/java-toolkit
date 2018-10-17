package com.thankjava.toolkit.core.encrypit;

import com.thankjava.toolkit.bean.encrypit.RSAKeys;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * 基于JDK 的 rsa 算法
 * <p>Function: RSA</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年8月10日 下午4:57:19
 */
public final class RSA {

    private static final String algorithm = "RSA";
    private static final String signAlgorithm = "SHA1WithRSA";

    /**
     * RSA 公密钥对生成
     * <p>Function: keyGen</p>
     * <p>Description: </p>
     *
     * @param keySize
     * @return Key数组  index = 0 公钥 | index = 1 私钥
     * @throws NoSuchAlgorithmException
     * @throws FileNotFoundException
     * @throws IOException
     * @author acexy@thankjava.com
     * @date 2016年8月10日 下午5:31:36
     * @version 1.0
     */
    public static RSAKeys keyGen(int keySize) {

        KeyPairGenerator generator = null;
        try {
            generator = KeyPairGenerator.getInstance(algorithm);
            generator.initialize(keySize);

            KeyPair keyPair = generator.generateKeyPair();

            return new RSAKeys(keyPair.getPublic(), keyPair.getPrivate());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 对内容进行加签
     * <p>Function: encrypt</p>
     * <p>Description: </p>
     *
     * @param contentByteArray 明文
     * @param privateKey       私钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     * @author acexy@thankjava.com
     * @date 2016年8月10日 下午5:41:51
     * @version 1.0
     */
    public static byte[] sign(byte[] contentByteArray, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance(signAlgorithm);
            signature.initSign(privateKey);
            signature.update(contentByteArray);
            return signature.sign();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 对内容进行验签
     * <p>Function: verify</p>
     * <p>Description: </p>
     *
     * @param contentByteArray 明文
     * @param cipherByteArray   密文
     * @param publicKey    公钥
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     * @author acexy@thankjava.com
     * @date 2016年8月10日 下午6:18:28
     * @version 1.0
     */
    public static boolean verify(byte[] contentByteArray, byte[] cipherByteArray, PublicKey publicKey) {
        Signature signature = null;
        try {
            signature = Signature.getInstance(signAlgorithm);
            signature.initVerify(publicKey);
            signature.update(contentByteArray);
            return signature.verify(cipherByteArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 使用公钥对内容进行加密
     *
     * @param contentByteArray
     * @param publicKey
     * @return
     */
    public static byte[] encrypt(byte[] contentByteArray, PublicKey publicKey) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            return cipher.doFinal(contentByteArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 使用私钥进行密文解密
     *
     * @param cipherByteArray
     * @param privateKey
     * @return
     */
    public static byte[] decrypt(byte[] cipherByteArray, PrivateKey privateKey) {
        try {
            Cipher cipher = Cipher.getInstance(algorithm);
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            return cipher.doFinal(cipherByteArray);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

}
