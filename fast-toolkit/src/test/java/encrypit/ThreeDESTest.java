package encrypit;

import com.thankjava.toolkit.core.encrypit.ThreeDES;

public class ThreeDESTest {

    public static void main(String[] args) {

        ThreeDES threeDES = new ThreeDES("0547CD5B468B47ACBC95F94B9CB88879");

        String pubKey = "pubkey";

        String cipher = threeDES.encrypt(pubKey, "对称加密");
        System.out.println("通过key加密后的密文：" + cipher);
        System.out.println("通过key解密后的原文：" + threeDES.decrypt(pubKey, cipher));
        System.out.println("通过key解密后的原文：" + threeDES.decrypt("badPk", cipher));
    }
}
