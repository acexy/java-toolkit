package io;

import com.thankjava.toolkit.bean.common.Charset;
import com.thankjava.toolkit.core.io.FileAppendWriter;

import java.util.UUID;

public class FileAppendWriteTest {

    public static void main(String[] args) {
        int i = 0;
        long startTime = System.currentTimeMillis();

        String filePath = "/Users/acexy/Downloads/append.txt";
        String lineSeparator = System.getProperty("line.separator");
        FileAppendWriter fileAppendWriter = new FileAppendWriter(filePath, Charset.gbk);
        String content = UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() + lineSeparator;
        while (i < 1000000) {
            fileAppendWriter.write(content);
            i++;
        }
        fileAppendWriter.close();
        System.out.println(System.currentTimeMillis() - startTime);

    }
}
