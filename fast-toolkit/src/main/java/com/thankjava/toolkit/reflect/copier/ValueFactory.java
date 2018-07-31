package com.thankjava.toolkit.reflect.copier;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
//import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thankjava.toolkit.reflect.ReflectHelper;

class ValueFactory {

	/**
	 * 处理类型是数组类型
	* <p>Function: createValueArray</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2015-1-27 下午4:30:29
	* @version 1.0
	* @param targetFieldType
	* @param targetObject
	* @param originValue
	* @return
	 */
	static Object createValueArray(Field targetField,Class<?> targetFieldType,Object targetObject,Object originValue){
		if(originValue == null){
			return null;
		}
		//获取数组实际容纳的的类型
		Class<?> proxyType = targetFieldType.getComponentType();
		Object[] originArray = (Object[]) originValue;
		if(originArray.length == 0){
			return null;
		}
		Object[] targetArray = (Object[]) Array.newInstance(proxyType, originArray.length);
		for (int i = 0 ; i < originArray.length ; i ++) {
			targetArray[i] = ValueCast.createValueCore(targetField,proxyType,targetObject,originArray[i]);
		}
		return targetArray;
	}
	
	
	/**
	 * 处理的数据是枚举类型
	* <p>Function: createValueEnum</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2015-1-27 下午4:58:57
	* @version 1.0
	* @param targetFieldType
	* @param targetObject
	* @param originValue
	* @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	static Object createValueEnum(Field targetField,Class<?> targetFieldType,Object targetObject,Object originValue){
		if(originValue == null){
			return null;
		}
		Object enumNameObj = ReflectHelper.getFieldVal(originValue,"name");//获取枚举中的name属性
		if(enumNameObj == null){
			return null;
		}
		String enumName = (String)enumNameObj;
		return Enum.valueOf((Class<Enum>)targetFieldType,enumName);//得到目标枚举对象
	}
	
	/**
	 * 处理的数据是集合类型
	* <p>Function: createValueList</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2015-1-27 下午4:59:12
	* @version 1.0
	* @param targetFieldType
	* @param targetObject
	* @param originValue
	* @return
	 * @throws ClassNotFoundException 
	 */
	static Object createValueList(Field targetField,Class<?> targetFieldType,Object targetObject,Object originValue) {
		if(originValue == null){
			return null;
		}
		
		List<?> originList = (List<?>)originValue;
		if(originList.size() == 0){
			return null;
		}
		
		Class<?> proxyType = originList.get(0).getClass();

//		Type type = targetField.getGenericType();
//		Type[] types = (Type[]) ReflectHelper.getFieldVal(type, "actualTypeArguments");
//		System.out.println(types);
//		Class<?> proxyType = null;
//		try {
//			System.out.println(ReflectHelper.getFieldVal(types[0], "name").toString());
//			proxyType = Class.forName(ReflectHelper.getFieldVal(types[0], "name").toString());
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		}
		
		
		List<Object> targetList = new ArrayList<Object>();
		
		for (Object object : originList) {
			targetList.add(ValueCast.createValueCore(targetField, proxyType, targetList, object));
		}
		return targetList;
		
	}	
	
	static Object createValueMap(Field targetField,Class<?> targetFieldType,Object targetObject,Object originValue){
		if(originValue == null){
			return null;
		}
		
		Map<?, ?> originMap = (Map<?, ?>)originValue;
		if(originMap.size() == 0){
			return null;
		}
		
		Class<?> keyClass = null;
		Class<?> valueClass = null;
		
		for(Map.Entry<?, ?> entry : originMap.entrySet()){
			keyClass = entry.getKey().getClass();
			valueClass = entry.getValue().getClass();
			break;
		}
		
		Map<Object, Object> targetMap = new HashMap<Object, Object>();
		for(Map.Entry<?, ?> entry : originMap.entrySet()){
			targetMap.put(
					ValueCast.createValueCore(targetField,keyClass, targetObject, entry.getKey()), 
					ValueCast.createValueCore(targetField,valueClass, targetObject, entry.getValue())
					);
		}
		return targetMap;
	}
}
