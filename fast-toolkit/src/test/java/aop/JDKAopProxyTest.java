package aop;

import com.thankjava.toolkit.core.aop.JDKAopProxy;

public class JDKAopProxyTest {

    public static void main(String[] args) {

        /**
         * 基于JDK代理的AOP是通过面向接口层面实现
         */
        IBusiness iBusiness = JDKAopProxy.createProxyObject(IBusiness.class, new BusinessImpl());

        System.out.println("最终执行函数返回值为：" + iBusiness.exe("exe"));

        System.out.println();

        System.out.println("最终执行函数返回值为：" + iBusiness.exe());

        System.out.println();

        iBusiness.invoke();
    }
}