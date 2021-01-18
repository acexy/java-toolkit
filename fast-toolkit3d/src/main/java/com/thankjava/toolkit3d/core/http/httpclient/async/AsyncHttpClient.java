package com.thankjava.toolkit3d.core.http.httpclient.async;

import com.thankjava.toolkit3d.bean.http.async.AsyncCookies;
import com.thankjava.toolkit3d.bean.http.async.AsyncRequest;
import com.thankjava.toolkit3d.bean.http.async.AsyncResponse;
import com.thankjava.toolkit3d.bean.http.async.AsyncResponseCallback;
import com.thankjava.toolkit3d.core.http.httpclient.async.core.DoRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;

import java.io.IOException;

public class AsyncHttpClient {

    /**
     * 同步请求的处理
     */
    private final DoRequest doRequest;

    private final CloseableHttpAsyncClient closeableHttpAsyncClient;

    AsyncHttpClient(CloseableHttpAsyncClient closeableHttpAsyncClient) {
        this.closeableHttpAsyncClient = closeableHttpAsyncClient;
        closeableHttpAsyncClient.start();
        doRequest = new DoRequest(closeableHttpAsyncClient);
    }

    /**
     * 发生同步请求,并自动携带历史可用的请求头部信息
     * <p>Function: syncRequestWithSession</p>
     * <p>Description: </p>
     *
     * @param asyncRequest
     * @return
     * @author acexy@thankjava.com
     * @date 2016年12月12日 下午3:54:33
     */
    public AsyncResponse syncRequestWithSession(AsyncRequest asyncRequest) {
        return doRequest.doRequest(asyncRequest, true, null);
    }


    /**
     * 发生同步请求,不携带任何历史可用的头部信息
     *
     * @param asyncRequest
     * @return
     */
    public AsyncResponse syncRequestWithoutSession(AsyncRequest asyncRequest) {
        return doRequest.doRequest(asyncRequest, false, null);
    }

    /**
     * 发起异步请求,并自动携带历史可用的请求头部信息
     *
     * @param asyncRequest
     * @param asyncResponseCallback
     */
    public void asyncRequestWithSession(AsyncRequest asyncRequest, AsyncResponseCallback asyncResponseCallback) {
        doRequest.doRequest(asyncRequest, true, asyncResponseCallback);
    }

    /**
     * 发起异步请求,不携带任何历史可用的头部信息
     *
     * @param asyncRequest
     * @param asyncResponseCallback
     */
    public void asyncRequestWithoutSession(AsyncRequest asyncRequest, AsyncResponseCallback asyncResponseCallback) {
        doRequest.doRequest(asyncRequest, false, asyncResponseCallback);
    }

    /**
     * 从整个http client 上下文中获取所有cookie
     * <p>Function: getRequestCookieStore</p>
     * <p>Description: </p>
     *
     * @return
     * @author acexy@thankjava.com
     * @date 2017年6月12日 下午3:31:00
     */
    public AsyncCookies getAllCookiesFromClientContext() {
        return new AsyncCookies(doRequest.getSyncCookieStore().getCookies());
    }

    /**
     * 从整个http client 上下文中获取指定的cookie
     * <p>Function: getCookie</p>
     * <p>Description: </p>
     *
     * @param cookieName
     * @return
     * @author acexy@thankjava.com
     * @date 2017年6月12日 下午3:41:55
     */
    public Cookie getCookieFromClientContext(String cookieName) {
        return getAllCookiesFromClientContext().getCookie(cookieName);
    }

    /**
     * 停止整个http client
     * <p>Function: shutdown</p>
     * <p>Description: </p>
     *
     * @author acexy@thankjava.com
     * @date 2016年12月14日 下午2:56:25
     */
    public void shutdown() {
        try {
            closeableHttpAsyncClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
