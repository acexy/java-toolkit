package zip4j;

import com.thankjava.toolkit3d.core.zip4j.Zip4jUtil;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;

public class Zip4jUtilTest {

    public static void main(String[] args) {

        // 压缩文件夹
        Zip4jUtil.zip("F:\\Temp\\resources", "f:", CompressionMethod.DEFLATE, CompressionLevel.FASTEST, "123");
        Zip4jUtil.zipDefault("F:\\Temp\\resources", "F:\\b\\A.zip");

//        Zip4jUtil.unzip("F:\\Share\\094155259.zip", "F:\\Temp\\");
    }

}
