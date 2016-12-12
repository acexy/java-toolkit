package org.thankjava.toolkit3d.http.async.entity;

import org.thankjava.toolkit3d.http.async.consts.Charset;
import org.thankjava.toolkit3d.http.async.consts.HttpMethod;

public class RequestParams {
	
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
	private Header header;
	
	/**
	 * 携带的请求参数
	 */
	private Parameter parameter;
	
	/**
	 * 请求方式
	 */
	private HttpMethod httpMethod;
	
	/**
	 * 编码
	 */
	private Charset charset = Charset.UTF8;
	
	public RequestParams(String url, HttpMethod httpMethod) {
		this.url = url;
		this.httpMethod = httpMethod;
	}
	
	public RequestParams(String url, HttpMethod httpMethod, Parameter parameter) {
		this.url = url;
		this.httpMethod = httpMethod;
		this.parameter = parameter;
	}
	
	public RequestParams(String url, HttpMethod httpMethod, Cookies cookies, Header header) {
		this.url = url;
		this.httpMethod = httpMethod;
		this.cookies = cookies;
		this.header = header;
	}

	public RequestParams(String url, HttpMethod httpMethod, Cookies cookies, Header header, Parameter parameter) {
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
	public Header getHeader() {
		return header;
	}
	public Parameter getParameter() {
		return parameter;
	}
	public HttpMethod getHttpMethod() {
		return httpMethod;
	}
	public Charset getCharset() {
		return charset;
	}

	
}
