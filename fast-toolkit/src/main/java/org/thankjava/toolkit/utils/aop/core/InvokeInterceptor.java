package org.thankjava.toolkit.utils.aop.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.thankjava.toolkit.utils.aop.anno.After;
import org.thankjava.toolkit.utils.aop.anno.Before;
import org.thankjava.toolkit.utils.aop.cache.Cache;
import org.thankjava.toolkit.utils.aop.entity.AopConfig;
import org.thankjava.toolkit.utils.aop.entity.AopParam;
import org.thankjava.toolkit.utils.reflect.ReflectHelper;


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
		
		//扫描该类的注解配置信息
		if(!Cache.isScannedClass(implementObject.getClass())){
			scanneAnno(implementObject);
		}
	}
	
	/**
	 * 将被代理的类的方法进行扫描，检测是否有aop的配置
	* <p>Function: scanneAnno</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月17日 下午5:51:04
	* @version 1.0
	* @param implementObject
	 */
	private void scanneAnno(Object implementObject){
		Method[] methods = ReflectHelper.getAllMethod(implementObject);
		if(methods != null){
		
			AopConfig config = null;
			Before before = null;
			After after = null;
			
			for (Method method : methods) {
				
				before = method.getAnnotation(Before.class);
				after = method.getAnnotation(After.class);
				
				config = new AopConfig();
				config.setMethodName(method.getName());
				config.setClassPath(implementObject.getClass().getName());
				
				if(before == null && after == null){
					config.setUsedAop(false);
				}else{
					config.setUsedAop(true);
					if(before != null){
						config.setBefore(before);
					}
					if(after != null){
						config.setAfter(after);
					}
				}
				
				Cache.put(config);
			}
		}
	}
	
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		AopConfig aopConfig = Cache.getAop(implementObject.getClass().getName() + method.getName());
		if(!aopConfig.isUsedAop()){
			return method.invoke(implementObject, args);
		}
		
		Before before = aopConfig.getBefore();
		
		Method aopMethod = null;
		Object aopObject = null;
		AopParam aopParam = null;
		
		if(before != null){ //执行before
			aopObject = before.cutClass().newInstance();
			aopMethod = ReflectHelper.getMethod(aopObject, before.cutMethod(), AopParam.class);
			aopParam = new AopParam(args);
			aopParam = (AopParam) ReflectHelper.invokeMethod(aopObject, aopMethod, aopParam);
		}
		
		if(aopParam != null && !aopParam.isInvokeProxyMethod()){
			return aopParam.getResult();
		}
		
		//执行被代理的方法
		Object invokeReturn = method.invoke(implementObject, args);
		
		After after = aopConfig.getAfter();
		if(after != null){
			aopObject = after.cutClass().newInstance();
			aopMethod = ReflectHelper.getMethod(aopObject, after.cutMethod(), AopParam.class);
			if(aopParam == null){
				aopParam = new AopParam(args);
			}else{
				aopParam = new AopParam(aopParam.getParams());
			}
			aopParam.setResult(invokeReturn);
			aopParam = (AopParam) ReflectHelper.invokeMethod(aopObject, aopMethod, aopParam);
			return aopParam.getResult();
		}
		
		return invokeReturn;
	}

}
