package com.thankjava.toolkit3d.http.async.core;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.thankjava.toolkit3d.http.async.entity.ResponseCallback;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;
import com.thankjava.toolkit3d.http.async.core.utils.RequestBuilder;
import com.thankjava.toolkit3d.http.async.core.utils.ResponseBuilder;
import com.thankjava.toolkit3d.http.async.entity.AsyncRequest;
import com.thankjava.toolkit3d.http.async.entity.AsyncResponse;

public class SyncRequest extends BasicRequest {

    private static SyncRequest syncRequest = new SyncRequest();
    private static CloseableHttpAsyncClient closeableHttpAsyncClient;

    private SyncRequest() {
    }

    public static SyncRequest getInterface(CloseableHttpAsyncClient closeableHttpAsyncClient) {
        SyncRequest.closeableHttpAsyncClient = closeableHttpAsyncClient;
        return syncRequest;
    }

    public AsyncResponse requestWithSession(AsyncRequest asyncRequest) {
        return doRequest(asyncRequest, true, null);
    }

    private AsyncResponse doRequest(final AsyncRequest asyncRequest, boolean withSession, final ResponseCallback callback) {

        Future<HttpResponse> future = null;
        addCookies(asyncRequest);


        HttpClientContext requestCtx = null;

        if (withSession) {
            requestCtx = syncHttpClientContext;
        } else {
            requestCtx = HttpClientContext.create();
        }

        try {

            final HttpRequestBase request = RequestBuilder.builderRequest(asyncRequest);
            final HttpClientContext ctx = requestCtx;

            future = closeableHttpAsyncClient.execute(request, requestCtx, callback != null ? new FutureCallback<HttpResponse>() {


                @Override
                public void completed(HttpResponse httpResponse) {
                    callback.completed(ResponseBuilder.builder(httpResponse, asyncRequest.getResCharset(), ctx));
                }

                @Override
                public void failed(Exception e) {
                    callback.failed(e);
                }

                @Override
                public void cancelled() {
                    callback.cancelled();
                }

            } : null);

            if (callback == null) {
                return ResponseBuilder.builder(future.get(), asyncRequest.getResCharset(), requestCtx);
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }

        return null;
    }

    private void addCookies(AsyncRequest asyncRequest) {
        if (asyncRequest.getCookies() != null) {
            List<Cookie> cookies = asyncRequest.getCookies().getAllCookies();
            for (Cookie cookie : cookies) {
                syncCookieStore.addCookie(cookie);
            }
        }
    }
}
