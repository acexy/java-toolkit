package aop;

import org.thankjava.toolkit.utils.aop.anno.After;
import org.thankjava.toolkit.utils.aop.anno.Before;

public class BusinessImpl implements IBusiness{
	
	@Before(cutMethod = "before",cutClass = CutPoint.class)
	@After(cutMethod = "after",cutClass = CutPoint.class)
	public String exe(String str){
		return str;
	}
}
