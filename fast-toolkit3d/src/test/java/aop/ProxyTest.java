package aop;

import org.thankjava.toolkit3d.utils.aop.core.AopProxyFactory;

public class ProxyTest {

	public static void main(String[] args) {
	
		
		long start = System.currentTimeMillis();
		Business business = AopProxyFactory.createProxyObject(Business.class);
		for(int i = 0 ; i < 1 ; i ++){
			
//			System.out.println("主函数执行bussiness.exe方法 并传入参数 param");
			
			business.exe("param");
//			business.exe1("param");
			
//			System.out.println("主函数执行bussiness.exe方法 执行完毕 得到返回值" + param);
		}
		System.out.println("proxy used: " + (System.currentTimeMillis() - start));
		
		
//		start = System.currentTimeMillis();
//		
//		business = new Business();
//		for(int i = 0 ; i < 10000 ; i ++){
//			
//			
//			String param = business.exe("param");
//			
//		}
//		System.out.println("proxy used: " + (System.currentTimeMillis() - start));
	}
}
