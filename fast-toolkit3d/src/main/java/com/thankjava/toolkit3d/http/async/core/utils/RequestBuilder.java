package com.thankjava.toolkit3d.http.async.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;
import com.thankjava.toolkit3d.http.async.entity.Headers;
import com.thankjava.toolkit3d.http.async.entity.Parameters;
import com.thankjava.toolkit3d.http.async.entity.AsyncRequest;


/**
 * 负责构建HttpGet/HttpPost对象
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
     * @param asyncRequest
     * @return
     */
    public static HttpRequestBase builderRequest(AsyncRequest asyncRequest) {
        HttpRequestBase request = null;

        if (HttpMethod.post.equals(asyncRequest.getHttpMethod())) {
            request = addParamsPost(asyncRequest);
        } else if (HttpMethod.get.equals(asyncRequest.getHttpMethod())) {
            request = addParamsGet(asyncRequest);
        }

        return request;
    }


    // POST
    private static HttpRequestBase addParamsPost(AsyncRequest asyncRequest) {

        HttpPost post = new HttpPost(asyncRequest.getUrl());
        Headers header = asyncRequest.getHeader();

        if (header != null) {
            post.setHeaders(header.toHeaderArray());
        }

        Parameters parameter = asyncRequest.getParameter();
        if (parameter != null) {
            try {
                post.setEntity(new UrlEncodedFormEntity(parameter.getNameValuePair(), asyncRequest.getReqCharset().charset));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return post;
    }

    // GET
    private static HttpRequestBase addParamsGet(AsyncRequest asyncRequest) {

        HttpGet get = new HttpGet(asyncRequest.getUrl());
        Parameters parameter = asyncRequest.getParameter();
        if (parameter != null) {
            try {
                get.setURI(new URIBuilder(get.getURI()).addParameters(parameter.getNameValuePair()).build());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }

        Headers header = asyncRequest.getHeader();
        if (header != null) {
            get.setHeaders(header.toHeaderArray());
        }

        return get;
    }
}
