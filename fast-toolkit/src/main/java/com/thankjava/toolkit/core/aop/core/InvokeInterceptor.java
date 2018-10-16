package com.thankjava.toolkit.core.aop.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.thankjava.toolkit.bean.aop.anno.After;
import com.thankjava.toolkit.bean.aop.anno.Before;
import com.thankjava.toolkit.bean.aop.entity.AopCache;
import com.thankjava.toolkit.bean.aop.entity.AopConfig;
import com.thankjava.toolkit.bean.aop.entity.AopArgs;
import com.thankjava.toolkit.bean.aop.util.AopScanner;
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
class InvokeInterceptor implements InvocationHandler {

    private Object implementObject;

    public InvokeInterceptor(Object implementObject) {
        this.implementObject = implementObject;
        AopScanner.scanner(implementObject);
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        AopConfig aopConfig = AopCache.getAopConfig(implementObject, method, args);

        if (!aopConfig.isUsedAop()) {
            return method.invoke(implementObject, args);
        }

        Before before = aopConfig.getBefore();
        Method aopMethod = null;
        Object aopInstance = null;
        AopArgs aopArgs = new AopArgs(args);
        Object invokeReturn = null;
        if (before != null) { //执行before
            aopInstance = aopConfig.getBeforeInstance();
            aopMethod = ReflectHelper.getMethod(aopInstance.getClass(), before.cutMethod(), AopArgs.class);
            aopArgs.setProxyInstance(aopConfig.getProxyInstance());
            ReflectHelper.invokeMethod(aopInstance, aopMethod, aopArgs);

            if (aopArgs.isInvokeProxyMethod()) {
                invokeReturn = method.invoke(implementObject, aopArgs.getInvokeArgs());
            }
        } else {
            aopArgs = new AopArgs(args);
            invokeReturn = method.invoke(implementObject, args);
        }

        //执行被代理的方法
        After after = aopConfig.getAfter();
        if (after != null) {
            aopInstance = aopConfig.getAfterInstance();
            aopMethod = ReflectHelper.getMethod(aopInstance.getClass(), after.cutMethod(), AopArgs.class);
            aopArgs.setProxyInstance(aopConfig.getProxyInstance());
            aopArgs.setOrigReturnResult(invokeReturn);
            ReflectHelper.invokeMethod(aopInstance, aopMethod, aopArgs);
            if (aopArgs.getOrigReturnResult() != null) {
                invokeReturn = aopArgs.getOrigReturnResult();
            }
            return invokeReturn;
        }
        return invokeReturn;
    }

}
