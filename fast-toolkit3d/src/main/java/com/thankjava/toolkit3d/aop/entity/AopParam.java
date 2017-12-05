package com.thankjava.toolkit3d.aop.entity;


public class AopParam {

	/**
	 * 获得拦截的方法传入的参数
	 */
	private Object[] params;

	/**
	 * 被代理的源方法的返回值
	 */
	private Object result;
	
	/**
	 * aop切片执行Before时，判断是否需要继续执行被代理的源方法
	 * 若代理的方法设置为不执行，After切片将不会生效
	 */
	private boolean isInvokeProxyMethod = true;
	
	/**
	 * 被代理的对象实例
	 */
	private Object proxyInstance = null;
	
	public AopParam(Object[] params) {
		this.params = params;
	}
	public AopParam(Object result) {
		this.result = result;
	}
	public Object[] getParams() {
		return params;
	}
	public void setParams(Object[] params) {
		this.params = params;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	public boolean isInvokeProxyMethod() {
		return isInvokeProxyMethod;
	}
	public void setInvokeProxyMethod(boolean isInvokeProxyMethod) {
		this.isInvokeProxyMethod = isInvokeProxyMethod;
	}
	public Object getProxyInstance() {
		return proxyInstance;
	}
	public void setProxyInstance(Object proxyInstance) {
		this.proxyInstance = proxyInstance;
	}
	
	
}
