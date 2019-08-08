package com.thankjava.toolkit.bean.common;

/**
 * 统一常用的charset枚举值
 */
public enum Charset {

    utf8("utf-8"),
    gb2312("gb2312"),
    gbk("gbk"),
    iso_8859_1("iso-8859-1"),
    ;

    public String charset;

    Charset(String code) {
        this.charset = code;
    }

}
