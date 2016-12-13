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

	private SyncRequest() {
	}

	public static SyncRequest getInterface(CloseableHttpAsyncClient closeableHttpAsyncClient) {
		SyncRequest.closeableHttpAsyncClient = closeableHttpAsyncClient;
		return syncRequest;
	}

	public ResponseParams request(RequestParams requestParams) {

		if (HttpMethod.post.equals(requestParams.getHttpMethod())) {
			return doPost(requestParams);
		} else if (HttpMethod.get.equals(requestParams.getHttpMethod())) {
			return doGet(requestParams);
		}

		return null;
	}

	private ResponseParams doPost(RequestParams requestParams) {
		final HttpPost request = RequestBuilder.builderPost(requestParams);

		CookieStore cookieStore = new BasicCookieStore();
		HttpClientContext httpClientConetxt = HttpClientContext.create();
		httpClientConetxt.setCookieStore(cookieStore);

		Future<HttpResponse> future = closeableHttpAsyncClient.execute(request, httpClientConetxt, null);
		try {
			return ResponseBuilder.builder(future.get(), httpClientConetxt, cookieStore);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ResponseParams doGet(RequestParams requestParams) {
		final HttpGet request = RequestBuilder.builderGet(requestParams);

		CookieStore cookieStore = new BasicCookieStore();
		HttpClientContext httpClientConetxt = HttpClientContext.create();
		httpClientConetxt.setCookieStore(cookieStore);

		Future<HttpResponse> future = closeableHttpAsyncClient.execute(request, httpClientConetxt, null);

		try {
			return ResponseBuilder.builder(future.get(), httpClientConetxt, cookieStore);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		return null;
	}
}
