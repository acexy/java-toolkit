package com.thankjava.toolkit.bean.aop.entity;

import com.thankjava.toolkit.bean.aop.anno.After;
import com.thankjava.toolkit.bean.aop.anno.Before;

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

    /**
     * before切片实例
     */
    private Object beforeInstance;

    /**
     * after切片实例
     */
    private Object afterInstance;

    /**
     * 被代理的对象实例
     */
    private Before before;
    private After after;
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

    public Object getBeforeInstance() {
        return beforeInstance;
    }

    public void setBeforeInstance(Object beforeInstance) {
        this.beforeInstance = beforeInstance;
    }

    public Object getAfterInstance() {
        return afterInstance;
    }

    public void setAfterInstance(Object afterInstance) {
        this.afterInstance = afterInstance;
    }

}
