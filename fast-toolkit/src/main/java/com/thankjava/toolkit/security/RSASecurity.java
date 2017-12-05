package com.thankjava.toolkit.security;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 * 基于JDK 的 rsa 算法
* <p>Function: RSASecurity</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2016年8月10日 下午4:57:19
* @version 1.0
 */
public final class RSASecurity {
	
	private static final String algorithm = "RSA";
	
	private static final String original = "SHA1WithRSA";
	
	private static final String charset = "utf-8";
	
	/**
	 * RSA 公密钥对生成
	* <p>Function: keyGen</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年8月10日 下午5:31:36
	* @version 1.0
	* @param keysize
	* @return Key数组  index = 0 公钥 | index = 1 私钥
	* @throws NoSuchAlgorithmException
	* @throws FileNotFoundException
	* @throws IOException
	 */
	public static Key[] keyGen(int keysize) throws NoSuchAlgorithmException, FileNotFoundException, IOException{
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance(algorithm);
		
		//设置长度
		generator.initialize(keysize);
		
		KeyPair keyPair = generator.generateKeyPair();
		
		Key publicKey = keyPair.getPublic();
		Key privateKey = keyPair.getPrivate();
		
		Key[] keys = new Key[2];
		
		keys[0] = publicKey;
		keys[1] = privateKey;
		
		return keys;
	}
	
	
	/**
	 * 加密算法
	* <p>Function: encrypt</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年8月10日 下午5:41:51
	* @version 1.0
	* @param originalText 明文
	* @param privateKey 私钥
	* @return
	* @throws NoSuchAlgorithmException
	* @throws InvalidKeyException
	* @throws SignatureException
	* @throws UnsupportedEncodingException
	 */
	public static byte[] encrypt(String originalText,PrivateKey privateKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException{
		Signature signature = Signature.getInstance(original);
		signature.initSign(privateKey);
		signature.update(originalText.getBytes(charset));
		return signature.sign();
	}
	
	/**
	 * 验密算法
	* <p>Function: verify</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年8月10日 下午6:18:28
	* @version 1.0
	* @param originalText 明文
	* @param ciphertext 密文
	* @param publicKey 公钥
	* @return
	* @throws NoSuchAlgorithmException
	* @throws InvalidKeyException
	* @throws SignatureException
	* @throws UnsupportedEncodingException
	 */
	public static boolean verify(String originalText,byte[] ciphertext, PublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException{
		Signature signature = Signature.getInstance(original);
		signature.initVerify(publicKey);
		signature.update(originalText.getBytes(charset));
		return signature.verify(ciphertext);
	}
}
