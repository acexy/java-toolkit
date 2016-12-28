package com.thankjava.toolkit3d.aop.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.thankjava.toolkit3d.aop.anno.After;
import com.thankjava.toolkit3d.aop.anno.Before;
import com.thankjava.toolkit3d.aop.cache.Cache;
import com.thankjava.toolkit3d.aop.entity.AopConfig;


/**
 * 用于扫码缓存的aop配置和加载历史配置
* <p>Function: AopConfigResolve</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年5月16日 上午11:07:32
* @version 1.0
 */
class AopConfigResolve {
	
	
	AopConfig config(Object obj, Method method){
		
		AopConfig config = Cache.getAop(getAopConfgKey(obj, method));
		
		//缓存的Aop切片配置信息不存在，则该方法是第一次被扫描
		Class<?>[] argsType;
		if(config == null){
			config = new AopConfig();
			config.setMethodName(method.getName());
			config.setClassPath(obj.getClass().getName());
			argsType = method.getParameterTypes();
			for (Class<?> clazz : argsType) {
				config.setArgs(clazz.getName());
			}
			
			Annotation[] anns = method.getAnnotations();
			if(anns == null || anns.length == 0){
				//没有配置注解内容，肯定没有使用Aop
				config.setUsedAop(false);
			}else{
				config.setBefore(method.getAnnotation(Before.class));
				config.setAfter(method.getAnnotation(After.class));
		
				if(config.getBefore() == null && config.getAfter() == null){
					//并没有Aop对应的注解配置
					config.setUsedAop(false);
				}else{
					config.setProxyInstance(obj);
					if(config.getBefore() != null){
						try {
							config.setAopBeforeInstance(config.getBefore().cutClass().newInstance());
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
					if(config.getAfter() != null){
						try {
							config.setAopAfterInstance(config.getAfter().cutClass().newInstance());
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}
					}
					config.setUsedAop(true);
				}
			}
			
			Cache.put(config);
			if(!config.isUsedAop()){
				return null;
			}
		} else if(!config.isUsedAop()){
			return null;
		}
		return config;
	}
	
	/**
	 * 创建Key
	* <p>Function: getAopConfgKey</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月22日 下午3:52:36
	* @version 1.0
	* @param proxy
	* @param method
	* @param args
	* @return
	 */
	private String getAopConfgKey(Object proxy, Method method){
		StringBuffer sb = new StringBuffer();
		sb.append(proxy.getClass().getName());
		sb.append(method.getName());
		Class<?>[] argsType = method.getParameterTypes();
		for (Class<?> clazz : argsType) {
			sb.append(clazz.getName());
		}
		return sb.toString();
	}
	
}
