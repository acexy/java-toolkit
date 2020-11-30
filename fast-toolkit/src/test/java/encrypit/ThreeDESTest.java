package encrypit;

import com.thankjava.toolkit.core.encrypit.ThreeDES;

public class ThreeDESTest {

    public static void main(String[] args) {

        ThreeDES threeDES = new ThreeDES();

        String pubKey = "123456789";

        String cipher = threeDES.encrypt(pubKey, "BD3E741BB8C9C77A");
        cipher = "BD3E741BB8C9C77A";
        System.out.println("通过key加密后的密文：" + cipher);
        System.out.println("通过key解密后的原文：" + threeDES.decrypt(pubKey, cipher));
        System.out.println("通过key解密后的原文：" + threeDES.decrypt("badPk", cipher));
    }
}
