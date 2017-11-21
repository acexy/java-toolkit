package com.thankjava.toolkit.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.thankjava.toolkit.reflect.copier.ValueCast;

/**
 * BeanCopier 属性对等复制
* <p>Function: BeanCopier</p>
* <p>Description: 适用于类似Po转Vo公共步骤</p>
* @author zhaoxy@thankjava.com
* @date 2016年1月11日 上午10:27:41
* @version 1.0
 */
public final class BeanCopier {
	
	private BeanCopier(){}

	/**
	 * 对等属性复制
	* <p>Function: copy</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月11日 上午10:29:26
	* @version 1.0
	* @param targetClass
	* @param originObject
	* @return
	 */
	public static <OriginObject, TargetObject> TargetObject copy(OriginObject originObject,Class<TargetObject> targetClass){
		
		//参数不合法异常
		if (originObject == null || targetClass == null) { 
			throw new IllegalArgumentException("originaObject and targetClass can't be null");
		}
		
		//目标返回对象
		TargetObject targetObject = null;
		
		try {
			targetObject = targetClass.newInstance();
		} catch (InstantiationException e) {
			throw new ClassCastException("targetClass can't be instance");
		} catch (IllegalAccessException e) {
			throw new ClassCastException("targetClass can't be instance");
		}
		
		return setValue(originObject,targetObject,targetClass);
	}
	
	/**
	 * 属性对等复制增加
	* <p>Function: append</p>
	* <p>Description: 将originObject对象中的属性复制到targetObject中,
	* 保留targetObject中原来的属性值
	* </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月22日 上午10:41:58
	* @version 1.0
	* @param originObject
	* @param targetObject
	* @return
	 */
	public static <OriginObject, TargetObject> TargetObject append(OriginObject originObject,TargetObject targetObject){
		if(originObject == null || targetObject == null){
			throw new IllegalArgumentException("originaObject and targetObject can't be null");
		}
		@SuppressWarnings("unchecked")
		Class<TargetObject> targetClass = (Class<TargetObject>) targetObject.getClass();
		return setValue(originObject,targetObject,targetClass);
		
	}
	
	/**
	 * List集合代理类型对等复制
	* <p>Function: copyList</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月11日 下午2:48:10
	* @version 1.0
	* @param originObjects
	* @param targetClass
	* @return ArrayList
	 */
	public static <OriginObject, TargetObject> List<TargetObject> copyList(List<OriginObject> originObjects,Class<TargetObject> targetClass){
		//参数不合法异常
		if (originObjects == null || targetClass == null) { 
			throw new IllegalArgumentException("originaObject and targetClass can't be null");
		}
		List<TargetObject> targetObjects = new ArrayList<TargetObject>();
		for (OriginObject originObject : originObjects) {
			targetObjects.add(copy(originObject, targetClass));
		}
		return targetObjects;
	}
	
	/**
	 * 为对象属性复制参数值
	* <p>Function: setValue</p>
	* <p>Description: 静态字段将不会处理</p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月11日 上午10:42:16
	* @version 1.0
	* @param targetObject
	* @return
	 */
	private static <OriginObject,TargetObject> TargetObject setValue(OriginObject originObject,TargetObject targetObject,Class<TargetObject> targetClass){
		
		Field[] targetFields = ReflectHelper.getFieldArrayExcludeUID(targetClass);
		
		Field originField = null; //目标字段类型
		
		Object originValue = null; //原始对象属性值
		Object targetValue = null; //目标对象属性值
		
		//实行从目标对象中找原始对象的属性方式， 
		for (Field targetField : targetFields) {
			
			originField = ReflectHelper.getField(originObject, targetField.getName()); 
			
			if(originField == null){ //目标对象有，但是原始对象中没有
				continue;
			}
			
			if(Modifier.isStatic(originField.getModifiers())){
				continue;
			}
			
			targetField.setAccessible(true);
			originField.setAccessible(true);
			
			originValue = ReflectHelper.getFieldVal(originObject, targetField.getName());
			if(originValue == null){ //从原始对象中获取的字段属性为null
				continue;
			}
			
			targetValue = ValueCast.cast(targetField, targetObject, originValue);
			
			if(targetValue != null){
				try {
					targetField.set(targetObject, targetValue);
				} catch (IllegalArgumentException e){
					throw new RuntimeException("fail to targetObject attribute assignment");
				} catch (IllegalAccessException e) {//为目标属性赋值失败
					throw new RuntimeException("fail to targetObject attribute assignment");
				}
			}
			
		}
		
		return targetObject;
	}
	
}
