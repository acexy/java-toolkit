package com.thankjava.toolkit.core.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.thankjava.toolkit.bean.aop.entity.AopCache;
import com.thankjava.toolkit.bean.aop.entity.AopConfig;
import com.thankjava.toolkit.bean.aop.entity.AopArgs;
import com.thankjava.toolkit.core.aop.util.AopScanner;
import com.thankjava.toolkit.core.aop.util.BreakMethod;


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
        AopScanner.scanner(implementObject.getClass());
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        AopConfig aopConfig = AopCache.getAopConfig(implementObject, method);

        if (aopConfig == null) {
            return method.invoke(implementObject, args);
        }

        if (!aopConfig.isUsedAop()) {
            return method.invoke(implementObject, args);
        }

        AopArgs aopArgs = new AopArgs(args);
        aopArgs.setProxyInstance(proxy);


        BreakMethod.invokeBeforeCutPoint(aopConfig, aopArgs);
        Object invokeReturn = aopArgs.getReturnResult();

        if (aopArgs.isInvokeProxyMethod()) {
            invokeReturn = method.invoke(implementObject, aopArgs.getInvokeArgs());
        }

        aopArgs.setReturnResult(invokeReturn);

        BreakMethod.invokeAfterCutPoint(aopConfig, aopArgs);

        return aopArgs.getReturnResult();
    }

}
