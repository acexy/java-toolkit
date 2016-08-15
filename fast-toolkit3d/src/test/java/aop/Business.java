package aop;

import org.thankjava.toolkit3d.utils.aop.anno.After;
import org.thankjava.toolkit3d.utils.aop.anno.Before;

public class Business {
	
	@Before(cutMethod = "before" , cutClass = CutPoint.class)
	@After(cutMethod = "after" , cutClass = CutPoint.class)
	public String exe(String str){
//		System.out.println("exe 现在执行，接收到的参数是: " + str + " 并返回 123");
		return "123";
	}
	
	public String exe1(String str){
//		System.out.println("exe 现在执行，接收到的参数是: " + str + " 并返回 123");
		return "123";
	}
}
