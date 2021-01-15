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
//        //获取1024位公密钥对
//        RSAKeyString rsaKeyString = RSAWithBase64.keyGen(512);
//        //公钥 base64
//
//        String text = "RSA非对称加密";
//
//        pk = rsaKeyString.getPublicKey();
//        pi = rsaKeyString.getPrivateKey();
//
//        System.out.println(pi);
//        System.out.println(rsaKeyString.getPrivatePem());
//        System.out.println(pk);
//        System.out.println(rsaKeyString.getPublicPem());

//        pk = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKGcmle4bY5xTyd5dXu5koPB58xAMPEXF/adVyzBKku1Vk5umPGwGKxSRrxfdAkuLg4is+pP+SOWKNCCk0lI5C8CAwEAAQ==";
        pi = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKtqruOIu5prJijdfFfbj7QuQp5PO2bERV0OXMZyISJLKuiNjK7aEEPnnGSvR0heOt6+D2mKwPZCz5gF183bgo/ZwPmG8r7YEExEjKCuwUx/8oe74F+Y/SGNmGFvMgFpn8or8qCTxDJzgksFj03AWvrZWg36MzzSHXG2BSW5SbN/AgMBAAECgYAnBuYEhdf4o1aPzUWHF0UTh8jBFFb44czND/0NbWSf0y9UvQ1/rcQwQaY4wjNfhZyvo+QManveVX3AJexpQQXrbQRCl7b+jgDgCMXiJurUfe65i6kiWyUj5tM4GyygixWEjlc04sDDFdfDuk6NzFhI0OJEDXupBKRZC+IlTr4GOQJBANbT6R+q2lk35tK1J14CTH/+7Hgc3JKGb7rPlbCP0wFhC2BY+dbcXdxWmyl4htXEk2DfT1p/zIwawUCEvGPXtlsCQQDMROXJKzOOG5C857SqPMnOCb9+ZQ/llQ/PZwCDTyEDNNFLAynETmpCDqXAxSjDvhpQBZSThj0FL1q4qwa3k+itAkEAplfr+WNYMyFAEAPJedeZ2LpR7Cec+7AxL5uxMsgLANRd9Lqwqhh0bTp6ZsA216ug2q/PSCoQ/qNw5D5jiYPsZQJAM3Je1W3WDEKRD6Lj88Rfp075NeJKQK5sTvZIQMobLG2PEO95A4IkbQK3aHe/rp0gj2r4Yrd6guXMWZpplK7h+QJAVjHXyW0Cz7q/C632fEQjgjwaNUqmMLczRnaaLrQYC84x5oRGK2cxYf9MlB6l0f5WhqXDLGO/0w/3G6fOyvPowg==";
//
//        // 进行公钥加密
//        text = "RSA测试";
//        base64Cipher = RSAWithBase64.encrypt(text, pk);
//        System.out.println("加密后的base64密文：" + base64Cipher);


        // 私钥解密
//        base64Cipher = "VgmM53Wiu7eKzZleY8vMvSmTtC+CrBYSTmwvBO/8a/1r3Q9C4YEpjsDyJvTFpQgqwic9TLirw9YxrQhJ5PdfgA==";
//        System.out.println("解密的内容：" + RSAWithBase64.decrypt(base64Cipher, pi, RSAEncryptAlgorithm.RSA_ECB_OAEPWithSHA_1AndMGF1Padding));
//
//
        base64Cipher = RSAWithBase64.sign("b=c&dg=&e=5&z=1", pi,RSASignAlgorithm.SHA256withRSA);
        System.out.println("加签base64签文：" + base64Cipher);

//        text = "RSA测试";
//        base64Cipher = "guMXTdwCylOWp8avMdrjuNAqW7xaB7+Idzf1ExWs4XIYkg/a1CXsWs6hfEYii89cCFwBkd34PG7Mpc4Mi0h/BQ==";
//        System.out.println("验签：" + RSAWithBase64.verify(text, base64Cipher, pk, RSASignAlgorithm.SHA256withRSA));

    }
}
