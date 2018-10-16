package com.thankjava.toolkit.core.reflect.copier;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import com.thankjava.toolkit.core.reflect.ReflectHelper;



/**
 * 用于确认字段类型
* <p>Function: BeanCopyTypeSort</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2015-1-27 下午3:40:16
* @version 1.0
 */
class TypeSort {
	
	
	private static boolean isBaseJDKSimpleType(Class<?> type) {
		if (String.class == type) {
			return true;
		}
		if (Integer.class == type || int.class == type) {
			return true;
		}
		if (Long.class == type || long.class == type) {
			return true;
		}
		if (Double.class == type || double.class == type) {
			return true;
		}
		if (Boolean.class == type || boolean.class == type) {
			return true;
		}
		if (Float.class == type || float.class == type) {
			return true;
		}
		if (Character.class == type || char.class == type) {
			return true;
		}
		if (Byte.class == type || byte.class == type) {
			return true;
		}
		if (Short.class == type || short.class == type) {
			return true;
		}
		return false;
	}

	
	
	private static boolean isBaseJDKMapType(Class<?> type) {
		if (Map.class == type || AbstractMap.class == type) {
			return true;
		}
		return false;
	}

	private static boolean isBaseJDKListType(Class<?> type){
		if (List.class == type || AbstractCollection.class == type) {
			return true;
		}
		return false;
	}
	
	
	static ClassType sortType(Class<?> type){
		
		if (isBaseJDKSimpleType(type)) {
			return ClassType.JDK_BASE;
		}
		if(type.isArray()){
			return ClassType.JDK_ARRAY;
		}
		if(type.isEnum()){
			return ClassType.JDK_ENUM;
		}
		
		type = ReflectHelper.getSignificantSupperClass(type);
		
		if(isBaseJDKListType(type)){
			return ClassType.JDK_LIST;
		}
		
		if(isBaseJDKMapType(type)){
			return ClassType.JDK_MAP;
		}
		
		return ClassType.T;
	}
}
