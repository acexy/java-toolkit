package com.thankjava.toolkit.core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 常用的反射操作
 * <p>Function: ReflectUtil</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2014-12-16 上午11:26:53
 */
public final class ReflectUtil {

    private ReflectUtil() {
    }

    /**
     * 获取对象指定属性信息
     * <p>Function: getField</p>
     * <p>Description: 取消安全机制限制，如果当前类不存在，则获取父类属性信息，未找到则返回null</p>
     *
     * @param clazz
     * @param fieldName
     * @return
     * @author acexy@thankjava.com
     * @date 2014-12-16 上午11:27:07
     * @version 1.0
     */
    public static Field getField(Class<?> clazz, String fieldName) {
        if (clazz == null) {
            return null;
        }
        Field field = null;
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                break;
            } catch (NoSuchFieldException e) {
                //第一次进入该异常，则clazz为obj
                //第二次进入该异常，则clazz为obj父类
                //不考虑子类
            }
        }
        return field;
    }

    /**
     * 获取指定对象的所有属性，包含父类属性
     * <p>Function: getFieldArrayExcludeUID</p>
     * <p>Description: 不抓取serialVersionUID属性</p>
     *
     * @param clazz
     * @return
     * @author acexy@thankjava.com
     * @date 2014-12-16 上午11:27:44
     */
    public static Field[] getFieldArrayIncludeSupClassExcludeUID(Class<?> clazz) {
        Field[] currField = clazz.getDeclaredFields();
        clazz = clazz.getSuperclass();
        Field[] supField = clazz.getDeclaredFields();
        Field[] temp = new Field[currField.length + supField.length];
        int length = 0;
        for (Field curr : currField) {
            if ("serialVersionUID".equals(curr.getName())) {
                continue;
            }
            temp[length] = curr;
            length++;
        }
        for (Field sup : supField) {
            if ("serialVersionUID".equals(sup.getName())) {
                continue;
            }
            temp[length] = sup;
            length++;
        }
        Field[] all = new Field[length];
        for (int i = 0; i < all.length; i++) {
            all[i] = temp[i];
        }
        return all;
    }

    /**
     * 获取指定对象的所有属性，不包含父类属性
     * <p>Function: getFieldArrayExcludeUID</p>
     * <p>Description: 不抓取serialVersionUID属性</p>
     *
     * @param clazz
     * @return
     * @author acexy@thankjava.com
     * @date 2014-12-16 上午11:27:44
     * @version 1.0
     */
    public static Field[] getFieldArrayExcludeUID(Class<?> clazz) {

        Field[] currField = clazz.getDeclaredFields();

        int len = currField.length;

        boolean existUID = false;

        Field[] temp = new Field[len];
        for (int index = 0; index < len; index++) {
            if ("serialVersionUID".equals(currField[index].getName())) {
                existUID = true;
                continue;
            }
            temp[index] = currField[index];
        }
        if (!existUID) {
            return currField;
        }
        return Arrays.copyOf(temp, len - 1);
    }

    /**
     * 获取指定类字段属性值
     * <p>Function: getFieldVal</p>
     * <p>Description: 取消安全访问限制，如果当前类不存在，则获取父类属性值  发生异常则返回NULL</p>
     *
     * @param obj
     * @param fieldName
     * @return
     * @author acexy@thankjava.com
     * @date 2014-12-16 上午11:28:20
     * @version 1.0
     */
    public static Object getFieldVal(Object obj, String fieldName) {
        Field field;
        if (obj == null) {
            return null;
        }
        try {
            field = getField(obj.getClass(), fieldName);
            field.setAccessible(true);//取消安全机制限制
            return field.get(obj);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 为字段属性设置属性值
     * <p>Function: setFieldVal</p>
     * <p>Description: </p>
     *
     * @param obj
     * @param fieldName
     * @param value
     * @author acexy@thankjava.com
     * @date 2014-12-18 上午9:52:18
     * @version 1.0
     */
    public static void setFieldVal(Object obj, String fieldName, Object value) {
        Field field;
        if (obj == null) {
            return;
        }
        try {
            field = getField(obj.getClass(), fieldName);
            field.setAccessible(true);//取消安全机制限制
            field.set(obj, value);
        } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置静态类成员变量值
     *
     * @param clazz
     * @param fieldName
     * @param value
     */
    public static void setFiledVal(Class clazz, String fieldName, Object value) {
        Field field = getField(clazz, fieldName);
        if (field == null) {
            return;
        }
        field.setAccessible(true);
        try {
            field.set(null, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取指定方法
     * <p>Function: getMethod</p>
     * <p>Description: </p>
     *
     * @param clazz
     * @param methodName
     * @param parameterTypes
     * @return
     * @author acexy@thankjava.com
     * @date 2014-12-18 上午9:55:54
     * @version 1.0
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        if (clazz == null) {
            return null;
        }
        try {
            return clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (SecurityException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取obj中所有方法函数
     * <p>Function: getAllMethod</p>
     * <p>Description: </p>
     *
     * @param obj
     * @return
     * @author acexy@thankjava.com
     * @date 2016年8月17日 下午5:49:53
     * @version 1.0
     */
    public static Method[] getAllMethod(Object obj) {
        if (obj == null) {
            return null;
        }
        Class<?> clazz = obj.getClass();
        try {
            return clazz.getDeclaredMethods();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定类类型的所有方法函数
     * @param clazz
     * @return
     */
    public static Method[] getAllMethod(Class<?> clazz) {
        if (clazz == null) {
            return null;
        }
        try {
            return clazz.getDeclaredMethods();
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 执行指定方法
     * <p>Function: invokeMethod</p>
     * <p>Description: </p>
     *
     * @param obj
     * @param method
     * @param parameter
     * @author acexy@thankjava.com
     * @date 2014-12-18 下午1:50:06
     * @version 1.0
     */
    public static Object invokeMethod(Object obj, Method method, Object... parameter) {
        try {
            method.setAccessible(true);
            return method.invoke(obj, parameter);
        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取父级的Class
     * <p>Function: getSignificantSupperClass</p>
     * <p>Description: </p>
     *
     * @param classType
     * @return
     * @author acexy@thankjava.com
     * @date 2015年6月18日 上午10:02:35
     * @version 1.0
     */
    public static Class<?> getSignificantSupperClass(Class<?> classType) {
        Class<?> supperClass = classType;
        while (classType != null) {
            classType = classType.getSuperclass();
            if (Object.class == classType || null == classType) {
                break;
            }
            supperClass = classType;
        }
        return supperClass;
    }
}