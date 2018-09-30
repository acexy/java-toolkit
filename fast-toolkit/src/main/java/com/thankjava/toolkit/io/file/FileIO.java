package com.thankjava.toolkit.io.file;

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
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
