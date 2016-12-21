package org.thankjava.toolkit3d.aop.core;

import java.lang.reflect.Method;

import org.thankjava.toolkit3d.aop.anno.After;
import org.thankjava.toolkit3d.aop.anno.Before;
import org.thankjava.toolkit3d.aop.entity.AopConfig;
import org.thankjava.toolkit3d.aop.entity.AopParam;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

class InvokeInterceptor implements MethodInterceptor {

	private static InvokeBreankMethod breankMethod = new InvokeBreankMethod(); 
	private static AopConfigResolve resolve = new AopConfigResolve();
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		
		//Befor
		AopConfig aopConfig = resolve.config(obj, method);

		if(aopConfig == null){
			//执行代理类的方法
			return proxy.invokeSuper(obj, args);
		}else{
			AopParam param = new AopParam(args);
			param.setProxyInstance(aopConfig.getProxyInstance());
			Before before = aopConfig.getBefore();
			if(before != null){
				param = breankMethod.before(aopConfig, before, param);
				if(!param.isInvokeProxyMethod()){
					return param.getResult();
				}
				args = param.getParams();
			}
			
			Object invokeObject = proxy.invokeSuper(obj, args);
			After after = aopConfig.getAfter();
			if(after != null){
				param.setResult(invokeObject);
				return breankMethod.after(aopConfig, after, param, invokeObject).getResult();
			}
			return invokeObject;
		}
	}
	

}
