package com.thankjava.toolkit.core.io;

import com.thankjava.toolkit.bean.common.Charset;

import java.io.*;

/**
 * 高效率大量数据写入同一个文件的方法
 * IO追加的方式写入文件
 */
public class FileAppendWriter {

    private Writer writer;

    /**
     * 实例化并打开IO
     * @param filePath
     * @param charset
     */
    public FileAppendWriter(String filePath, Charset charset) {
        try {
            writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(
                                    new File(filePath),
                                    true
                            ),
                            charset.charset
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 追加写入字符串
     * @param content
     */
    public FileAppendWriter write(String content) {
        try {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 追加写入char数组数据
     * @param chars
     */
    public FileAppendWriter write(char[] chars) {
        try {
            writer.write(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }


    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
