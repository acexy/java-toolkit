package http.httpclient.async;

import com.thankjava.toolkit3d.bean.http.async.*;
import com.thankjava.toolkit3d.core.http.httpclient.async.AsyncHttpClient;
import com.thankjava.toolkit3d.core.http.httpclient.async.AsyncHttpClientBuilder;

public class AsyncHttpClientTest {

    public static void main(String[] args) throws InterruptedException {

        final AsyncHttpClient client = new AsyncHttpClientBuilder()
                .setWithoutSSLCheck()
                .setCookiePolicyLevel(AsyncCookieCheckLevel.BROWSER_COMPATIBILITY)
                .setTimeout(30000)
                .create();

        final AsyncHttpClient clientOther = new AsyncHttpClientBuilder()
                .setWithoutSSLCheck()
                .setCookiePolicyLevel(AsyncCookieCheckLevel.BROWSER_COMPATIBILITY)
                .setTimeout(50000)
                .create();



//        FileIO.write2File("./a.png", response.getDataByteArray());
//        System.out.println(response.getDataByteArray());


        new Thread(new Runnable() {
            @Override
            public void run() {
                AsyncRequest request = new AsyncRequest(
                        "http://localhost:8080",
                        AsyncHttpMethod.put,
                        new AsyncParameters("ddfadfasdfasd")
                );
                AsyncResponse resp = client.syncRequestWithoutSession(request);
                System.out.println(resp.getException());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                AsyncRequest request = new AsyncRequest(
                        "http://localhost:8080",
                        AsyncHttpMethod.put,
                        new AsyncParameters("ddfadfasdfasd")
                );
                AsyncResponse resp = clientOther.syncRequestWithoutSession(request);
                System.out.println(resp.getException());
            }
        }).start();


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


//        long st = System.currentTimeMillis();
//        for (int i = 0; i < 10; i++) {
//            client.asyncRequestWithSession(request, new AsyncResponseCallback() {
//                @Override
//                public void completed(AsyncResponse asyncResponse) {
//                    System.out.println(asyncResponse.getHeader());
//                }
//
//                @Override
//                public void failed(Exception e) {
//
//                }
//
//                @Override
//                public void cancelled() {
//
//                }
//            });
//        }
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
