package com.thankjava.toolkit3d.bean.http.async;

import com.thankjava.toolkit.bean.common.Charset;

public class AsyncRequest {

    /**
     * 请求的完整地址
     */
    private String url;

    /**
     * 携带的cookies信息
     */
    private AsyncCookies asyncCookies;

    /**
     * 携带的头部信息
     */
    private AsyncHeaders header;

    /**
     * 携带的请求参数
     */
    private AsyncParameters parameter;

    /**
     * 请求方式
     */
    private AsyncHttpMethod asyncHttpMethod;

    /**
     * 请求编码 默认utf8
     */
    private Charset reqCharset = Charset.utf8;

    /**
     * 响应编码 默认utf8
     */
    private Charset resCharset = Charset.utf8;

    /**
     * http请求参数构建
     * @param url   请求地址 需携带协议头 http/https
     * @param asyncHttpMethod    请求类型
     */
    public AsyncRequest(String url, AsyncHttpMethod asyncHttpMethod) {
        this.url = url;
        this.asyncHttpMethod = asyncHttpMethod;
    }

    public AsyncRequest(String url, AsyncHttpMethod asyncHttpMethod, AsyncParameters parameter) {
        this.url = url;
        this.asyncHttpMethod = asyncHttpMethod;
        this.parameter = parameter;
    }

    /**
     * http请求参数构建
     * @param url
     * @param asyncHttpMethod
     * @param parameter 请求参数 post body | get url
     * @param header 携带的 header
     */
    public AsyncRequest(String url, AsyncHttpMethod asyncHttpMethod, AsyncParameters parameter, AsyncHeaders header) {
        this.url = url;
        this.asyncHttpMethod = asyncHttpMethod;
        this.parameter = parameter;
        this.header = header;
    }

    /**
     * http请求参数构建
     * @param url
     * @param asyncHttpMethod
     * @param asyncCookies cooke信息
     * @param header
     */
    public AsyncRequest(String url, AsyncHttpMethod asyncHttpMethod, AsyncCookies asyncCookies, AsyncHeaders header) {
        this.url = url;
        this.asyncHttpMethod = asyncHttpMethod;
        this.asyncCookies = asyncCookies;
        this.header = header;
    }

    public AsyncRequest(String url, AsyncHttpMethod asyncHttpMethod, AsyncCookies asyncCookies, AsyncHeaders header, AsyncParameters parameter) {
        this.url = url;
        this.asyncHttpMethod = asyncHttpMethod;
        this.asyncCookies = asyncCookies;
        this.header = header;
        this.parameter = parameter;
    }

    public String getUrl() {
        return url;
    }

    public AsyncCookies getAsyncCookies() {
        return asyncCookies;
    }

    public AsyncHeaders getHeader() {
        return header;
    }

    public AsyncParameters getParameter() {
        return parameter;
    }

    public AsyncHttpMethod getAsyncHttpMethod() {
        return asyncHttpMethod;
    }

    public Charset getReqCharset() {
        return reqCharset;
    }

    public AsyncRequest setReqCharset(Charset reqCharset) {
        this.reqCharset = reqCharset;
        return this;
    }

    public Charset getResCharset() {
        return resCharset;
    }

    public AsyncRequest setResCharset(Charset resCharset) {
        this.resCharset = resCharset;
        return this;
    }

    public AsyncRequest setAsyncCookies(AsyncCookies asyncCookies) {
        this.asyncCookies = asyncCookies;
        return this;
    }

    public AsyncRequest setHeader(AsyncHeaders header) {
        this.header = header;
        return this;
    }

    public AsyncRequest setParameter(AsyncParameters parameter) {
        this.parameter = parameter;
        return this;
    }

}
