package org.thankjava.toolkit3d.utils.aop.cache;

import java.util.HashMap;
import java.util.Map;

import org.thankjava.toolkit3d.utils.aop.entity.AopConfig;
import org.thankjava.toolkit3d.utils.aop.entity.CutPointConfig;

/**
 * 该类主要是用于在检索切片信息时起到一次扫描后续读取,以及对切片类做单实例处理
* <p>Function: Cache</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年5月16日 上午11:06:28
* @version 1.0
 */
public class Cache {

	private static Map<String, AopConfig> aopConfigs;
	private static Map<String, CutPointConfig> cutPointClass;
	
	static {
		aopConfigs = new HashMap<String, AopConfig>();
		cutPointClass = new HashMap<String, CutPointConfig>();
	}
	
	public static void put(AopConfig aopConfig){
		aopConfigs.put(aopConfig.getClassPath() + aopConfig.getMethodName(), aopConfig);
	}
	
	public static AopConfig getAop(String key){
		return aopConfigs.get(key);
	}
	
	public static void put(CutPointConfig cutPointConfig){
		cutPointClass.put(cutPointConfig.getClassPath(), cutPointConfig);
	}
	
	public static CutPointConfig getCutPoint(String key){
		return cutPointClass.get(key);
	}
}
