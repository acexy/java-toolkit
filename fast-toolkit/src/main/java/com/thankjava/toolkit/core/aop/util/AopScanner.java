package com.thankjava.toolkit.core.aop.util;

import com.thankjava.toolkit.bean.aop.anno.After;
import com.thankjava.toolkit.bean.aop.anno.Before;
import com.thankjava.toolkit.bean.aop.entity.AopCache;
import com.thankjava.toolkit.bean.aop.entity.AopConfig;
import com.thankjava.toolkit.core.reflect.ReflectHelper;

import java.lang.reflect.Method;

public class AopScanner {

    /**
     * 将被代理的类的方法进行扫描，检测是否有aop的配置
     * <p>Function: scanner</p>
     * <p>Description: </p>
     *
     * @param implementObjectClass
     * @author acexy@thankjava.com
     * @date 2016年8月17日 下午5:51:04
     * @version 1.0
     */
    public static void scanner(Class<?> implementObjectClass, Class<?>... proxyClass) {

        if (AopCache.isScannedClass(implementObjectClass)) {
            return;
        }
        Method[] methods = ReflectHelper.getAllMethod(implementObjectClass);

        if (methods != null) {

            AopConfig config = null;
            Before before = null;
            After after = null;
            Class<?>[] argsType;

            for (Method method : methods) {

                config = new AopConfig();
                argsType = method.getParameterTypes();

                for (Class<?> clazz : argsType) {
                    config.setArgs(clazz.getName());
                }

                before = method.getAnnotation(Before.class);
                after = method.getAnnotation(After.class);

                config.setMethodName(method.getName());
                if (proxyClass != null && proxyClass.length > 0) {
                    config.setClassPath(proxyClass[0].getName());
                } else {
                    config.setClassPath(implementObjectClass.getName());
                }

                if (before == null && after == null) {
                    config.setUsedAop(false);
                } else {
                    config.setUsedAop(true);
                    if (before != null) {
                        config.setBefore(before);
                        try {
                            Object instance = AopCache.getCutPoint(before.cutClass());
                            if (instance != null) {
                                config.setBeforeInstance(instance);
                            } else {
                                instance = before.cutClass().newInstance();
                                config.setBeforeInstance(instance);
                                AopCache.putCutPoint(instance);
                            }
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (after != null) {
                        config.setAfter(after);
                        try {
                            Object instance = AopCache.getCutPoint(after.cutClass());
                            if (instance != null) {
                                config.setAfterInstance(instance);
                            } else {
                                instance = after.cutClass().newInstance();
                                config.setAfterInstance(instance);
                                AopCache.putCutPoint(instance);
                            }
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

                AopCache.putAopConfig(config);
            }
        }
    }

}
