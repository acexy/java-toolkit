package com.thankjava.toolkit3d.aop.cache;

import java.util.HashMap;
import java.util.Map;

import com.thankjava.toolkit3d.aop.entity.AopConfig;

/**
 * 该类主要是用于在检索切片信息时起到一次扫描后续读取,以及对切片类做单实例处理
* <p>Function: Cache</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2016年5月16日 上午11:06:28
* @version 1.0
 */
public class Cache {

	private static Map<String, AopConfig> aopConfigs;
	
	static {
		aopConfigs = new HashMap<String, AopConfig>();
	}
	
	public static void put(AopConfig aopConfig){
		aopConfigs.put(aopConfig.getClassPath() + aopConfig.getMethodName() + aopConfig.getArgs(), aopConfig);
	}
	
	public static AopConfig getAop(String key){
		return aopConfigs.get(key);
	}
}
