package com.thankjava.toolkit3d.core.async.entity;


/**
 * @Author: acexy@thankjava.com
 * 2018/9/29
 * @Description: 使用异步请求http时请求完毕的回调
 **/
public interface AsyncResponseCallback {

    /**
     * 请求完毕
     * @param asyncResponse
     */
    void completed(AsyncResponse asyncResponse);

    /**
     * 请求异常
     * @param e
     */
    void failed(Exception e);

    /**
     * 请求已取消
     */
    void cancelled();
}
