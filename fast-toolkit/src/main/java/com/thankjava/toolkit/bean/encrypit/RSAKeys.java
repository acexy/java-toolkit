package com.thankjava.toolkit.bean.encrypit;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAKeys {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RSAKeys(PublicKey publicKey, PrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
}
