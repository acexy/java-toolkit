package com.thankjava.toolkit.bean.encrypit;

/**
 * @Desc: RSA算法常用padding模式
 * @Author: acexy@thankjava.com
 * @Date: 2019-06-28
 **/
public enum RSAEncryptAlgorithm {

    RSA_ECB_PKCS1Padding("RSA/ECB/PKCS1Padding"),
    RSA_ECB_OAEPWithSHA_1AndMGF1Padding("RSA/ECB/OAEPWithSHA-1AndMGF1Padding"),
    RSA_ECB_OAEPWithSHA_256AndMGF1Padding("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");

    public String algorithm;

    private RSAEncryptAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }
}
