package com.thankjava.toolkit3d.core.aop.core;

import java.lang.reflect.Method;

import com.thankjava.toolkit.core.reflect.ReflectHelper;
import com.thankjava.toolkit3d.core.aop.anno.After;
import com.thankjava.toolkit3d.core.aop.anno.Before;
import com.thankjava.toolkit3d.core.aop.entity.AopConfig;
import com.thankjava.toolkit3d.core.aop.entity.AopParam;


class InvokeBreankMethod {


	public AopParam before(AopConfig aopConfig, Before before, AopParam param){
		Object aopInstance = aopConfig.getAopBeforeInstance();
		Method cutMethod = ReflectHelper.getMethod(aopInstance.getClass(), before.cutMethod(), AopParam.class);
		param = (AopParam) ReflectHelper.invokeMethod(aopInstance, cutMethod, param);
		return param;
	}
	

	public AopParam after(AopConfig aopConfig, After after, AopParam param, Object invokeResult){
		Object aopInstance = aopConfig.getAopAfterInstance();
		Method cutMethod = ReflectHelper.getMethod(aopInstance.getClass(), after.cutMethod(), AopParam.class);
		return (AopParam) ReflectHelper.invokeMethod(aopInstance, cutMethod, param);
	}
}
