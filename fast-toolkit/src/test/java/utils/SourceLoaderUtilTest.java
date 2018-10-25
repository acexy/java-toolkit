package utils;

import com.thankjava.toolkit.core.utils.SourceLoaderUtil;

import java.io.IOException;
import java.io.InputStream;

public class SourceLoaderUtilTest {

    public static void main(String[] args) throws IOException {
        InputStream is = SourceLoaderUtil.getResourceAsInputStream("config.properties");
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        is.close();
        System.out.println(new String(bytes));
    }

}
