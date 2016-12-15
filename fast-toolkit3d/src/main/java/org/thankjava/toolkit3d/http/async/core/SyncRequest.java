package org.thankjava.toolkit3d.http.async.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.thankjava.toolkit3d.http.async.consts.HttpMethod;
import org.thankjava.toolkit3d.http.async.entity.RequestParams;
import org.thankjava.toolkit3d.http.async.entity.ResponseParams;
import org.thankjava.toolkit3d.http.async.utils.RequestBuilder;
import org.thankjava.toolkit3d.http.async.utils.ResponseBuilder;

public class SyncRequest {

	private static SyncRequest syncRequest = new SyncRequest();
	private static CloseableHttpAsyncClient closeableHttpAsyncClient;

	private static HttpClientContext httpClientContext = HttpClientContext.create();
	private static CookieStore cookieStore = new BasicCookieStore();

	static {
		httpClientContext.setCookieStore(cookieStore);
	}

	private SyncRequest() {
	}

	public static SyncRequest getInterface(CloseableHttpAsyncClient closeableHttpAsyncClient) {
		SyncRequest.closeableHttpAsyncClient = closeableHttpAsyncClient;
		return syncRequest;
	}

	public ResponseParams requestWithSession(RequestParams requestParams) {
		Future<HttpResponse> future = null;
		
		// POST
		if (HttpMethod.post.equals(requestParams.getHttpMethod())) {
			final HttpPost request = RequestBuilder.builderPost(requestParams);
			future = closeableHttpAsyncClient.execute(request, httpClientContext, null);
			try {
				return ResponseBuilder.builder(future.get(), cookieStore.getCookies());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}

			// GET
		} else if (HttpMethod.get.equals(requestParams.getHttpMethod())) {
			final HttpGet request = RequestBuilder.builderGet(requestParams);
			future = closeableHttpAsyncClient.execute(request, httpClientContext, null);
			try {
				return ResponseBuilder.builder(future.get(), cookieStore.getCookies());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

		return null;
	}
}
