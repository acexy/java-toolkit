package io.util;

import com.thankjava.toolkit.core.io.util.ImageCommonUtil;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * @author : acexy@thankjava.com
 * @desc : TODO
 * @date : 2020/6/24
 **/
public class ImageCommonUtilTest {

    public static void main(String[] args) throws IOException {

        ImageIO.write(ImageIO.read(new ByteArrayInputStream(ImageCommonUtil.simpleCompressJPEG("/Users/acexy/Downloads/ffc11578f5d899a059e760e96bc2f3f8.jpg", 0.09f))), "JPEG", new File("/Users/acexy/Downloads/qrcode.jpg"));
    }
}
