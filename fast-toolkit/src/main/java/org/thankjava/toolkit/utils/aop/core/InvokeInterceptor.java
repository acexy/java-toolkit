package org.thankjava.toolkit.utils.aop.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * 核心执行的拦截器
* <p>Function: InvokeInterceptor</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年8月17日 下午2:11:06
* @version 1.0
 */
public class InvokeInterceptor implements InvocationHandler{

	private Object implementObject;
	
	public InvokeInterceptor(Object implementObject){
		this.implementObject = implementObject;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		
		//执行被代理的方法
		Object invokeReturn = method.invoke(implementObject, args);
		
		return invokeReturn;
	}

}
