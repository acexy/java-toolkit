package org.thankjava.toolkit3d.utils.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.thankjava.toolkit.utils.security.RSASecurity;

/**
 * 基于JDK RSA 算法 通过Base64缩位
* <p>Function: RSABase64Security</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年8月10日 下午6:25:44
* @version 1.0
 */
public class RSABase64Security {

	private static final String algorithm = "RSA";
	
	
	/**
	 * 获取RAS Base64 公密钥对
	* <p>Function: keyGen</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月10日 下午6:31:26
	* @version 1.0
	* @param 
	* @return String[] keysize index=0 公钥 index=1私钥
	 */
	public static String[] keyGen(int keysize){
		try {
			Key[] keys = RSASecurity.keyGen(keysize);
			
			String[] keysStr = new String[2];
			String publicKey = new String(Base64.encodeBase64(keys[0].getEncoded()));
			String privateKey = new String(Base64.encodeBase64(keys[1].getEncoded()));
			keysStr[0] = publicKey;
			keysStr[1] = privateKey;
			return keysStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 从Base64字符中解出私钥
	* <p>Function: decryptPrivateKeyForBase64KeyStr</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月11日 上午9:43:39
	* @version 1.0
	* @param privateKeyStr
	* @return
	 */
	public static PrivateKey decryptPrivateKeyForBase64KeyStr(String privateKeyStr){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec((Base64.decodeBase64(privateKeyStr)));
			return keyFactory.generatePrivate(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 从Base64字符中解出公钥
	* <p>Function: decryptPublicKeyForBasee64KeyStr</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月11日 上午9:50:30
	* @version 1.0
	* @param publicKeyStr
	* @return
	 */
	public static PublicKey decryptPublicKeyForBasee64KeyStr(String publicKeyStr){
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec((Base64.decodeBase64(publicKeyStr)));
			return keyFactory.generatePublic(keySpec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 加密
	* <p>Function: encrypt</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月11日 上午9:48:48
	* @version 1.0
	* @param originalText
	* @param privateKeyStr
	* @return
	 */
	public static String encrypt(String originalText,String privateKeyStr){
		PrivateKey privateKey = decryptPrivateKeyForBase64KeyStr(privateKeyStr);
		try {
			return new String(Base64.encodeBase64(RSASecurity.encrypt(originalText, privateKey)));
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 验密
	* <p>Function: verify</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月11日 上午9:54:13
	* @version 1.0
	* @param originalText
	* @param ciphertext
	* @param publicKeyStr
	* @return
	 */
	public static boolean verify(String originalText,String ciphertext, String publicKeyStr){
		PublicKey publicKey = decryptPublicKeyForBasee64KeyStr(publicKeyStr);
		try {
			return RSASecurity.verify(originalText, Base64.decodeBase64(ciphertext), publicKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (SignatureException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}
}
