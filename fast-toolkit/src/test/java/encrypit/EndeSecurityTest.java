package encrypit;

import com.thankjava.toolkit.core.encrypit.ThreeDES;

public class EndeSecurityTest {

	public static void main(String[] args) {
		System.out.println(ThreeDES.encryptStr("app", "赵肖瑶"));
		System.out.println(ThreeDES.encryptStr("app", "赵肖"));
		
		System.out.println(ThreeDES.decodeStr("app", "E41C325E301B31EAA7844806CBC321A9"));
		System.out.println(ThreeDES.decodeStr("ap1", "4FC3ABC9FC3B0984"));
	}
}
