package org.thankjava.toolkit3d.utils.aop.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.thankjava.toolkit3d.utils.aop.anno.After;
import org.thankjava.toolkit3d.utils.aop.anno.Before;
import org.thankjava.toolkit3d.utils.aop.cache.Cache;
import org.thankjava.toolkit3d.utils.aop.entity.AopConfig;


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
		
		String key = obj.getClass().getName() + method.getName();
		AopConfig config = Cache.getAop(key);
		
		//缓存的Aop切片配置信息不存在，则该方法是第一次被扫描
		if(config == null){
			config = new AopConfig();
			config.setMethodName(method.getName());
			config.setClassPath(obj.getClass().getName());
			
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
	
}
