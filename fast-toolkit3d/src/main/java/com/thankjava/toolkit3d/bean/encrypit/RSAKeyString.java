package com.thankjava.toolkit3d.bean.encrypit;

public class RSAKeyString {

    private String publicKey;
    private String privateKey;

    public RSAKeyString(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
