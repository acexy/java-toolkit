package io.file;

import com.thankjava.toolkit.core.io.LargerDataWrite2CSV;

import java.io.UnsupportedEncodingException;

public class LargerDataWrite2CSVTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        LargerDataWrite2CSV largerDataWrite2CSV = new LargerDataWrite2CSV(
                "/Users/acexy/Downloads/csv.csv",
                "gbk",
                LargerDataWrite2CSV.LineSeparator.CRLF_WINDOWS);
        largerDataWrite2CSV.write("中国,美国");
        largerDataWrite2CSV.write(new String[]{"nice", "cool"}, ',');
        largerDataWrite2CSV.close();

    }
}
