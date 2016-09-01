package org.thankjava.toolkit3d.utils.aop.core;

import java.lang.reflect.Method;

import org.thankjava.toolkit.utils.reflect.ReflectHelper;
import org.thankjava.toolkit3d.utils.aop.anno.After;
import org.thankjava.toolkit3d.utils.aop.anno.Before;
import org.thankjava.toolkit3d.utils.aop.cache.Cache;
import org.thankjava.toolkit3d.utils.aop.entity.AopParam;
import org.thankjava.toolkit3d.utils.aop.entity.CutPointConfig;


class InvokeBreankMethod {


	public AopParam before(Before before, Object[] args){
		try {
			String classPath = before.cutClass().getName();
			Object cutObj = null;
			cutObj = Cache.getCutPoint(classPath);
			
			if(cutObj == null){
				//第一次初始化切片对象
				cutObj = before.cutClass().newInstance();
				Cache.put(new CutPointConfig(classPath, cutObj));
			}else{
				cutObj = ((CutPointConfig)cutObj).getCutPointObj();
			}
			
			Method cutMethod = ReflectHelper.getMethod(cutObj, before.cutMethod(), AopParam.class);
			AopParam param = new AopParam(args);
			param = (AopParam) ReflectHelper.invokeMethod(cutObj, cutMethod, param);
			return param;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	

	public Object after(After after,Object invokeResult){
		try {
			String classPath = after.cutClass().getName();
			Object cutObj = null;
			cutObj = Cache.getCutPoint(classPath);
			if(cutObj == null){
				cutObj = after.cutClass().newInstance();
				Cache.put(new CutPointConfig(classPath, cutObj));
			}else{
				cutObj = ((CutPointConfig)cutObj).getCutPointObj();
			}
			Method cutMethod = ReflectHelper.getMethod(cutObj, after.cutMethod(), AopParam.class);
			AopParam param = new AopParam(invokeResult);
			ReflectHelper.invokeMethod(cutObj, cutMethod, param);
			return param.getResult();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}
