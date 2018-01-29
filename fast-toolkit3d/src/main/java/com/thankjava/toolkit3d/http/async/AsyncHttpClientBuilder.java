package com.thankjava.toolkit3d.http.async;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.client.HttpAsyncClients;

public class AsyncHttpClientBuilder {


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
        System.setProperty("jsse.enableSNIExtension", "true");
        return new AsyncHttpClient(HttpAsyncClients.createDefault());
    }
}
