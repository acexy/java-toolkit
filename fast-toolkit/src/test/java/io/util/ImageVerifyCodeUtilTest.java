package io.util;

import com.thankjava.toolkit.core.io.util.ImageVerifyCodeUtil;

import java.io.*;

/**
 * 简易图片验证码
 */
public class ImageVerifyCodeUtilTest {

    public static void main(String[] args) throws IOException {

        ImageVerifyCodeUtil.encode2ImgFile(120, 40, "f:/code.jpg", "2w34");

//        byte[] bytes = ImageVerifyCode.encodeImage(200,80,"1q2w3e");
//        ImageIO.write(ImageIO.read(new ByteArrayInputStream(bytes)),"png",new File("f:/b.jpg"));
    }

}
