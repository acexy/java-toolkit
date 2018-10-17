package com.thankjava.toolkit3d.core.http.core;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import com.thankjava.toolkit3d.bean.http.HttpMethod;
import com.thankjava.toolkit3d.bean.http.Headers;
import com.thankjava.toolkit3d.bean.http.Parameters;
import com.thankjava.toolkit3d.bean.http.AsyncRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;


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
     *
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

            if (parameter.getNameValuePair() != null) {

                try {
                    post.setEntity(new UrlEncodedFormEntity(parameter.getNameValuePair(), asyncRequest.getReqCharset().charset));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            } else if (parameter.getText() != null) {

                post.setEntity(
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

                post.setEntity(entityBuilder.build());
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
                post.setEntity(entityBuilder.build());
            }

        }

        return post;
    }

    // GET
    private static HttpRequestBase addParamsGet(AsyncRequest asyncRequest) {

        HttpGet get = new HttpGet(asyncRequest.getUrl());
        Parameters parameter = asyncRequest.getParameter();
        if (parameter != null && parameter.getNameValuePair() != null) {
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
