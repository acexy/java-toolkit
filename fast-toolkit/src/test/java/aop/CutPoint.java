package aop;

import com.thankjava.toolkit.bean.aop.entity.AopArgs;

public class CutPoint {

    /**
     * before 案例
     * 传入和返回值必须为AopParam类型
     * <p>Function: before</p>
     * <p>Description: </p>
     *
     * @param param
     * @return
     * @author acexy@thankjava.com
     * @date 2016年8月17日 下午7:58:46
     * @version 1.0
     */
    public void before(AopArgs param) {

        //通过该方法获取原始被拦截的方法的传入参数
        String origStr = (String) param.getInvokeArgs()[0];

        //修改被拦截的方法所能获得的参数 并且在after 切片中也只能获得被修改后的参数
        System.out.println("我是切片before，原来传递的参数: " + origStr + " 被我修改成了：proxy");
        param.setInvokeArgs(new Object[]{"proxy"});


        //可以设置被拦截的方法到底是否需要执行 (该属性只有Before切片有用)
        param.setInvokeProxyMethod(true);

    }

    public void after(AopArgs param) {

        // 获取被拦截的方法执行后的返回结果 (如果被代理的方法含有before切片，需要该切片允许执行被代理的方法才有该返回值)
        String result = (String) param.getOrigReturnResult();

        //设置返回值 如果你想修改被拦截的方法的源生返回值
        param.setOrigReturnResult("proxy_result");
        System.out.println("我是参数after，原来返回的参数: " + result + " 将被我修改为 proxy_result");
    }

}
