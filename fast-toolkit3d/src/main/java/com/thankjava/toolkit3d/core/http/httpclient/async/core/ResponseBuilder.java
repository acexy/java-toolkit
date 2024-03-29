package com.thankjava.toolkit3d.core.http.httpclient.async.core;

import com.thankjava.toolkit.bean.common.Charset;
import com.thankjava.toolkit3d.bean.http.async.AsyncCookies;
import com.thankjava.toolkit3d.bean.http.async.AsyncHeaders;
import com.thankjava.toolkit3d.bean.http.async.AsyncResponse;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;

import java.util.List;

public final class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static AsyncResponse builder(HttpResponse response, Charset charset, HttpClientContext syncHttpClientContext) {

        AsyncResponse asyncResponse = new AsyncResponse();
        asyncResponse.setHttpCode(response.getStatusLine().getStatusCode());
        // 处理header
        Header[] headers = response.getAllHeaders();
        if (headers != null && headers.length > 0) {
            asyncResponse.setHeader(new AsyncHeaders(headers));
        }
        List<Cookie> cookies = syncHttpClientContext.getCookieStore().getCookies();
        // 处理cookie
        if (cookies != null && cookies.size() > 0) {
            asyncResponse.setAsyncCookies(new AsyncCookies(cookies));
        }
        asyncResponse.setHttpEntity(response.getEntity());
        asyncResponse.setCharset(charset);
        return asyncResponse;
    }

}
