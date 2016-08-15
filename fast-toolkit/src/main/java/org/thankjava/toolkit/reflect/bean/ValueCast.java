package org.thankjava.toolkit.reflect.bean;

import java.lang.reflect.Field;

import org.thankjava.toolkit.reflect.BeanCopier;

public class ValueCast {

	/**
	 * 将原始类型的参数值转换成目标类型的对等参数值
	* <p>Function: cast</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月11日 上午11:07:37
	* @version 1.0
	* @param targetField
	* @param targetObject
	* @param originValue
	* @return
	 */
	public static Object cast(Field targetField,Object targetObject,Object originValue){
		return createValueCore(targetField,targetField.getType(),targetObject, originValue);
	}
	
	
	static Object createValueCore(Field targetField,Class<?> targetFieldType,Object targetObject,Object originValue){
		
		switch (TypeSort.sortType(targetFieldType)) {
		
		case JDK_BASE:
			return originValue;
		case JDK_ARRAY:
			return ValueFactory.createValueArray(targetField,targetFieldType,targetObject,originValue);
		case JDK_ENUM:
			return ValueFactory.createValueEnum(targetField,targetFieldType,targetObject,originValue);
		case JDK_LIST:
			return ValueFactory.createValueList(targetField,targetFieldType,targetObject,originValue);
		case JDK_MAP:
			return ValueFactory.createValueMap(targetField, targetFieldType, targetObject, originValue);
		default: //T
			return BeanCopier.copy(originValue,targetFieldType);
		}
		
	}
	
	
}
