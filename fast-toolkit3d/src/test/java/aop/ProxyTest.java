package aop;

import com.thankjava.toolkit3d.aop.core.AopProxyFactory;

public class ProxyTest {

	public static void main(String[] args) {
		
		Business business = AopProxyFactory.createProxyObject(Business.class);
		
		long st = System.currentTimeMillis();
//		for (int i = 0 ; i < 100000 ; i ++) {
//			System.out.println("最终函数获得的返回值: " + business.exe(1));
			System.out.println("最终函数获得的返回值: " + business.exe("1"));
//		}
		System.out.println("total time : " + (System.currentTimeMillis() - st));
	}
}
