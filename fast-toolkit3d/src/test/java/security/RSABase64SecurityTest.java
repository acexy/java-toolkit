package security;

import org.thankjava.toolkit3d.security.RSABase64Security;

public class RSABase64SecurityTest {

	public static void main(String[] args) {
//		String[] keys = RSABase64Security.keyGen(1024);
//		
//		System.out.println("pubKey: " + keys[0]);
//		System.out.println("priKey: " + keys[1]);
//		
//		String ciphertext = RSABase64Security.encrypt("hellow rsa", keys[1]);
//		System.out.println("ciphertext: " + ciphertext);
//		System.out.println("verify: " + RSABase64Security.verify("hellow rsa", ciphertext, keys[0]));
//		
		RSABase64Security.decryptPublicKeyForBasee64KeyStr("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDy33MTrxD8eG7dhA3nSEH3RCMg7fFEFWjabAAc7AVE10Glt7sNFDmNI5035rcnqgSGiDpMBYZNO5Ovid3PunwpK3xaNdX7FMIh+kScpfX2bqJZlWnPPjP6RfIu+AoEPM2V2e3gBQuwwxQHcBZaqg6baGz133hJIz6/7Urkttfh1QIDAQAB");
	}
}
