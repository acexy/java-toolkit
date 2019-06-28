package com.thankjava.toolkit3d.bean.encrypit;

/**
 * JAVA默认生成PKCS8格式的
 */
public class RSAKeyString {

    private String publicKey;
    private String privateKey;

    private String privatePem;
    private String publicPem;

    public RSAKeyString(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * 获取原始PKCS8格式的公钥字符串
     *
     * @return
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * 获取原始PKCS8格式的私钥字符串
     *
     * @return
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * 获取PKCS8的pem文件私钥字符串
     *
     * @return
     */
    public String getPrivatePem() {

        if (privatePem == null) {
            privatePem = splitKey(privateKey, "-----BEGIN PRIVATE KEY-----", "-----END PRIVATE KEY-----");
        }
        return privatePem;
    }

    /**
     * 获取pem文件公钥钥字符串
     *
     * @return
     */
    public String getPublicPem() {
        if (publicPem == null) {
            publicPem = splitKey(publicKey, "-----BEGIN PUBLIC KEY-----", "-----END PUBLIC KEY-----");
        }
        return publicPem;
    }

    private String splitKey(String keyStr, String beginStr, String endStr) {
        String separator = System.getProperty("line.separator");
        int len = keyStr.length();
        int freq = len / 64;
        StringBuilder sb = new StringBuilder(beginStr);

        for (int i = 0; i < freq; i++) {
            sb.append(separator);
            sb.append(keyStr.substring(i * 64, (i + 1) * 64));
        }

        if (len > freq * 64) {
            sb.append(separator);
            sb.append(keyStr.substring(freq * 64));
        }

        sb.append(separator);
        sb.append(endStr);
        return sb.toString();
    }

}
