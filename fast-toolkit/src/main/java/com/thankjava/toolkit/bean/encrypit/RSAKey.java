package com.thankjava.toolkit.bean.encrypit;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSAKey {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    public RSAKey(PublicKey publicKey, PrivateKey privateKey) {
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
