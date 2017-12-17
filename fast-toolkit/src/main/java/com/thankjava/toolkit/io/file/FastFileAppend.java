package com.thankjava.toolkit.io.file;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 通过高效率大量数据写入同一个文件的方法
 * IO追加的方式写入文件
 */
public class FastFileAppend {

    static Map<String, BufferedWriter> bufferedWriter = new HashMap<>();
    static Map<String, FileWriter> fileWriter = new HashMap<>();


    /**
     * 使用BufferedWriter进行文件追加的方式写入
     * 完毕后请使用 closeBufferedWriterIO() 方法关闭IO流
     * @param filePath
     * @param content
     */
    public static void fastBufferedWriter(String filePath, String content) {
        BufferedWriter out = null;
        try {
            out = bufferedWriter.get(filePath);
            if (out == null) {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath, true)));
                bufferedWriter.put(filePath, out);
            }
            out.write(content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 使用BufferedWriter进行文件追加的方式写入
     * 完毕后请使用 closeFileWriter() 方法关闭IO流
     * @param filePath
     * @param content
     */
    public static void fastFileWriter(String filePath, String content) {
        try {
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = fileWriter.get(filePath);
            if (writer == null) {
                writer = new FileWriter(filePath, true);
                fileWriter.put(filePath, writer);
            }
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 关闭fastBufferedWriter的文件句柄操作对象
     * @param filePath
     */
    public static void closeBufferedWriterIO(String filePath) {
        BufferedWriter out = bufferedWriter.get(filePath);
        if (out != null) {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭fastFileWriter的文件句柄操作对象
     * @param filePath
     */
    public static void closeFileWriter(String filePath) {
        FileWriter writer = fileWriter.get(filePath);
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
