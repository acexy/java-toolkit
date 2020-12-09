package encrypit;

import com.thankjava.toolkit.bean.encrypit.RSAEncryptAlgorithm;
import com.thankjava.toolkit.bean.encrypit.RSASignAlgorithm;
import com.thankjava.toolkit3d.bean.encrypit.RSAKeyString;
import com.thankjava.toolkit3d.core.encrypit.RSAWithBase64;

public class RSABase64Test {

    public static void main(String[] args) {

        String pk;
        String pi;
        String base64Cipher;
        String text;
        //获取1024位公密钥对
        RSAKeyString rsaKeyString = RSAWithBase64.keyGen(1024);
        //公钥 base64

        text = "RSA非对称加密";

        pk = rsaKeyString.getPublicKey();
        pi = rsaKeyString.getPrivateKey();

        System.out.println(pi);
        System.out.println(rsaKeyString.getPrivatePem());
        System.out.println(pk);
        System.out.println(rsaKeyString.getPublicPem());

//        pk = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKGcmle4bY5xTyd5dXu5koPB58xAMPEXF/adVyzBKku1Vk5umPGwGKxSRrxfdAkuLg4is+pP+SOWKNCCk0lI5C8CAwEAAQ==";
//        pi = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAoZyaV7htjnFPJ3l1e7mSg8HnzEAw8RcX9p1XLMEqS7VWTm6Y8bAYrFJGvF90CS4uDiKz6k/5I5Yo0IKTSUjkLwIDAQABAkA55zshfNG7/HsIwKrINpf5Hytm8gTXDJgoYv1N6rO1xeFlDq7gpq5tlFhL3gWFKBRjRZ5lg1UobBwtS0zhVx2RAiEA92EX1RbPotyA5qkrsB8OlKj+gSBFQuYIrA+tu2fCLVkCIQCnPl1tWfikFDCC4lhOVz1FCgTQXFKd89ai14k2EixExwIhAN9N9ux0zfPOplERDo3KSygosKyxC7SK3/MpqAMOc3rpAiBR5oqeKHdI8tIm5m1UQAizLIra9rtGusibim2ZWyDutQIhAPBGlykRghBjYNVfQsCyb+dVulFDVv0EuXz679HHHR8k";
//
//        // 进行公钥加密
        text = "RSA测试";
        base64Cipher = RSAWithBase64.encrypt(text, pk, RSAEncryptAlgorithm.RSA_ECB_PKCS1Padding);
        System.out.println("加密后的base64密文：" + base64Cipher);

        // 私钥解密
        System.out.println("解密的内容：" + RSAWithBase64.decrypt(base64Cipher, pi,RSAEncryptAlgorithm.RSA_ECB_PKCS1Padding));
//
//
        base64Cipher = RSAWithBase64.sign(text, pi,RSASignAlgorithm.SHA256withRSA);
        System.out.println("加签base64签文：" + base64Cipher);

        System.out.println("验签：" + RSAWithBase64.verify(text, base64Cipher, pk, RSASignAlgorithm.SHA256withRSA));

    }
}
