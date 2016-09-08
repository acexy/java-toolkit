package org.thankjava.toolkit3d.aop.entity;

public class CutPointConfig {

	private String classPath;
	
	private Object cutPointObj;

	public String getClassPath() {
		return classPath;
	}

	public Object getCutPointObj() {
		return cutPointObj;
	}

	public CutPointConfig(String classPath, Object cutPointObj) {
		this.classPath = classPath;
		this.cutPointObj = cutPointObj;
	}
	
}
