package com.thankjava.toolkit.aop.core;

import java.lang.reflect.Proxy;

/**
 * 创建基于JDK的代理对象 JDK代理是基于接口层面的
* <p>Function: AopProxyFactory</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2016年8月17日 下午1:56:28
* @version 1.0
 */
public class AopProxyFactory {

	/**
	 * 创建代理对象
	* <p>Function: createProxyObject</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年8月17日 下午1:58:11
	* @version 1.0
	* @param interfaceType	接口类类型
	* @param implementObject	基于interfaceType接口的实现类实例
	 * @return 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxyObject(Class<T> interfaceType,Object implementObject){
		return (T)Proxy.newProxyInstance(
				implementObject.getClass().getClassLoader(),
				implementObject.getClass().getInterfaces(), 
				new InvokeInterceptor(implementObject)
				);
		
	}
}
