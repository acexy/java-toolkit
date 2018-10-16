package com.thankjava.toolkit.core.file;

import java.io.*;

/**
 * 基于 FastFileAppend 快速写入大量数据的CSV文件
 */
public class LargerDataWrite2CSV {

    private FastFileAppend fastFileAppend;
    private String lineSeparator;
    StringBuffer stringBuffer = new StringBuffer();

    public enum LineSeparator {

        CR_MAC("\r"),
        CRLF_WINDOWS("\r\n"),
        LF_UNIX("\n");

        public String separator;

        LineSeparator(String separator) {
            this.separator = separator;
        }
    }

    /**
     * @param filePath
     * @param lineSeparator 换行符 如果为空则获取当前系统环境的换行符
     * @throws FileNotFoundException
     */
    public LargerDataWrite2CSV(String filePath, String charset, LineSeparator lineSeparator) {
        fastFileAppend = new FastFileAppend(filePath, charset);
        if (lineSeparator != null) {
            this.lineSeparator = lineSeparator.separator;
        } else {
            this.lineSeparator = System.getProperty("line.separator");
        }
    }

    /**
     * 写入每行数据
     *
     * @param dataLine 每个字段的值
     * @param dataSeparator 字段之间的分隔符
     * @throws IOException
     */
    public void write(String[] dataLine, char dataSeparator) {
        if (dataLine == null || dataLine.length == 0) {
            return;
        }
        stringBuffer.setLength(0);
        for (String line : dataLine) {
            stringBuffer.append(line);
            stringBuffer.append(dataSeparator);
        }
        stringBuffer.append(lineSeparator);
        stringBuffer.deleteCharAt(stringBuffer.length() - 2);
        fastFileAppend.write(stringBuffer.toString());
    }

    public void write(String line) {
        fastFileAppend.write(line + lineSeparator);
    }

    public void close() {
        fastFileAppend.close();
    }
}
