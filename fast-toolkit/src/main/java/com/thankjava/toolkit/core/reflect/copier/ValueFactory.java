package com.thankjava.toolkit.core.reflect.copier;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thankjava.toolkit.core.reflect.ReflectUtil;
import sun.reflect.generics.reflectiveObjects.GenericArrayTypeImpl;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import sun.reflect.generics.reflectiveObjects.TypeVariableImpl;
import sun.reflect.generics.reflectiveObjects.WildcardTypeImpl;

class ValueFactory {

    /**
     * 处理类型是数组类型
     * <p>Function: createValueArray</p>
     * <p>Description: </p>
     *
     * @param targetFieldType
     * @param targetObject
     * @param originValue
     * @return
     * @author acexy@thankjava.com
     * @date 2015-1-27 下午4:30:29
     */
    static Object createValueArray(Field targetField, Class<?> targetFieldType, Object targetObject, Object originValue) {
        if (originValue == null) {
            return null;
        }
        //获取数组实际容纳的的类型
        Class<?> proxyType = targetFieldType.getComponentType();
        Object[] originArray = (Object[]) originValue;
        if (originArray.length == 0) {
            return null;
        }
        Object[] targetArray = (Object[]) Array.newInstance(proxyType, originArray.length);
        for (int i = 0; i < originArray.length; i++) {
            targetArray[i] = ValueCast.createValue(targetField, proxyType, targetObject, originArray[i]);
        }
        return targetArray;
    }


    /**
     * 处理的数据是枚举类型
     * <p>Function: createValueEnum</p>
     * <p>Description: </p>
     *
     * @param targetFieldType
     * @param targetObject
     * @param originValue
     * @return
     * @author acexy@thankjava.com
     * @date 2015-1-27 下午4:58:57
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    static Object createValueEnum(Field targetField, Class<?> targetFieldType, Object targetObject, Object originValue) {
        if (originValue == null) {
            return null;
        }
        Object enumNameObj = ReflectUtil.getFieldVal(originValue, "name");//获取枚举中的name属性
        if (enumNameObj == null) {
            return null;
        }
        String enumName = (String) enumNameObj;
        return Enum.valueOf((Class<Enum>) targetFieldType, enumName);//得到目标枚举对象
    }

    /**
     * 处理的数据是集合类型
     * <p>Function: createValueList</p>
     * <p>Description: </p>
     *
     * @param targetFieldType
     * @param targetObject
     * @param originValue
     * @return
     * @throws ClassNotFoundException
     * @author acexy@thankjava.com
     * @date 2015-1-27 下午4:59:12
     */
    static Object createValueList(Field targetField, Class<?> targetFieldType, Object targetObject, Object originValue) {

        if (originValue == null) {
            return null;
        }

        List<?> originList = (List<?>) originValue;
        if (originList.size() == 0) {
            return null;
        }

        Type type = targetField.getGenericType();
        Object actualTypeArguments = ReflectUtil.getFieldVal(type, "actualTypeArguments");
        Type[] actualType = (Type[]) actualTypeArguments;
        Class<?> proxyType;

        if (actualType.length > 0) {
            if (actualType[0] instanceof Class) {
                proxyType = (Class<?>) actualType[0];
            } else {
               return originList;
            }
        } else {
            return null;
        }

        List<Object> targetList = new ArrayList<Object>();

        for (Object object : originList) {
            targetList.add(ValueCast.createValue(targetField, proxyType, targetList, object));
        }
        return targetList;

    }

    static Object createValueMap(Field targetField, Class<?> targetFieldType, Object targetObject, Object originValue) {
        if (originValue == null) {
            return null;
        }

        Map<?, ?> originMap = (Map<?, ?>) originValue;
        if (originMap.size() == 0) {
            return null;
        }

        Class<?> keyClass = null;
        Class<?> valueClass = null;

        for (Map.Entry<?, ?> entry : originMap.entrySet()) {
            keyClass = entry.getKey().getClass();
            valueClass = entry.getValue().getClass();
            break;
        }

        Map<Object, Object> targetMap = new HashMap<Object, Object>();
        for (Map.Entry<?, ?> entry : originMap.entrySet()) {
            targetMap.put(
                    ValueCast.createValue(targetField, keyClass, targetObject, entry.getKey()),
                    ValueCast.createValue(targetField, valueClass, targetObject, entry.getValue())
            );
        }
        return targetMap;
    }
}
