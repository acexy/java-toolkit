package com.thankjava.toolkit3d.http.async;

import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class AsyncHttpClientBuilder {


	/**
	 * 创建默认的AsyncHttpClient实例
	* <p>Function: createDefault</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月12日 下午3:45:57
	* @version 1.0
	* @return
	 */
	public static AsyncHttpClient createDefault(){
		return new AsyncHttpClient(HttpAsyncClients.createDefault());
	}
	
	/**
	 * 创建通用的AsyncHttpClient实例
	* <p>Function: createCommon</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月13日 下午2:20:45
	* @version 1.0
	* @return
	 */
	public static AsyncHttpClient createCommon(){
		HttpAsyncClientBuilder httpAsyncClientBuilder = HttpAsyncClients.custom();
		return new AsyncHttpClient(httpAsyncClientBuilder.build());
	}
}
