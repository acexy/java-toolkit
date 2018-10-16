package com.thankjava.toolkit3d.core.async.core;

import java.util.List;

import com.thankjava.toolkit3d.core.async.entity.AsyncResponse;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import com.thankjava.toolkit3d.core.async.consts.Charset;
import com.thankjava.toolkit3d.core.async.entity.Cookies;
import com.thankjava.toolkit3d.core.async.entity.Headers;

public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static AsyncResponse builder(HttpResponse response, Charset charset, HttpClientContext syncHttpClientContext) {

        AsyncResponse asyncResponse = new AsyncResponse();

        asyncResponse.setHttpCode(response.getStatusLine().getStatusCode());

        // 处理header
        Header[] headers = response.getAllHeaders();
        if (headers != null && headers.length > 0) {
            asyncResponse.setHeader(new Headers(headers));
        }

        List<Cookie> cookies = syncHttpClientContext.getCookieStore().getCookies();

        // 处理cookie
        if (cookies != null && cookies.size() > 0) {
            asyncResponse.setCookies(new Cookies(cookies));
        }

        asyncResponse.setHttpEntity(response.getEntity());
        asyncResponse.setCharset(charset);

        return asyncResponse;
    }

}
