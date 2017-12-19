package com.thankjava.toolkit.io.file;

import java.io.*;

/**
 * 高效率大量数据写入同一个文件的方法
 * IO追加的方式写入文件
 */
public class FastFileAppend {

    private Writer writer;

    public FastFileAppend(String filePath, String charset) {
        try {
            openIO(filePath, charset);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openIO(String filePath, String charset) throws IOException {
        writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(
                                new File(filePath),
                                true
                        ),
                        charset
                )
        );
    }

    public void write(String content) {
        try {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
