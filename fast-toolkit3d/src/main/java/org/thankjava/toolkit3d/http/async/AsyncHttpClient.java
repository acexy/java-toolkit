package org.thankjava.toolkit3d.http.async;

import java.io.IOException;

import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.thankjava.toolkit3d.http.async.core.SyncRequest;
import org.thankjava.toolkit3d.http.async.entity.RequestParams;
import org.thankjava.toolkit3d.http.async.entity.ResponseParams;

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
	 * 发起同步的请求
	* <p>Function: syncRequest</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月12日 下午3:54:33
	* @version 1.0
	* @param requestParams
	* @param parameter
	* @return
	 */
	public ResponseParams syncRequest(RequestParams requestParams){
		return syncRequest.request(requestParams);
	}
	
	public void shutdown(){
		try {
			closeableHttpAsyncClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
