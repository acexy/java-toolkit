package com.thankjava.toolkit3d.vo.db;

/**
 * @Author: acexy@thankjava.com
 * 2018/8/1
 * @Description:
 **/
public class Sort {

    private String column;
    private SortType sortType;

    public Sort(String column, SortType sortType) {
        this.column = column;
        this.sortType = sortType;
    }

    public String getColumn() {
        return column;
    }

    public SortType getSortType() {
        return sortType;
    }
}
