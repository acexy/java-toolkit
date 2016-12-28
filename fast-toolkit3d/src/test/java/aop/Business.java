package aop;

import com.thankjava.toolkit3d.aop.anno.After;
import com.thankjava.toolkit3d.aop.anno.Before;

public class Business {
	
	@Before(cutMethod = "before" , cutClass = CutPoint.class)
	@After(cutMethod = "after" , cutClass = CutPoint.class)
	public String exe(String str){
		return str;
	}
	
	public String exe(int num) {
		System.out.println(num);
		return "num";
	}
}
