package com.thankjava.toolkit.core.io.util;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Iterator;

/**
 * @author : acexy@thankjava.com
 * @desc : 图片的一般处理
 * @date : 2020/6/24
 **/
public class ImageCommonUtil {

    private final static String SUPPORT_TYPE = "jpeg";

    /**
     * 对jpeg图片进行质量压缩
     * @param picPath
     * @param quality 0 ~ 1 质量
     * @return
     * @throws IOException
     */
    public static byte[] simpleCompressJPEG(String picPath, float quality) throws IOException {

        BufferedImage image = ImageIO.read(new FileInputStream(new File(picPath)));
        Iterator<ImageWriter> iterator = ImageIO.getImageWritersByFormatName(SUPPORT_TYPE);
        ImageWriter imageWriter = (ImageWriter) iterator.next();

        ImageWriteParam imageWriteParam = imageWriter.getDefaultWriteParam();
        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        imageWriteParam.setCompressionQuality(quality);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IIOImage iioImage = new IIOImage(image, null, null);

        imageWriter.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
        imageWriter.write(null, iioImage, imageWriteParam);

        InputStream inputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        ImageIO.write(ImageIO.read(inputStream), SUPPORT_TYPE, outStream);
        return outStream.toByteArray();
    }
}
