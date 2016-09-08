package aop;

import org.thankjava.toolkit3d.aop.anno.After;
import org.thankjava.toolkit3d.aop.anno.Before;

public class Business {
	
	@Before(cutMethod = "before" , cutClass = CutPoint.class)
	@After(cutMethod = "after" , cutClass = CutPoint.class)
	public String exe(String str){
		return str;
	}
	
	public String exe(int num) {
		return "num";
	}
}
