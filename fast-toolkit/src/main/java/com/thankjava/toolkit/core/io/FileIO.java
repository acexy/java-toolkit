package com.thankjava.toolkit.core.io;

import java.io.*;

/**
 * @Author: acexy@thankjava.com
 * 2018/9/30
 * @Description: 用于处理文件常用的IO
 **/
public class FileIO {

    /**
     * 将byte数据写成文件
     *
     * @param filePath
     * @param byteData
     */
    public static void write2File(String filePath, byte[] byteData) {
        try {
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
            bufferedOutputStream.write(byteData);
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将指定文件读成byte数据
     * @param filePath
     * @return
     */
    public static byte[] read2ByteArray(String filePath) {
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(new File(filePath)));
            byte[] byteArr = new byte[bufferedInputStream.available()];
            bufferedInputStream.read(byteArr);
            bufferedInputStream.close();
            return byteArr;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
