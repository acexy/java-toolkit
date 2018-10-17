package encrypit;

import com.thankjava.toolkit3d.bean.encrypit.RSAStringKeys;
import com.thankjava.toolkit3d.core.encrypit.RSAWithBase64;

public class RSABase64Test {

    public static void main(String[] args) {

        //获取1024位公密钥对
        RSAStringKeys rsaStringKeys = RSAWithBase64.keyGen(1024);
        //公钥 base64

        String text = "RSA非对称加密";

        // 进行公钥加密
        String base64Cipher = RSAWithBase64.encrypt(text, rsaStringKeys.getPublicKey());

        // 得到加密后的密文
        System.out.println("加密后的base64密文：" + base64Cipher);
        System.out.println("解密加密的内容：" + RSAWithBase64.decrypt(base64Cipher, rsaStringKeys.getPrivateKey()));


        base64Cipher = RSAWithBase64.sign(text, rsaStringKeys.getPrivateKey());
        System.out.println("加签base64签文：" + base64Cipher);
        System.out.println("验签：" + RSAWithBase64.verify(text, base64Cipher, rsaStringKeys.getPublicKey()));

        //从base64 String 中解出公钥
        RSAWithBase64.decryptPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDy33MTrxD8eG7dhA3nSEH3RCMg7fFEFWjabAAc7AVE10Glt7sNFDmNI5035rcnqgSGiDpMBYZNO5Ovid3PunwpK3xaNdX7FMIh+kScpfX2bqJZlWnPPjP6RfIu+AoEPM2V2e3gBQuwwxQHcBZaqg6baGz133hJIz6/7Urkttfh1QIDAQAB");
    }
}
