package http.httpclient.async;

import com.thankjava.toolkit.core.thread.ThreadPool;
import com.thankjava.toolkit3d.bean.http.async.*;
import com.thankjava.toolkit3d.core.http.httpclient.async.AsyncHttpClient;
import com.thankjava.toolkit3d.core.http.httpclient.async.AsyncHttpClientBuilder;

public class AsyncHttpClientTest {

    static ThreadPool pool = new ThreadPool();
    public static void main(String[] args) throws InterruptedException {

//        final AsyncHttpClient client = new AsyncHttpClientBuilder()
//                .setWithoutSSLCheck()
//                .setCookiePolicyLevel(AsyncCookieCheckLevel.BROWSER_COMPATIBILITY)
//                .setTimeout(30000)
//                .create();

        final AsyncHttpClient defaultClient =  AsyncHttpClientBuilder.createDefault();

//        client.asyncRequestWithoutSession(request, new AsyncResponseCallback() {
//            @Override
//            public void completed(AsyncResponse asyncResponse) {
//                System.out.println(asyncResponse);
//                client.shutdown();
//            }
//
//            @Override
//            public void failed(Exception e) {
//                System.out.println(e);
//                client.shutdown();
//            }
//
//            @Override
//            public void cancelled() {
//                client.shutdown();
//            }
//        });

//        Thread.sleep(2000);
//        response = client.syncRequestWithoutSession(request);
//        System.out.println(response);
//
//        Thread.sleep(2000);
//        response = client.syncRequestWithSession(request);
//        System.out.println(response);


        long st = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            pool.execute(()->{
                long sta = System.currentTimeMillis();
                defaultClient.syncRequestWithSession(new AsyncRequest("http://127.0.0.1:8080",AsyncHttpMethod.get));
                System.out.println(String.valueOf(System.currentTimeMillis() - sta));
            });

        }
//        System.out.println("发起10次异步请求耗时: " + (System.currentTimeMillis() - st));
//
//        st = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            response = client.syncRequestWithSession(request);
//            System.out.println(response.getHeader());
//        }
//        System.out.println("发起10次同步请求耗时: " + (System.currentTimeMillis() - st));
//        client.shutdown();

    }
}
