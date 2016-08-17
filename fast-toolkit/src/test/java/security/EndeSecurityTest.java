package security;

import org.thankjava.toolkit.utils.security.ThreeEndeSecurity;

public class EndeSecurityTest {

	public static void main(String[] args) {
		System.out.println(ThreeEndeSecurity.encryptStr("app", "赵肖瑶"));
		System.out.println(ThreeEndeSecurity.encryptStr("app", "赵肖"));
		
		System.out.println(ThreeEndeSecurity.decodeStr("app", "E41C325E301B31EAA7844806CBC321A9"));
		System.out.println(ThreeEndeSecurity.decodeStr("ap1", "4FC3ABC9FC3B0984"));
	}
}
