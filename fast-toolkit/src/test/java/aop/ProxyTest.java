package aop;

import com.thankjava.toolkit.core.aop.core.AopProxyFactory;

public class ProxyTest {

	public static void main(String[] args) {
	
		/**
		 * 基于JDK代理的AOP是通过面向接口层面实现
		 */
		IBusiness iBusiness = AopProxyFactory.createProxyObject(IBusiness.class, new BusinessImpl());
		
//		long st = System.currentTimeMillis();
//		for (int i = 0 ; i < 100000 ; i ++) {
//			System.out.println("最终函数获得的返回值: " + iBusiness.exe(1));
			System.out.println("最终函数获得的返回值: " + iBusiness.exe("1"));
//		}
//		System.out.println("total time : " + (System.currentTimeMillis() - st));
	}
}