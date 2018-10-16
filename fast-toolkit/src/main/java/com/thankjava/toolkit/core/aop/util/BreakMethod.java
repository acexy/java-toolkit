package com.thankjava.toolkit.core.aop.util;

import com.thankjava.toolkit.bean.aop.anno.After;
import com.thankjava.toolkit.bean.aop.anno.Before;
import com.thankjava.toolkit.bean.aop.entity.AopArgs;
import com.thankjava.toolkit.bean.aop.entity.AopConfig;
import com.thankjava.toolkit.core.reflect.ReflectHelper;

import java.lang.reflect.Method;

/**
 * 执行切片数据
 *
 * @Author: acexy@thankjava.com
 * 2018/10/16
 * @Description:
 **/
public class BreakMethod {

    public static void invokeBeforeCutPoint(AopConfig aopConfig, AopArgs aopArgs) {
        Before before = aopConfig.getBefore();
        if (before != null) {
            Object aopInstance = aopConfig.getBeforeInstance();
            Method aopMethod = ReflectHelper.getMethod(aopInstance.getClass(), aopConfig.getBefore().cutMethod(), AopArgs.class);
            ReflectHelper.invokeMethod(aopInstance, aopMethod, aopArgs);
        }
    }

    public static void invokeAfterCutPoint(AopConfig aopConfig, AopArgs aopArgs) {
        After after = aopConfig.getAfter();
        if (after != null) {
            Object aopInstance = aopConfig.getAfterInstance();
            Method aopMethod = ReflectHelper.getMethod(aopInstance.getClass(), aopConfig.getAfter().cutMethod(), AopArgs.class);
            ReflectHelper.invokeMethod(aopInstance, aopMethod, aopArgs);
        }
    }

}
