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

    private PageEntity() {
    }

    /**
     * 创建分页对象
     *
     * @param tClass         返回数据的javaBean class 类型指定
     * @param queryCondition
     * @return
     */
    public static PageEntity newPageEntity(Class<?> tClass, Object queryCondition) {
        PageEntity pageEntity = new PageEntity();
        pageEntity.queryCondition = queryCondition;
        pageEntity.tClass = tClass;
        return pageEntity;
    }

    public PageEntity setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }


    public PageEntity setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
        return this;
    }

    public PageEntity setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        this.pageCount = totalCount % this.pageSize == 0 ? totalCount / this.pageSize : totalCount / this.pageSize + 1;
        this.hasNext = this.totalCount > this.pageSize * this.pageNumber;
        return this;
    }

    public PageEntity addSort(Sort sort) {
        this.sorts.add(sort);
        return this;
    }

    public PageEntity setList(ArrayList<T> list) {
        this.list = list;
        return this;
    }

    private int pageSize = 20;
    private int pageNumber = 1;
    private long totalCount = 0;
    private long pageCount = 0;
    private Boolean hasNext = false;
    private Object queryCondition;
    private Class<T> tClass;
    private List<Sort> sorts = new ArrayList<>();

    private List<T> list = new ArrayList<>();

    public int getPageSize() {
        return pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public Object getQueryCondition() {
        return queryCondition;
    }

    public List<T> getList() {
        return list;
    }

    public Boolean getHasNext() {
        return hasNext;
    }

    public List<Sort> getSorts() {
        return sorts;
    }

    public Class<T> getTClass() {
        return tClass;
    }

    public long getPageCount() {
        return pageCount;
    }
}
