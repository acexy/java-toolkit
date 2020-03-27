package com.thankjava.toolkit3d.core.http.httpclient.async.core;

import com.thankjava.toolkit3d.bean.http.AsyncRequest;
import com.thankjava.toolkit3d.bean.http.Headers;
import com.thankjava.toolkit3d.bean.http.HttpMethod;
import com.thankjava.toolkit3d.bean.http.Parameters;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;


/**
 * 负责构建HttpRequestBase对象
 * <p>Function: RequestBuilder</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年12月12日 下午4:44:39
 */
public class RequestBuilder {

    /**
     * 创建请求信息
     *
     * @param asyncRequest
     * @return
     */
    public static HttpRequestBase builderRequest(AsyncRequest asyncRequest) {
        return getRequest(asyncRequest);
    }

    private static HttpRequestBase addEntityParams(HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, AsyncRequest asyncRequest) {

        Parameters parameter = asyncRequest.getParameter();

        if (parameter != null) {

            if (parameter.getNameValuePair() != null) {
                try {
                    httpEntityEnclosingRequestBase.setEntity(new UrlEncodedFormEntity(parameter.getNameValuePair(), asyncRequest.getReqCharset().charset));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }

            if (parameter.getText() != null) {
                httpEntityEnclosingRequestBase.setEntity(
                        new StringEntity(parameter.getText(),
                                ContentType.create(
                                        parameter.getContentType() == null ? ContentType.DEFAULT_TEXT.getMimeType() : parameter.getContentType(),
                                        Charset.forName(parameter.getContentEncoding() == null ? asyncRequest.getReqCharset().charset : parameter.getContentEncoding()
                                        )
                                )
                        )
                );

            } else if (parameter.getByteData() != null) {

                EntityBuilder entityBuilder = EntityBuilder.create();
                entityBuilder.setBinary(parameter.getByteData());

                if (parameter.getContentEncoding() != null) {
                    entityBuilder.setContentEncoding(parameter.getContentEncoding());
                }

                entityBuilder.setContentType(
                        ContentType.create(
                                parameter.getContentType() == null ? ContentType.DEFAULT_TEXT.getMimeType() : parameter.getContentType(),
                                Charset.forName(parameter.getContentEncoding() == null ? asyncRequest.getReqCharset().charset : parameter.getContentEncoding())
                        )
                );

                httpEntityEnclosingRequestBase.setEntity(entityBuilder.build());
            } else if (parameter.getFile() != null) {

                EntityBuilder entityBuilder = EntityBuilder.create();
                entityBuilder.setFile(parameter.getFile());

                if (parameter.getContentEncoding() != null) {
                    entityBuilder.setContentEncoding(parameter.getContentEncoding());
                }

                entityBuilder.setContentType(
                        ContentType.create(
                                parameter.getContentType() == null ? ContentType.DEFAULT_TEXT.getMimeType() : parameter.getContentType(),
                                Charset.forName(parameter.getContentEncoding() == null ? asyncRequest.getReqCharset().charset : parameter.getContentEncoding())
                        )
                );
                httpEntityEnclosingRequestBase.setEntity(entityBuilder.build());
            }

        }

        return httpEntityEnclosingRequestBase;
    }

    private static HttpRequestBase addBaseParams(HttpRequestBase httpRequestBase, AsyncRequest asyncRequest) {
        Parameters parameter = asyncRequest.getParameter();
        if (parameter != null && parameter.getNameValuePair() != null) {
            try {
                httpRequestBase.setURI(new URIBuilder(httpRequestBase.getURI()).addParameters(parameter.getNameValuePair()).build());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return httpRequestBase;
    }

    private static HttpRequestBase getRequest(AsyncRequest asyncRequest) {

        HttpMethod httpMethod = asyncRequest.getHttpMethod();
        HttpRequestBase requestBase;

        boolean useEntity = false;
        switch (httpMethod) {
            case get:
                requestBase = new HttpGet(asyncRequest.getUrl());
                break;
            case post:
                requestBase = new HttpPost(asyncRequest.getUrl());
                useEntity = true;
                break;
            case patch:
                requestBase = new HttpPatch(asyncRequest.getUrl());
                useEntity = true;
                break;
            case delete:
                requestBase = new HttpDelete(asyncRequest.getUrl());
                break;
            case head:
                requestBase = new HttpHead(asyncRequest.getUrl());
            case options:
                requestBase = new HttpOptions(asyncRequest.getUrl());
            case put:
                requestBase = new HttpPut(asyncRequest.getUrl());
                useEntity = true;
                break;
            case trace:
                requestBase = new HttpTrace(asyncRequest.getUrl());
            default:
                return new HttpGet(asyncRequest.getUrl());
        }

        Headers header = asyncRequest.getHeader();
        if (header != null) {
            requestBase.setHeaders(header.toHeaderArray());
        }

        if (useEntity) {
            requestBase = addEntityParams((HttpEntityEnclosingRequestBase) requestBase, asyncRequest);
        } else {
            requestBase = addBaseParams(requestBase, asyncRequest);
        }

        return requestBase;

    }
}
