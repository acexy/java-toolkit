package utils;

import com.thankjava.toolkit.core.utils.ClassSourceLoaderUtil;

import java.io.IOException;
import java.io.InputStream;

public class ClassSourceLoaderUtilTest {

    public static void main(String[] args) throws IOException {
        InputStream is = ClassSourceLoaderUtil.getResourceAsInputStream("config.properties");
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        is.close();
        System.out.println(new String(bytes));
    }

}
