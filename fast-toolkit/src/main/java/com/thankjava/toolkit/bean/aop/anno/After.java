package com.thankjava.toolkit.bean.aop.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

/**
 * 该注解用于声明 使用该注解的方法 在执行前 将执行自定义aop切片
 * <p>Function: After</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 */
public @interface After {

	Class<?> cutClass();
	String cutMethod();
	
}
