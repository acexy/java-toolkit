package org.thankjava.toolkit.aop.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.thankjava.toolkit.aop.entity.AopConfig;


/**
 * 缓存一些aop扫描的配置信息，避免每次扫描aop信息可以提高代码性能
* <p>Function: Cache</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年8月17日 下午5:45:04
* @version 1.0
 */
public class Cache {
	
	
	/**
	 * 已经被扫描过的类凭证
	 */
	private static ArrayList<Class<?>> scannedClass = new ArrayList<Class<?>>();
	
	private static Map<String, AopConfig> aopConfigs;
	
	static {
		aopConfigs = new HashMap<String, AopConfig>();
	}
	
	
	/**
	 * 检查该类是否已经被扫描aop配置
	* <p>Function: isScannedClass</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月17日 下午5:44:41
	* @version 1.0
	* @param clazz
	* @return
	 */
	public static boolean isScannedClass(Class<?> clazz){
		return scannedClass.contains(clazz);
	}
	
	public static void put(AopConfig aopConfig){
		aopConfigs.put(aopConfig.getClassPath() + aopConfig.getMethodName() + aopConfig.getArgs(), aopConfig);
	}
	
	public static AopConfig getAop(String key){
		return aopConfigs.get(key);
	}
}
