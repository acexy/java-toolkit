package com.thankjava.toolkit3d.core.http.httpclient.async;

import com.thankjava.toolkit3d.bean.http.async.AsyncCookieCheckLevel;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.ssl.SSLContextBuilder;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

public class AsyncHttpClientBuilder {

    private final HttpAsyncClientBuilder httpAsyncClientBuilder;
    private final RequestConfig.Builder requestConfigBuilder;

    /**
     * 设置需要创建自定义 AsyncHttpClient 实例
     */
    public AsyncHttpClientBuilder() {
        httpAsyncClientBuilder = HttpAsyncClients.custom();
        httpAsyncClientBuilder.setMaxConnTotal(1000);
        httpAsyncClientBuilder.setMaxConnPerRoute(100);
        requestConfigBuilder = RequestConfig.custom();
    }


    /**
     * 创建默认的AsyncHttpClient实例
     * <p>Function: createDefault</p>
     * <p>Description: </p>
     *
     * @return
     * @author acexy@thankjava.com
     * @date 2016年12月12日 下午3:45:57
     */
    public static AsyncHttpClient createDefault() {
        HttpAsyncClientBuilder defaultHttpAsyncClientBuilder = HttpAsyncClientBuilder.create();
        defaultHttpAsyncClientBuilder.setMaxConnTotal(1000);
        defaultHttpAsyncClientBuilder.setMaxConnPerRoute(100);
        return new AsyncHttpClient(defaultHttpAsyncClientBuilder.build());
    }

    /**
     * 设置不校验证书
     *
     * @return
     */
    public AsyncHttpClientBuilder setWithoutSSLCheck() {

        SSLContext sslContext = null;

        try {
            sslContext = new SSLContextBuilder().loadTrustMaterial(
                    null,
                    (TrustStrategy) (chain, authType) -> true
            ).build();
        } catch (NoSuchAlgorithmException | KeyManagementException | KeyStoreException e) {
            e.printStackTrace();
        }

        httpAsyncClientBuilder.setSSLContext(sslContext);
        httpAsyncClientBuilder.setSSLHostnameVerifier(new NoopHostnameVerifier());

        return this;
    }

    /**
     * 设置cookie的校验级别
     *
     * @return
     */
    public AsyncHttpClientBuilder setCookiePolicyLevel(AsyncCookieCheckLevel cookiePolicyLevel) {
        requestConfigBuilder.setCookieSpec(cookiePolicyLevel.code);
        return this;
    }

    /**
     * 关闭http-client警告日志
     *
     * @return
     */
    public AsyncHttpClientBuilder setCloseWarnLogger() {
        System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.SimpleLog");
        System.setProperty("org.apache.commons.logging.simplelog.defaultlog", "error");
        return this;
    }

    /**
     * 设置超时时间
     *
     * @param timeout milliseconds
     * @return
     */
    public AsyncHttpClientBuilder setTimeout(int timeout) {
        requestConfigBuilder.setConnectTimeout(timeout);
        requestConfigBuilder.setSocketTimeout(timeout);
        requestConfigBuilder.setConnectionRequestTimeout(timeout);
        return this;
    }

    public AsyncHttpClientBuilder setConnTotal(int connTotal, int perRoute) {
        httpAsyncClientBuilder.setMaxConnTotal(connTotal);
        httpAsyncClientBuilder.setMaxConnPerRoute(perRoute);
        return this;
    }


    /**
     * 按照设置创建AsyncHttpClient 实例
     *
     * @return
     */
    public AsyncHttpClient create() {
        httpAsyncClientBuilder.setDefaultRequestConfig(requestConfigBuilder.build());
        return new AsyncHttpClient(httpAsyncClientBuilder.build());
    }
}
