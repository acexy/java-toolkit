package org.thankjava.toolkit3d.utils.aop.core;

import net.sf.cglib.proxy.Enhancer;


/**
 * aop切片的代理生成
* <p>Function: AopProxyFactory</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年5月16日 上午11:08:14
* @version 1.0
 */
public class AopProxyFactory {
	
	
	/**
	 * 生成代理对象 基于代理对象的无参构造函数
	* <p>Function: createProxyObject</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月17日 下午2:46:58
	* @version 1.0
	* @param proxyClass
	* @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T createProxyObject(Class<T> proxyClass){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(proxyClass);
		enhancer.setCallback(new InvokeInterceptor());
		return (T) enhancer.create();
	}
}
