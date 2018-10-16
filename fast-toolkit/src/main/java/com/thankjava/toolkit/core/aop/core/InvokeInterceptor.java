package com.thankjava.toolkit.core.aop.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.thankjava.toolkit.core.aop.anno.After;
import com.thankjava.toolkit.core.aop.anno.Before;
import com.thankjava.toolkit.core.aop.cache.Cache;
import com.thankjava.toolkit.core.aop.entity.AopConfig;
import com.thankjava.toolkit.core.aop.entity.AopParam;
import com.thankjava.toolkit.core.reflect.ReflectHelper;


/**
 * 核心执行的拦截器
 * <p>Function: InvokeInterceptor</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年8月17日 下午2:11:06
 */
public class InvokeInterceptor implements InvocationHandler {

    private Object implementObject;

    public InvokeInterceptor(Object implementObject) {
        this.implementObject = implementObject;

        //扫描该类的注解配置信息
        if (!Cache.isScannedClass(implementObject.getClass())) {
            scanneAnno(implementObject);
        }
    }

    /**
     * 将被代理的类的方法进行扫描，检测是否有aop的配置
     * <p>Function: scanneAnno</p>
     * <p>Description: </p>
     *
     * @param implementObject
     * @author acexy@thankjava.com
     * @date 2016年8月17日 下午5:51:04
     * @version 1.0
     */
    private void scanneAnno(Object implementObject) {
        Method[] methods = ReflectHelper.getAllMethod(implementObject);
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
                config.setClassPath(implementObject.getClass().getName());

                if (before == null && after == null) {
                    config.setUsedAop(false);
                } else {
                    config.setProxyInstance(implementObject);
                    config.setUsedAop(true);
                    if (before != null) {
                        config.setBefore(before);
                        try {
                            config.setAopBeforeInstance(before.cutClass().newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                    if (after != null) {
                        config.setAfter(after);
                        try {
                            config.setAopAfterInstance(before.cutClass().newInstance());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

                Cache.put(config);
            }
        }
    }


    /**
     * 创建Key
     * <p>Function: getAopConfigKey</p>
     * <p>Description: </p>
     *
     * @param method
     * @return
     * @author acexy@thankjava.com
     * @date 2016年8月22日 下午3:52:36
     * @version 1.0
     */
    private String getAopConfigKey(Method method) {
        StringBuffer sb = new StringBuffer();
        sb.append(implementObject.getClass().getName());
        sb.append(method.getName());
        Class<?>[] argsType = method.getParameterTypes();
        for (Class<?> clazz : argsType) {
            sb.append(clazz.getName());
        }
        return sb.toString();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        AopConfig aopConfig = Cache.getAop(getAopConfigKey(method));
        if (!aopConfig.isUsedAop()) {
            return method.invoke(implementObject, args);
        }

        Before before = aopConfig.getBefore();

        Method aopMethod = null;
        Object aopInstance = null;
        AopParam aopParam = null;

        if (before != null) { //执行before
            aopInstance = aopConfig.getAopBeforeInstance();
            aopMethod = ReflectHelper.getMethod(aopInstance.getClass(), before.cutMethod(), AopParam.class);
            aopParam = new AopParam(args);
            aopParam.setProxyInstance(aopConfig.getProxyInstance());
            aopParam = (AopParam) ReflectHelper.invokeMethod(aopInstance, aopMethod, aopParam);
        }

        if (aopParam != null && !aopParam.isInvokeProxyMethod()) {
            return aopParam.getResult();
        }

        //执行被代理的方法
        Object invokeReturn = method.invoke(implementObject, args);

        After after = aopConfig.getAfter();
        if (after != null) {
            aopInstance = aopConfig.getAopAfterInstance();
            aopMethod = ReflectHelper.getMethod(aopInstance.getClass(), after.cutMethod(), AopParam.class);
            if (aopParam == null) {
                aopParam = new AopParam(args);
                aopParam.setProxyInstance(aopConfig.getProxyInstance());
            } else {
                aopParam = new AopParam(aopParam.getParams());
                aopParam.setProxyInstance(aopConfig.getProxyInstance());
            }
            aopParam.setResult(invokeReturn);
            aopParam = (AopParam) ReflectHelper.invokeMethod(aopInstance, aopMethod, aopParam);
            return aopParam.getResult();
        }

        return invokeReturn;
    }

}
