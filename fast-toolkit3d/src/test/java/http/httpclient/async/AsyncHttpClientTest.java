package http.httpclient.async;

import com.thankjava.toolkit3d.bean.http.async.AsyncCookieCheckLevel;
import com.thankjava.toolkit3d.bean.http.async.AsyncHttpMethod;
import com.thankjava.toolkit3d.bean.http.async.AsyncRequest;
import com.thankjava.toolkit3d.bean.http.async.AsyncResponse;
import com.thankjava.toolkit3d.core.http.httpclient.async.AsyncHttpClient;
import com.thankjava.toolkit3d.core.http.httpclient.async.AsyncHttpClientBuilder;

public class AsyncHttpClientTest {

    public static void main(String[] args) throws InterruptedException {

        final AsyncHttpClient client = new AsyncHttpClientBuilder()
//                .setWithoutSSLCheck()
                .setCookiePolicyLevel(AsyncCookieCheckLevel.BROWSER_COMPATIBILITY)
                .setTimeout(30000)
                .create();

        AsyncRequest request = new AsyncRequest(
                "https://source.thankjava.com/view/XB6ObfN",
                AsyncHttpMethod.put
        );
//
        AsyncResponse response = client.syncRequestWithSession(request);
        System.out.println(response);
//        FileIO.write2File("./a.png", response.getDataByteArray());
//        System.out.println(response.getDataByteArray());


//        AsyncRequest request = new AsyncRequest(
//                "http://localhost:8001",
//                HttpMethod.post,
//                new Parameters(
//                        new File("F:\\Download\\ChromeStandaloneSetup64.exe"),
////                        FileIO.read2ByteArray("F:\\Temp\\io.utf-8")
//                        RequestContentType.TEXT_PLAIN
//                )
//        );

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
