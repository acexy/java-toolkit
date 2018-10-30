package com.thankjava.toolkit.bean.aop.entity;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.thankjava.toolkit.bean.aop.entity.AopConfig;


/**
 * 缓存一些aop扫描的配置信息，避免每次扫描aop信息可以提高代码性能
 * <p>Function: AopCache</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年8月17日 下午5:45:04
 */
public class AopCache {


    /**
     * 已经被扫描过的类
     */
    private static ArrayList<Class<?>> scannedClass = new ArrayList<>();

    /**
     * 扫描到的Aop配置信息
     */
    private static Map<String, AopConfig> aopConfigs = new HashMap<>();

    /**
     * 切片配置信息
     */
    private static Map<Class<?>, Object> cutPoints = new HashMap<>();

    /**
     * 检查该类是否已经被扫描aop配置
     * <p>Function: isScannedClass</p>
     * <p>Description: </p>
     *
     * @param clazz
     * @return
     * @author acexy@thankjava.com
     * @date 2016年8月17日 下午5:44:41
     * @version 1.0
     */
    public static boolean isScannedClass(Class<?> clazz) {
        return scannedClass.contains(clazz);
    }

    public static void putAopConfig(AopConfig aopConfig) {
        aopConfigs.put(aopConfig.getClassPath() + aopConfig.getMethodName() + aopConfig.getArgs(), aopConfig);
    }

    public static AopConfig getAopConfig(Object proxy, Method method, Object[] args) {
        String key = proxy.getClass().getName();
        key += method.getName();
        if (args != null && args.length > 0) {
            for (Object obj : args) {
                key += obj.getClass().getName();
            }
        }
        return aopConfigs.get(key);
    }

    public static void putCutPoint(Object cutPoint) {
        cutPoints.put(cutPoint.getClass(), cutPoint);
    }
    public static Object getCutPoint(Class<?> cutPointClass) {
        return cutPoints.get(cutPointClass);
    }
}
