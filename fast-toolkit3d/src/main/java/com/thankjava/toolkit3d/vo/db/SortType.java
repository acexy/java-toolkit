package com.thankjava.toolkit3d.vo.db;

/**
 * @Author: acexy@thankjava.com
 * 2018/8/1
 * @Description:
 **/
public enum SortType {

    asc(1), desc(-1);

    SortType(int code) {
        this.code = code;
    }

    public int code;

}
