package zip4j;

import com.thankjava.toolkit3d.bean.zip4j.ZipLevel;
import com.thankjava.toolkit3d.bean.zip4j.ZipType;
import com.thankjava.toolkit3d.core.zip4j.Zip4jUtil;

public class Zip4jHelperTest {

	public static void main(String[] args) {
		
		boolean flag = false;
//		boolean flag = Zip4jUtil.unzip("F:\\Temp\\windows.weiyun.3.3.1539.zip", "F:\\Temp\\testZip","123");
//		System.out.println(flag);
		
		
		Zip4jUtil.zip("F:\\Temp\\myeclipse.10.7.1_crack", "F:\\Temp\\", ZipType.COMP_DEFLATE, ZipLevel.DEFLATE_LEVEL_NORMAL,"123");
	}
}
