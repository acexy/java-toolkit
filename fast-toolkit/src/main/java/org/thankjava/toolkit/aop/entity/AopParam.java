package org.thankjava.toolkit.aop.entity;


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
	 * aop切片执行Befor时，判断是否需要继续执行被代理的源方法
	 * 若代理的方法设置为不执行，After切片将不会生效
	 */
	private boolean isInvokeProxyMethod = true;
	
	
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
	
	
	
}
