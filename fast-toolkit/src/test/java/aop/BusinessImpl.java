package aop;

import com.thankjava.toolkit.bean.aop.anno.After;
import com.thankjava.toolkit.bean.aop.anno.Before;

public class BusinessImpl implements IBusiness{
	
	@Before(cutMethod = "before",cutClass = CutPoint.class)
	@After(cutMethod = "after",cutClass = CutPoint.class)
	public String exe(String str){
        System.out.println("我是BusinessImpl.exe方法，我得到了参数：" + str + " 并返回了它");
		return str;
	}
}
