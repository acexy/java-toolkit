package com.thankjava.toolkit3d.fastjson;

import java.util.Map;

import com.thankjava.toolkit.reflect.BeanCopier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Alibaba FastJson
 * 依赖于 maven[com.alibaba:fastjson]
 * <p>Function: FastJson</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2015年12月30日 下午12:01:26
 */
public class FastJson {

    /**
     * 将json字符串转换成对象
     * <p>Function: toObject</p>
     * <p>Description: </p>
     *
     * @param json
     * @param clazz
     * @return
     * @author acexy@thankjava.com
     * @date 2015年12月30日 下午12:01:44
     * @version 1.0
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        if (json == null || json.trim().length() == 0) {
            return null;
        }
        T t = null;
        try {
            t = JSON.parseObject(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return t;
    }

    /**
     * 将JSONObject 转成对象
     *
     * @param jsonObject
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T toObject(JSONObject jsonObject, Class<T> clazz) {
        if (jsonObject == null) {
            return null;
        }
        return JSONObject.toJavaObject(jsonObject, clazz);
    }

    /**
     * 将会解析json字符串，并将json中解析的数据
     * append到对象t中，原对象t中的属性不会丢失
     * <p>Function: appendObject</p>
     * <p>Description: </p>
     *
     * @param json
     * @param t
     * @return
     * @author acexy@thankjava.com
     * @date 2017年3月10日 下午3:01:58
     * @version 1.0
     */
    public static <T> T appendObject(String json, T t) {
        @SuppressWarnings("unchecked")
        Class<T> clazz = (Class<T>) t.getClass();
        T tNew = toObject(json, clazz);
        return BeanCopier.append(tNew, t);
    }

    /**
     * 将对象转json字符串
     * <p>Function: toJSONString</p>
     * <p>Description: </p>
     *
     * @param object
     * @return
     * @author acexy@thankjava.com
     * @date 2015年12月30日 下午12:26:29
     * @version 1.0
     */
    public static String toJSONString(Object object) {
        if (object == null) {
            return null;
        }
        return JSONObject.toJSONString(object);
    }

    /**
     * java bean 2 JSONObject
     *
     * @param object
     * @return
     */
    public static JSONObject toJSONObject(Object object) {
        return (JSONObject) JSONObject.toJSON(object);
    }

    /**
     * 对象转Map
     * <p>Function: toMap</p>
     * <p>Description: </p>
     *
     * @param object
     * @return
     * @author acexy@thankjava.com
     * @date 2016年3月8日 下午5:01:19
     * @version 1.0
     */
    public static Map<String, Object> toMap(Object object) {
        if (object == null) {
            return null;
        }
        return toMap(toJSONString(object));
    }

    /**
     * 将jsonstr 转换成Map对象
     * <p>Function: toMap</p>
     * <p>Description: </p>
     *
     * @param json
     * @return
     * @author acexy@thankjava.com
     * @date 2016年1月19日 下午5:56:52
     * @version 1.0
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String json) {
        return (Map<String, Object>) JSON.parse(json);
    }

}
