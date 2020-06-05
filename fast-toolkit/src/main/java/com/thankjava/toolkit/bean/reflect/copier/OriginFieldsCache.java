package com.thankjava.toolkit.bean.reflect.copier;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : acexy@thankjava.com
 * @desc : TODO
 * @date : 2020/6/5
 **/
public final class OriginFieldsCache {

    private final Map<String, Field> cacheFields = new HashMap<>();

    public OriginFieldsCache addField(String fieldName, Field field) {
        field.setAccessible(true);
        cacheFields.put(fieldName, field);
        return this;
    }

    public Field getField(String fieldName) {
        return cacheFields.get(fieldName);
    }

}
