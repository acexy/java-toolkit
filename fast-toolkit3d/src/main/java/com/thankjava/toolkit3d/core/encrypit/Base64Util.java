package com.thankjava.toolkit3d.core.encrypit;

import org.apache.commons.codec.binary.Base64;

/**
 * 基于Apache Base64
 */
public class Base64Util {

    /**
     * base64 编码
     * @param bytes
     * @return
     */
    public static byte[] encode(byte[] bytes) {
        return Base64.encodeBase64(bytes);
    }

    /**
     * base64 编码
     * @param bytes
     * @return base64字符串
     */
    public static String encode2String(byte[] bytes) {
        return Base64.encodeBase64String(bytes);
    }

    /**
     * base64 解码
     * @param bytes
     * @return
     */
    public static byte[] decode(byte[] bytes) {
        return Base64.decodeBase64(bytes);
    }

    /**
     * base64 解码
     * @param base64String
     * @return
     */
    public static byte[] decode(String base64String) {
        return Base64.decodeBase64(base64String);
    }
}
