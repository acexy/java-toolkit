package com.thankjava.toolkit.bean.utils;

/**
 * @Author: acexy@thankjava.com
 * 2018/10/16
 * @Description:
 **/
public enum TimeType {

    DEFAULT("yyyy-MM-dd|HH:mm:ss"),
    yyyyMMddHH("yyyyMMddHH"),
    yyyyMMddHHmmssSSS("yyyyMMddHHmmssSSS"),
    yyyyMMddHHmmss("yyyyMMddHHmmss"),
    yyyyMMdd("yyyyMMdd"),
    HHmmss("HHmmss"),

    yyyy1MM1dd1HH1mm("yyyy/MM/dd/HH/mm"),
    yyyy2MM2dd("yyyy-MM-dd"),
    ;

    public String type;

    TimeType(String type) {
        this.type = type;
    }

}