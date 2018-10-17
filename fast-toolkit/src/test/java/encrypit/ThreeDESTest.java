package encrypit;

import com.thankjava.toolkit.core.encrypit.ThreeDES;

public class ThreeDESTest {

    public static void main(String[] args) {

        String priKey = "a 3des's pri key";
        String cipher = ThreeDES.encryptStr(priKey, "对称加密 3des");

        System.out.println("通过key加密后的密文：" + cipher);
        System.out.println("通过key解密后的原文：" + ThreeDES.decodeStr(priKey, cipher));
    }
}
