package io.file;

import com.thankjava.toolkit.io.file.FastFileAppend;

import java.util.UUID;

public class FastFileAppendTest {

    public static void main(String[] args) {
        int i = 0;
        long startTime = System.currentTimeMillis();

        String filePath = "/Users/acexy/Downloads/fastFileWriter";
        String lineSeparator = System.getProperty("line.separator");
        while (i < 1000000) {
            FastFileAppend.fastFileWriter(filePath, UUID.randomUUID().toString() + lineSeparator);
            i++;
        }
        FastFileAppend.closeFileWriter(filePath);
        System.out.println(System.currentTimeMillis() - startTime);

        filePath = "/Users/acexy/Downloads/fastBufferedWriter";
        startTime = System.currentTimeMillis();
        i = 0;
        while (i < 1000000) {
            FastFileAppend.fastBufferedWriter(filePath, UUID.randomUUID() + lineSeparator);
            i++;
        }
        FastFileAppend.closeBufferedWriterIO(filePath);
        System.out.println(System.currentTimeMillis() - startTime);
    }
}
