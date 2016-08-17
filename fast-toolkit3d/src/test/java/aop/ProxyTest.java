package aop;

import org.thankjava.toolkit3d.utils.aop.core.AopProxyFactory;

public class ProxyTest {

	public static void main(String[] args) {
		
		Business business = AopProxyFactory.createProxyObject(Business.class);
		
		long st = System.currentTimeMillis();
		for (int i = 0 ; i < 100000 ; i ++) {
			System.out.println("最终函数获得的返回值: " + business.exe("param"));
		}
		System.out.println("total time : " + (System.currentTimeMillis() - st));
	}
}
