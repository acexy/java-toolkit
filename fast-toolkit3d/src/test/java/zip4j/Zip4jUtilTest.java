package zip4j;

import com.thankjava.toolkit3d.bean.zip4j.ZipLevel;
import com.thankjava.toolkit3d.bean.zip4j.ZipType;
import com.thankjava.toolkit3d.core.zip4j.Zip4jUtil;

public class Zip4jUtilTest {

    public static void main(String[] args) {

        // 压缩文件夹
//        Zip4jUtil.zip("F:\\Upgrade", "f:", ZipType.COMP_DEFLATE, ZipLevel.DEFLATE_LEVEL_FASTEST);

        Zip4jUtil.unzip("F:\\Upgrade.zip","F:\\Temp\\");
    }
}
