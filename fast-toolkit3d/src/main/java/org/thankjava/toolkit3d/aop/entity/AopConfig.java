package org.thankjava.toolkit3d.aop.entity;

import org.thankjava.toolkit3d.aop.anno.After;
import org.thankjava.toolkit3d.aop.anno.Before;

public class AopConfig {
	
	/**
	 * class位置
	 */
	private String classPath;
	
	/**
	 * 执行的方法名
	 */
	private String methodName;
	
	/**
	 * 传入的参数类型拼接
	 */
	private String args = "";
	
	private Object aopBeforeInstance = null;
	
	private Object aopAfterInstance = null;
	
	/**
	 * 扫描注解注入的事件配置
	 */
	
	private Before before = null;
	private After after = null;
	
	private boolean isUsedAop = false;
	
	public String getClassPath() {
		return classPath;
	}
	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Before getBefore() {
		return before;
	}
	public void setBefore(Before before) {
		this.before = before;
	}
	public After getAfter() {
		return after;
	}
	public void setAfter(After after) {
		this.after = after;
	}
	public boolean isUsedAop() {
		return isUsedAop;
	}
	public void setUsedAop(boolean isUsedAop) {
		this.isUsedAop = isUsedAop;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = this.args + args;
	}
	public Object getAopBeforeInstance() {
		return aopBeforeInstance;
	}
	public void setAopBeforeInstance(Object aopBeforeInstance) {
		this.aopBeforeInstance = aopBeforeInstance;
	}
	public Object getAopAfterInstance() {
		return aopAfterInstance;
	}
	public void setAopAfterInstance(Object aopAfterInstance) {
		this.aopAfterInstance = aopAfterInstance;
	}
}
