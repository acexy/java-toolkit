package com.thankjava.toolkit3d.bean.http;

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
