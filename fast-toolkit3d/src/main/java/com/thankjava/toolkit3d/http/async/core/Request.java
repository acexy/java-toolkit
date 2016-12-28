package com.thankjava.toolkit3d.http.async.core;

import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;

/**
 * 封装的Async请求框架
* <p>Function: Request</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年12月15日 下午6:00:19
* @version 1.0
 */
class Request {

	
	/**
	 * 同步请求的上下文
	 */
	protected static HttpClientContext syncHttpClientContext = HttpClientContext.create();
	/**
	 * 同步请求的CookieStore
	 */
	protected static CookieStore syncCookieStore = new BasicCookieStore();
	
	static {
		syncHttpClientContext.setCookieStore(syncCookieStore);
	}
	
	/**
	 * 获取请求的CookieStore
	* <p>Function: getSyncCookieStore</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月15日 下午6:00:02
	* @version 1.0
	* @return
	 */
	public static CookieStore getSyncCookieStore(){
		return Request.syncCookieStore;
	}
}
