package org.thankjava.toolkit3d.aop.core;

import java.lang.reflect.Method;

import org.thankjava.toolkit.reflect.ReflectHelper;
import org.thankjava.toolkit3d.aop.anno.After;
import org.thankjava.toolkit3d.aop.anno.Before;
import org.thankjava.toolkit3d.aop.entity.AopConfig;
import org.thankjava.toolkit3d.aop.entity.AopParam;


class InvokeBreankMethod {


	public AopParam before(AopConfig aopConfig, Before before, Object[] args){
		Object aopInstance = aopConfig.getAopBeforeInstance();
		Method cutMethod = ReflectHelper.getMethod(aopInstance, before.cutMethod(), AopParam.class);
		AopParam param = new AopParam(args);
		param = (AopParam) ReflectHelper.invokeMethod(aopInstance, cutMethod, param);
		return param;
	}
	

	public Object after(AopConfig aopConfig, After after,Object invokeResult){
		Object aopInstance = aopConfig.getAopAfterInstance();
		Method cutMethod = ReflectHelper.getMethod(aopInstance, after.cutMethod(), AopParam.class);
		AopParam param = new AopParam(invokeResult);
		ReflectHelper.invokeMethod(aopInstance, cutMethod, param);
		return param.getResult();
	}
}
