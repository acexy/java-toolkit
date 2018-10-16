package io;

import com.thankjava.toolkit.core.io.FastFileAppend;

import java.util.UUID;

public class FastFileAppendTest {

    public static void main(String[] args) {
        int i = 0;
        long startTime = System.currentTimeMillis();

        String filePath = "/Users/acexy/Downloads/append.txt";
        String lineSeparator = System.getProperty("line.separator");
        FastFileAppend fastFileAppend = new FastFileAppend(filePath, "GBK");
        String content = UUID.randomUUID().toString() + UUID.randomUUID().toString() + UUID.randomUUID().toString() + lineSeparator;
        while (i < 1000000) {
            fastFileAppend.write(content);
            i++;
        }
        fastFileAppend.close();
        System.out.println(System.currentTimeMillis() - startTime);

    }
}
