package com.thankjava.toolkit.bean.aop.entity;

/**
 * Aop参数
 */
public class AopArgs {

    public AopArgs(Object[] invokeArgs) {
        this.invokeArgs = invokeArgs;
    }

	/**
	 * 被代理的方法参数列表值
	 */
	private Object[] invokeArgs;

	/**
	 * 被代理的源方法的返回值 (如果含有before切片，需要该切片允许执行被代理的函数)
	 */
	private Object returnResult;
	
	/**
	 * aop切片执行before时，判断是否需要继续执行被代理的源方法
	 * 若代理的方法设置为不执行，After切片将不会生效
	 */
	private boolean invokeProxyMethod = true;
	
	/**
	 * 被代理的类对象实例
	 */
	private Object proxyInstance = null;


    public Object[] getInvokeArgs() {
        return invokeArgs;
    }

    public void setInvokeArgs(Object[] invokeArgs) {
        this.invokeArgs = invokeArgs;
    }

    public Object getReturnResult() {
        return returnResult;
    }

    public void setReturnResult(Object returnResult) {
        this.returnResult = returnResult;
    }

    public boolean isInvokeProxyMethod() {
        return invokeProxyMethod;
    }

    public void setInvokeProxyMethod(boolean invokeProxyMethod) {
        this.invokeProxyMethod = invokeProxyMethod;
    }

    public Object getProxyInstance() {
        return proxyInstance;
    }

    public void setProxyInstance(Object proxyInstance) {
        this.proxyInstance = proxyInstance;
    }
}
