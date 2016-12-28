package com.thankjava.toolkit3d.http.async;

import java.io.IOException;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import com.thankjava.toolkit3d.http.async.core.SyncRequest;
import com.thankjava.toolkit3d.http.async.entity.RequestParams;
import com.thankjava.toolkit3d.http.async.entity.ResponseParams;

public class AsyncHttpClient {

	/**
	 * 同步请求的处理
	 */
	private static SyncRequest syncRequest;
	
	
	private static CloseableHttpAsyncClient closeableHttpAsyncClient;
	
	AsyncHttpClient(CloseableHttpAsyncClient closeableHttpAsyncClient){
		AsyncHttpClient.closeableHttpAsyncClient = closeableHttpAsyncClient;
		
		closeableHttpAsyncClient.start();
		init();
	}
	
	private void init(){
		AsyncHttpClient.syncRequest = SyncRequest.getInterface(closeableHttpAsyncClient);
	}
	
	/**
	 * 发生同步请求,并自动携带相关的请求头信息
	* <p>Function: syncRequestWithSession</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月12日 下午3:54:33
	* @version 1.0
	* @param requestParams
	* @param parameter
	* @return
	 */
	public ResponseParams syncRequestWithSession(RequestParams requestParams){
		return syncRequest.requestWithSession(requestParams);
	}
	
	/**
	 * 停止整个client
	* <p>Function: shutdown</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月14日 下午2:56:25
	* @version 1.0
	 */
	public void shutdown(){
		try {
			closeableHttpAsyncClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
