package verifycode;

import com.thankjava.toolkit.verifycode.ImageVerifyCode;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Arrays;
import java.util.Random;

/**
 * 简易图片验证码
 */
public class ImageVerifyCodeTest {

    public static void main(String[] args) throws IOException {

        ImageVerifyCode.writeImage(120, 40, "f:/code.jpg", "2w34");

//        byte[] bytes = ImageVerifyCode.encodeImage(200,80,"1q2w3e");
//        ImageIO.write(ImageIO.read(new ByteArrayInputStream(bytes)),"png",new File("f:/b.jpg"));
    }

}
