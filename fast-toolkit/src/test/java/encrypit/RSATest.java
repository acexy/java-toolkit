package encrypit;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SignatureException;

import com.thankjava.toolkit.bean.encrypit.RSAKey;
import com.thankjava.toolkit.core.encrypit.RSA;

public class RSATest {

    public static void main(String[] args) throws NoSuchAlgorithmException, FileNotFoundException, IOException, InvalidKeyException, SignatureException {

        RSAKey key = RSA.keyGen(1024);

        PublicKey pubK = key.getPublicKey();
        PrivateKey priK = key.getPrivateKey();

        // 私钥加签数据
        String text = "RSA非对称算法";

        byte[] cipherByte = RSA.encrypt(text.getBytes(), pubK);
        System.out.println("RSA解密：" + new String(RSA.decrypt(cipherByte, priK)));

        byte[] signArray = RSA.sign(text.getBytes(), priK);
        System.out.println("验签结果：" + RSA.verify(text.getBytes(), signArray, pubK));
    }
}
