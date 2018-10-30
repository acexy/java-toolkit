package aop;

import com.thankjava.toolkit3d.core.aop.CglibAopProxy;

public class CglibAopProxyTest {

    public static void main(String[] args) {

        Business business = CglibAopProxy.createProxyObject(Business.class);

        System.out.println("最终执行函数返回值为：" + business.exe("exe"));
        System.out.println();
        System.out.println("最终执行函数返回值为：" + business.exe());
        System.out.println();
        business.invoke();
    }
}
