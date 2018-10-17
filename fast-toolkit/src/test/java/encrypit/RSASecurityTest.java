package encrypit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;

import com.thankjava.toolkit.core.encrypit.RSA;

public class RSASecurityTest {

	public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, InvalidKeyException, SignatureException {
		Key[] keys = RSA.keyGen(1024);
		Key pubK = keys[0];
		Key priK = keys[1];
		
		byte[] ciphertext = RSA.encrypt("AAA", (PrivateKey)priK);
		System.out.println(RSA.verify("AAA",ciphertext, (PublicKey)pubK));
	}
}
