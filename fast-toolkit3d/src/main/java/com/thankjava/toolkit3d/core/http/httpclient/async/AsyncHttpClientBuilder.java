package com.thankjava.toolkit3d.core.http.httpclient.async;

import com.thankjava.toolkit3d.bean.http.CookieCheckLevel;
import org.apache.http.client.config.RequestConfig;
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

    private HttpAsyncClientBuilder httpAsyncClientBuilder;
    private RequestConfig.Builder requestConfigBuilder;

    /**
     * 设置需要创建自定义 AsyncHttpClient 实例
     */
    public AsyncHttpClientBuilder() {
        httpAsyncClientBuilder = HttpAsyncClients.custom();
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
     * @version 1.0
     */
    public static AsyncHttpClient createDefault() {
        return new AsyncHttpClient(HttpAsyncClients.createDefault());
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
                    new TrustStrategy() {
                        public boolean isTrusted(X509Certificate[] chain, String authType) {
                            return true;
                        }
                    }
            ).build();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        httpAsyncClientBuilder.setSSLContext(sslContext);

        return this;
    }

    /**
     * 设置cookie的校验级别
     *
     * @return
     */
    public AsyncHttpClientBuilder setCookiePolicyLevel(CookieCheckLevel cookiePolicyLevel) {
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
