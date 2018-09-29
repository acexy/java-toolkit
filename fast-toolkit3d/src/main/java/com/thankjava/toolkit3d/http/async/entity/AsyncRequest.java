package com.thankjava.toolkit3d.http.async.entity;

import com.thankjava.toolkit3d.http.async.consts.Charset;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;

public class AsyncRequest {

    /**
     * 请求的完整地址
     */
    private String url;

    /**
     * 携带的cookies信息
     */
    private Cookies cookies;

    /**
     * 携带的头部信息
     */
    private Headers header;

    /**
     * 携带的请求参数
     */
    private Parameters parameter;

    /**
     * 请求方式
     */
    private HttpMethod httpMethod;

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
     * @param httpMethod    请求类型
     */
    public AsyncRequest(String url, HttpMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

    public AsyncRequest(String url, HttpMethod httpMethod, Parameters parameter) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.parameter = parameter;
    }

    /**
     * http请求参数构建
     * @param url
     * @param httpMethod
     * @param parameter 请求参数 post body | get url
     * @param header 携带的 header
     */
    public AsyncRequest(String url, HttpMethod httpMethod, Parameters parameter, Headers header) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.parameter = parameter;
        this.header = header;
    }

    /**
     * http请求参数构建
     * @param url
     * @param httpMethod
     * @param cookies cooke信息
     * @param header
     */
    public AsyncRequest(String url, HttpMethod httpMethod, Cookies cookies, Headers header) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.cookies = cookies;
        this.header = header;
    }

    public AsyncRequest(String url, HttpMethod httpMethod, Cookies cookies, Headers header, Parameters parameter) {
        this.url = url;
        this.httpMethod = httpMethod;
        this.cookies = cookies;
        this.header = header;
        this.parameter = parameter;
    }

    public String getUrl() {
        return url;
    }

    public Cookies getCookies() {
        return cookies;
    }

    public Headers getHeader() {
        return header;
    }

    public Parameters getParameter() {
        return parameter;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
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

    public AsyncRequest setCookies(Cookies cookies) {
        this.cookies = cookies;
        return this;
    }

    public AsyncRequest setHeader(Headers header) {
        this.header = header;
        return this;
    }

    public AsyncRequest setParameter(Parameters parameter) {
        this.parameter = parameter;
        return this;
    }

}
