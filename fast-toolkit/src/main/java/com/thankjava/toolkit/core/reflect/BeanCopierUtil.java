package com.thankjava.toolkit.core.reflect;

import com.thankjava.toolkit.core.reflect.copier.ValueCast;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * BeanCopierUtil 属性对等复制
 * <p>Function: BeanCopierUtil</p>
 * <p>Description: 适用于类似Po转Vo公共步骤</p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年1月11日 上午10:27:41
 */
public final class BeanCopierUtil {

    private BeanCopierUtil() {
    }

    /**
     * 对等属性复制
     * <p>Function: copy</p>
     * <p>Description: </p>
     *
     * @param targetClass
     * @param originObject
     * @return
     * @author acexy@thankjava.com
     * @date 2016年1月11日 上午10:29:26
     */
    public static <OriginObject, TargetObject> TargetObject copy(OriginObject originObject, Class<TargetObject> targetClass) {

        if (originObject == null) {
            return null;
        }

        //参数不合法异常
        if (targetClass == null) {
            throw new IllegalArgumentException("targetClass can't be null");
        }

        //目标返回对象
        TargetObject targetObject = null;

        try {
            targetObject = targetClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return setValue(originObject, targetObject, targetClass);
    }

    /**
     * 属性值重赋值
     * <p>Function: copy</p>
     * <p>Description: 将newObject对象中的相同字段名属性值复制到originObject
     * </p>
     *
     * @param originObject
     * @param newObject
     * @return
     * @author acexy@thankjava.com
     * @date 2016年12月22日 上午10:41:58
     * @version 1.0
     */
    public static <OriginObject, TargetObject> void copy(TargetObject newObject, OriginObject originObject) {
        if (originObject == null) {
            return;
        }
        if (newObject == null) {
            throw new IllegalArgumentException("targetClass can't be null");
        }
        @SuppressWarnings("unchecked")
        Class<TargetObject> targetClass = (Class<TargetObject>) newObject.getClass();
        setValue(originObject, newObject, targetClass);

    }

    /**
     * List集合代理类型对等复制
     * <p>Function: copyList</p>
     * <p>Description: </p>
     *
     * @param originObjects
     * @param targetClass
     * @return ArrayList
     * @author acexy@thankjava.com
     * @date 2016年1月11日 下午2:48:10
     * @version 1.0
     */
    public static <OriginObject, TargetObject> List<TargetObject> copyList(List<OriginObject> originObjects, Class<TargetObject> targetClass) {

        if (originObjects == null) {
            return null;
        }

        //参数不合法异常
        if (targetClass == null) {
            throw new IllegalArgumentException("targetClass can't be null");
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
     *
     * @param targetObject
     * @return
     * @author acexy@thankjava.com
     * @date 2016年1月11日 上午10:42:16
     * @version 1.0
     */
    private static <OriginObject, TargetObject> TargetObject setValue(OriginObject originObject, TargetObject targetObject, Class<TargetObject> targetClass) {

        Field[] targetFields = ReflectUtil.getFieldArrayIncludeSupClassExcludeUID(targetClass);

        Field originField = null; //目标字段类型

        Object originValue = null; //原始对象属性值
        Object targetValue = null; //目标对象属性值

        //从目标对象中找原始对象的属性方式，
        for (Field targetField : targetFields) {

            originField = ReflectUtil.getField(originObject.getClass(), targetField.getName());

            if (originField == null) { //目标对象有，但是原始对象中没有
                continue;
            }

            if (Modifier.isStatic(originField.getModifiers())) {
                continue;
            }

            targetField.setAccessible(true);
            originField.setAccessible(true);

            originValue = ReflectUtil.getFieldVal(originObject, targetField.getName());
            if (originValue == null) { //从原始对象中获取的字段属性为null
                continue;
            }

            targetValue = ValueCast.cast(targetField, targetObject, originValue);

            if (targetValue != null) {
                try {
                    targetField.set(targetObject, targetValue);
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {//为目标属性赋值失败
                    e.printStackTrace();
                }
            }
        }

        return targetObject;
    }

}
