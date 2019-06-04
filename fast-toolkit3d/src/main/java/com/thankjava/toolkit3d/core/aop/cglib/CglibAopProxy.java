package com.thankjava.toolkit3d.core.aop.cglib;

import net.sf.cglib.proxy.Enhancer;


/**
 * aop切片的代理生成
 * <p>Function: JDKAopProxy</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年5月16日 上午11:08:14
 */
public class CglibAopProxy {


    /**
     * 生成代理对象 基于代理对象的无参构造函数
     * <p>Function: createProxyObject</p>
     * <p>Description: </p>
     *
     * @param proxyClass
     * @return
     * @author acexy@thankjava.com
     * @date 2016年8月17日 下午2:46:58
     * @version 1.0
     */
    @SuppressWarnings("unchecked")
    public static <T> T createProxyObject(Class<T> proxyClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(proxyClass);
        enhancer.setCallback(new InvokeInterceptor(proxyClass));
        return (T) enhancer.create();
    }
}
