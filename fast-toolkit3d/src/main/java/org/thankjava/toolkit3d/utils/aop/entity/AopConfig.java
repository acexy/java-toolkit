package org.thankjava.toolkit3d.utils.aop.entity;

import org.thankjava.toolkit3d.utils.aop.anno.After;
import org.thankjava.toolkit3d.utils.aop.anno.Before;

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
	
	
	
}
