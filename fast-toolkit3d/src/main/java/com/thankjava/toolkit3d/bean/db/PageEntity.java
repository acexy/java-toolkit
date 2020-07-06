package com.thankjava.toolkit3d.bean.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: acexy@thankjava.com
 * 2018/8/1
 * @Description:
 **/
public class PageEntity<T> implements Serializable {


    public PageEntity<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }


    public PageEntity<T> setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public PageEntity<T> setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.pageCount = totalCount % this.pageSize == 0 ? totalCount / this.pageSize : totalCount / this.pageSize + 1;
        this.hasNext = this.totalCount > this.pageSize * this.pageNumber;
        return this;
    }

    public PageEntity<T> addSort(Sort sort) {
        this.sorts.add(sort);
        return this;
    }

    public PageEntity<T> setList(ArrayList<T> list) {
        this.list = list;
        return this;
    }

    private int pageSize = 20;
    private int pageNumber = 1;
    private long totalCount = 0;
    private long pageCount = 0;
    private Boolean hasNext = false;

    private Object condition;
    private List<Sort> sorts = new ArrayList<>();

    private List<T> list;

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public long getPageCount() {
        return pageCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Object getCondition() {
        return condition;
    }

    public void setCondition(Object condition) {
        this.condition = condition;
    }
}
