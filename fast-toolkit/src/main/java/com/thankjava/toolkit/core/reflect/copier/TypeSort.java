package com.thankjava.toolkit.core.reflect.copier;

import java.util.*;

import com.thankjava.toolkit.bean.reflect.copier.ClassType;
import com.thankjava.toolkit.core.reflect.ReflectUtil;


/**
 * 用于确认字段类型
 * <p>Function: BeanCopyTypeSort</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2015-1-27 下午3:40:16
 */
class TypeSort {

    private static boolean isBaseJDKSimpleType(Class<?> type) {
        if (String.class == type) {
            return true;
        } else if (Integer.class == type || int.class == type) {
            return true;
        } else if (Long.class == type || long.class == type) {
            return true;
        } else if (Double.class == type || double.class == type) {
            return true;
        } else if (Boolean.class == type || boolean.class == type) {
            return true;
        } else if (Float.class == type || float.class == type) {
            return true;
        } else if (Character.class == type || char.class == type) {
            return true;
        } else if (Byte.class == type || byte.class == type) {
            return true;
        } else if (Short.class == type || short.class == type) {
            return true;
        } else if (Date.class == type) {
            return true;
        }
        return false;
    }


    private static boolean isBaseJDKMapType(Class<?> type) {
        return Map.class == type || AbstractMap.class == type;
    }

    private static boolean isBaseJDKListType(Class<?> type) {
        return List.class == type || AbstractCollection.class == type;
    }

    static ClassType sortType(Class<?> type) {

        if (isBaseJDKSimpleType(type)) {
            return ClassType.JDK_BASE;
        }
        if (type.isArray()) {
            return ClassType.JDK_ARRAY;
        }
        if (type.isEnum()) {
            return ClassType.JDK_ENUM;
        }

        type = ReflectUtil.getSignificantSupperClass(type);

        if (isBaseJDKListType(type)) {
            return ClassType.JDK_LIST;
        }

        if (isBaseJDKMapType(type)) {
            return ClassType.JDK_MAP;
        }

        return ClassType.T;
    }
}
