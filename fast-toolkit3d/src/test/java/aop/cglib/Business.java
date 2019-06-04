package aop.cglib;


import com.thankjava.toolkit.bean.aop.anno.After;
import com.thankjava.toolkit.bean.aop.anno.Before;

public class Business {
	
	@Before(cutMethod = "before" , cutClass = CutPoint.class)
	@After(cutMethod = "after" , cutClass = CutPoint.class)
	public String exe(String str){
		System.out.println("Business.exe方法，我得到了参数：" + str + " 并返回了它");
		return str;
	}

    @Before(cutMethod = "before",cutClass = CutPoint.class)
    @After(cutMethod = "after",cutClass = CutPoint.class)
    public String exe() {
        System.out.println("我是BusinessImpl.exe 无参方法 我返回了 exe");
        return "exe";
    }

    @Before(cutMethod = "before",cutClass = CutPoint.class)
    @After(cutMethod = "after",cutClass = CutPoint.class)
    public void invoke() {
        System.out.println("我是BusinessImpl.invoke 无参无返回值方法");

    }
}
