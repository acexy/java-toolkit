package security;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;

import org.thankjava.toolkit.utils.security.RSASecurity;

public class RSASecurityTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, InvalidKeyException, SignatureException {
		Key[] keys = RSASecurity.keyGen(1024);
		Key pubK = keys[0];
		Key priK = keys[1];
		
		byte[] ciphertext = RSASecurity.encrypt("AAA", (PrivateKey)priK);
		System.out.println(RSASecurity.verify("AAA",ciphertext, (PublicKey)pubK));
	}
}
