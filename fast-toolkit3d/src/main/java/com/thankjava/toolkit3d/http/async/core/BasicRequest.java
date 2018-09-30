package com.thankjava.toolkit3d.http.async.core;

import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;

/**
 * 封装的Async请求框架
* <p>Function: BasicRequest</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2016年12月15日 下午6:00:19
* @version 1.0
 */
class BasicRequest {
	
	/**
	 * 请求上下文
	 */
	protected static HttpClientContext syncHttpClientContext = HttpClientContext.create();

	/**
	 * 请求的CookieStore
	 */
	protected static CookieStore syncCookieStore = new BasicCookieStore();
	
	static {
		syncHttpClientContext.setCookieStore(syncCookieStore);
	}
	
	/**
	 * 获取请求的CookieStore
	* <p>Function: getSyncCookieStore</p>
	* <p>Description: </p>
	* @author acexy@thankjava.com
	* @date 2016年12月15日 下午6:00:02
	* @version 1.0
	* @return
	 */
	public static CookieStore getSyncCookieStore(){
		return BasicRequest.syncCookieStore;
	}
}
