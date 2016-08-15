package system;

import org.thankjava.toolkit.system.Screenshot;
import org.thankjava.toolkit.system.SystemUtil;

public class SystemUtilTest {

	public static void main(String[] args) {
		String[] strs = SystemUtil.execShell("ipconfig", "GBK");
		System.out.println(strs[0]);
		
		Screenshot.screenshot("./","screensho");
	}
}
