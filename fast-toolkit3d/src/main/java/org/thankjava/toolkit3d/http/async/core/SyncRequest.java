package org.thankjava.toolkit3d.http.async.core;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.thankjava.toolkit3d.http.async.consts.HttpMethod;
import org.thankjava.toolkit3d.http.async.core.utils.RequestBuilder;
import org.thankjava.toolkit3d.http.async.core.utils.ResponseBuilder;
import org.thankjava.toolkit3d.http.async.entity.RequestParams;
import org.thankjava.toolkit3d.http.async.entity.ResponseParams;

public class SyncRequest extends Request{

	private static SyncRequest syncRequest = new SyncRequest();
	private static CloseableHttpAsyncClient closeableHttpAsyncClient;

	private SyncRequest() {
	}

	public static SyncRequest getInterface(CloseableHttpAsyncClient closeableHttpAsyncClient) {
		SyncRequest.closeableHttpAsyncClient = closeableHttpAsyncClient;
		return syncRequest;
	}

	public ResponseParams requestWithSession(RequestParams requestParams) {
		Future<HttpResponse> future = null;
		addCookies(requestParams);
		
		// POST
		if (HttpMethod.post.equals(requestParams.getHttpMethod())) {
			final HttpPost request = RequestBuilder.builderPost(requestParams);
			future = closeableHttpAsyncClient.execute(request, syncHttpClientContext, null);
			try {
				return ResponseBuilder.builder(future.get(), syncCookieStore.getCookies());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			// GET
		} else if (HttpMethod.get.equals(requestParams.getHttpMethod())) {
			final HttpGet request = RequestBuilder.builderGet(requestParams);
			future = closeableHttpAsyncClient.execute(request, syncHttpClientContext, null);
			try {
				return ResponseBuilder.builder(future.get(), syncCookieStore.getCookies());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
	
	private void addCookies(RequestParams requestParams){
		if(requestParams.getCookies() != null){
			List<Cookie> cookies = requestParams.getCookies().getAllCookies();
			for (Cookie cookie : cookies) {
				syncCookieStore.addCookie(cookie);
			}
		}
	}
}
