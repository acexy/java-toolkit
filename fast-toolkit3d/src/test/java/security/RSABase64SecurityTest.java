package security;

import org.thankjava.toolkit3d.security.RSABase64Security;

public class RSABase64SecurityTest {

	public static void main(String[] args) {
		
		//获取1024位公密钥对
		String[] keys = RSABase64Security.keyGen(1024);
		
		//公钥 base64
		System.out.println("pubKey: " + keys[0]);
		
		//密钥 base64
		System.out.println("priKey: " + keys[1]);
		
		//将 hellow rsa 进行公钥加密
		String ciphertext = RSABase64Security.encrypt("hellow rsa", keys[1]);
		
		//得到加密后的密文 base64
		System.out.println("ciphertext: " + ciphertext);
		
		//利用密文 和密钥 对已知内容 hellow rsa 进行验密 , 验证密文的内容是否为hellow rsa
		System.out.println("verify: " + RSABase64Security.verify("hellow rsa", ciphertext, keys[0]));

		//从base64 String 中解出公钥
		RSABase64Security.decryptPublicKeyForBasee64KeyStr("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDy33MTrxD8eG7dhA3nSEH3RCMg7fFEFWjabAAc7AVE10Glt7sNFDmNI5035rcnqgSGiDpMBYZNO5Ovid3PunwpK3xaNdX7FMIh+kScpfX2bqJZlWnPPjP6RfIu+AoEPM2V2e3gBQuwwxQHcBZaqg6baGz133hJIz6/7Urkttfh1QIDAQAB");
	}
}
