package com.thankjava.toolkit3d.http.async.core;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;
import com.thankjava.toolkit3d.http.async.core.utils.RequestBuilder;
import com.thankjava.toolkit3d.http.async.core.utils.ResponseBuilder;
import com.thankjava.toolkit3d.http.async.entity.AsyncRequest;
import com.thankjava.toolkit3d.http.async.entity.AsyncResponse;

public class SyncRequest extends Request{

	private static SyncRequest syncRequest = new SyncRequest();
	private static CloseableHttpAsyncClient closeableHttpAsyncClient;

	private SyncRequest() {}

	public static SyncRequest getInterface(CloseableHttpAsyncClient closeableHttpAsyncClient) {
		SyncRequest.closeableHttpAsyncClient = closeableHttpAsyncClient;
		return syncRequest;
	}

	public AsyncResponse requestWithSession(AsyncRequest asyncRequest) {
		Future<HttpResponse> future = null;
		addCookies(asyncRequest);
		
		// POST
		if (HttpMethod.post.equals(asyncRequest.getHttpMethod())) {
			final HttpPost request = RequestBuilder.builderPost(asyncRequest);
			future = closeableHttpAsyncClient.execute(request, syncHttpClientContext, null);
			try {
				return ResponseBuilder.builder(future.get(), asyncRequest.getResCharset(), syncCookieStore.getCookies());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			// GET
		} else if (HttpMethod.get.equals(asyncRequest.getHttpMethod())) {
			final HttpGet request = RequestBuilder.builderGet(asyncRequest);
			future = closeableHttpAsyncClient.execute(request, syncHttpClientContext, null);
			try {
				return ResponseBuilder.builder(future.get(), asyncRequest.getResCharset(), syncCookieStore.getCookies());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	private void addCookies(AsyncRequest asyncRequest){
		if(asyncRequest.getCookies() != null){
			List<Cookie> cookies = asyncRequest.getCookies().getAllCookies();
			for (Cookie cookie : cookies) {
				syncCookieStore.addCookie(cookie);
			}
		}
	}
}
