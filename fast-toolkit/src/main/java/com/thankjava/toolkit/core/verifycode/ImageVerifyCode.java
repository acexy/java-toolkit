package com.thankjava.toolkit.core.verifycode;

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
public class ImageVerifyCode {

    private ImageVerifyCode(){}

    private static final int defaultWidth = 200, defaultHigh = 80;

    private static Random random = new Random();

    private static void outputImage(int width, int high, OutputStream outputStream, String verifyCode) throws IOException {
        int verifySize = verifyCode.length();
        BufferedImage bufferedImage = new BufferedImage(width, high, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Color[] colors = new Color[5];
        Color[] colorSpaces = new Color[]{
                Color.WHITE, Color.CYAN,
                Color.GRAY, Color.LIGHT_GRAY,
                Color.MAGENTA, Color.ORANGE,
                Color.PINK, Color.YELLOW
        };
        float[] fractions = new float[colors.length];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorSpaces[random.nextInt(colorSpaces.length)];
            fractions[i] = random.nextFloat();
        }
        Arrays.sort(fractions);

        graphics2D.setColor(Color.GRAY);
        graphics2D.fillRect(0, 0, width, high);

        Color c = getRandColor(200, 250);
        graphics2D.setColor(c);
        graphics2D.fillRect(0, 2, width, high - 4);

        graphics2D.setColor(getRandColor(160, 200));
        for (int i = 0; i < 20; i++) {
            int x = random.nextInt(width - 1);
            int y = random.nextInt(high - 1);
            int xl = random.nextInt(6) + 1;
            int yl = random.nextInt(12) + 1;
            graphics2D.drawLine(x, y, x + xl + 40, y + yl + 20);
        }

        float yawpRate = 0.05f;
        int area = (int) (yawpRate * width * high);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(high);
            int rgb = getRandomIntColor();
            bufferedImage.setRGB(x, y, rgb);
        }

        shear(graphics2D, width, high, c);

        graphics2D.setColor(getRandColor(100, 160));
        int fontSize = high - 4;
        Font font = new Font("Algerian", Font.ITALIC, fontSize);
        graphics2D.setFont(font);
        char[] chars = verifyCode.toCharArray();
        for (int i = 0; i < verifySize; i++) {
            AffineTransform affine = new AffineTransform();
            affine.setToRotation(Math.PI / 4 * random.nextDouble() * (random.nextBoolean() ? 1 : -1), (width / verifySize) * i + fontSize / 2, high / 2);
            graphics2D.setTransform(affine);
            graphics2D.drawChars(chars, i, 1, ((width - 10) / verifySize) * i + 5, high / 2 + fontSize / 2 - 10);
        }

        graphics2D.dispose();
        ImageIO.write(bufferedImage, "jpg", outputStream);
    }

    private static void shear(Graphics g, int w1, int h1, Color color) {
        shearX(g, w1, h1, color);
        shearY(g, w1, h1, color);
    }

    private static void shearX(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(2);
        boolean borderGap = true;
        int frames = 1;
        int phase = random.nextInt(2);

        for (int i = 0; i < h1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(0, i, w1, 1, (int) d, 0);
            if (borderGap) {
                g.setColor(color);
                g.drawLine((int) d, i, 0, i);
                g.drawLine((int) d + w1, i, w1, i);
            }
        }

    }

    private static void shearY(Graphics g, int w1, int h1, Color color) {
        int period = random.nextInt(40) + 10; // 50;
        boolean borderGap = true;
        int frames = 20;
        int phase = 7;
        for (int i = 0; i < w1; i++) {
            double d = (double) (period >> 1)
                    * Math.sin((double) i / (double) period
                    + (6.2831853071795862D * (double) phase)
                    / (double) frames);
            g.copyArea(i, 0, 1, h1, 0, (int) d);
            if (borderGap) {
                g.setColor(color);
                g.drawLine(i, (int) d, i, 0);
                g.drawLine(i, (int) d + h1, i, h1);
            }

        }

    }

    private static int getRandomIntColor() {
        int[] rgb = getRandomRgb();
        int color = 0;
        for (int c : rgb) {
            color = color << 8;
            color = color | c;
        }
        return color;
    }

    private static int[] getRandomRgb() {
        int[] rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            rgb[i] = random.nextInt(255);
        }
        return rgb;
    }

    private static Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

    /**
     * 生成验证码文件
     * @param width
     * @param high
     * @param imgPathAndName
     * @param verifyCode
     * @throws IOException
     */
    public static void writeImage(int width, int high, String imgPathAndName, String verifyCode) throws IOException{
        width = width < 1 ? defaultWidth : width;
        high = high < 1 ? defaultHigh : high;
        File outputFile = new File(imgPathAndName);
        File dir = outputFile.getParentFile();
        if(!dir.exists()){
            dir.mkdirs();
        }
        outputFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(outputFile);
        outputImage(width, high, fos, verifyCode);
        fos.close();
    }
    /**
     * 得到验证码byte array
     * @param width
     * @param high
     * @param verifyCode
     * @return
     * @throws IOException
     */
    public static byte[] encodeImage(int width, int high, String verifyCode) throws IOException {
        width = width < 1 ? defaultWidth : width;
        high = high < 1 ? defaultHigh : high;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        outputImage(width, high, byteArrayOutputStream, verifyCode);
        return byteArrayOutputStream.toByteArray();
    }

}
