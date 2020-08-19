package encrypit;

import com.thankjava.toolkit3d.core.encrypit.Base64Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 创建时间 : 20/8/14 <br />
 *
 * @author : acexy@acexy.cn
 **/
public class Base64UtilTest {

    public static void main(String[] args) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("F:\\Download\\Browser\\5f335f650cf5d85bf8bc0e7a.png"));
        byte[] bytes = new byte[fileInputStream.available()];
        fileInputStream.read(bytes);
        System.out.println(Base64Util.encode2String(bytes));
    }
}