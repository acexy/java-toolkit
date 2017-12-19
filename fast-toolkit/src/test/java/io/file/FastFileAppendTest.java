package io.file;

import com.thankjava.toolkit.io.file.FastFileAppend;

import java.util.UUID;

public class FastFileAppendTest {

    public static void main(String[] args) {
        int i = 0;
        long startTime = System.currentTimeMillis();

        String filePath = "/Users/acexy/Downloads/append.txt";
        String lineSeparator = System.getProperty("line.separator");
        FastFileAppend fastFileAppend = new FastFileAppend(filePath, "GBK");
        while (i < 10000) {
            fastFileAppend.write(UUID.randomUUID().toString() + lineSeparator);
            i++;
        }
        fastFileAppend.close();
        System.out.println(System.currentTimeMillis() - startTime);

    }
}
