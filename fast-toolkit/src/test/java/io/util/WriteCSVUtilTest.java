package io.util;

import com.thankjava.toolkit.core.io.util.WriteCSVUtil;

import java.io.UnsupportedEncodingException;

public class WriteCSVUtilTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        WriteCSVUtil writeCSVUtil = new WriteCSVUtil(
                "/Users/acexy/Downloads/csv.csv",
                "gbk",
                WriteCSVUtil.LineSeparator.LF_UNIX);
        writeCSVUtil.write("中国,美国");
        writeCSVUtil.write(new String[]{"nice", "cool"}, ',');
        writeCSVUtil.close();

    }
}
