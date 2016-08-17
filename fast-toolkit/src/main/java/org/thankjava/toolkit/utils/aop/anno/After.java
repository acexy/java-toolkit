package org.thankjava.toolkit.utils.aop.anno;

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
* @author zhaoxy@thankjava.com
* @date 2016年5月16日 上午11:04:51
* @version 1.0
 */
public @interface After {

	Class<?> cutClass();
	String cutMethod();
	
}
