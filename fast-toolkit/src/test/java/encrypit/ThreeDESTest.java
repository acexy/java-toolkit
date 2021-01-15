package encrypit;

import com.thankjava.toolkit.core.encrypit.ThreeDES;

public class ThreeDESTest {

    public static void main(String[] args) {

        ThreeDES threeDES = new ThreeDES();

        String pubKey = "87c9bcf8484440ebaa99d6df2a1f4768";

        String cipher = threeDES.encrypt(pubKey, "打发大水发生大法打发盛大发售的发生的");
        System.out.println("通过key加密后的密文：" + cipher);
        System.out.println("通过key解密后的原文：" + threeDES.decrypt(pubKey, cipher));
        System.out.println("通过key解密后的原文：" + threeDES.decrypt("badPk", cipher));
    }
}
