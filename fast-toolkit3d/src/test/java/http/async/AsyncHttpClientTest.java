package http.async;

import com.thankjava.toolkit.io.file.FileIO;
import com.thankjava.toolkit3d.http.async.AsyncHttpClient;
import com.thankjava.toolkit3d.http.async.AsyncHttpClientBuilder;
import com.thankjava.toolkit3d.http.async.consts.Charset;
import com.thankjava.toolkit3d.http.async.consts.CookieCheckLevel;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;
import com.thankjava.toolkit3d.http.async.entity.*;

public class AsyncHttpClientTest {

    public static void main(String[] args) throws InterruptedException {

        AsyncHttpClient client = new AsyncHttpClientBuilder()
                .setWithoutSSLCheck()
                .setCookiePolicyLevel(CookieCheckLevel.BROWSER_COMPATIBILITY)
                .setTimeout(30000)
                .create();

//        AsyncRequest request = new AsyncRequest(
//                "https://source.thankjava.com/2018/9/18/54e35fc2951193af3867cd35942f3395.png",
//                HttpMethod.get
//        );
//
//        AsyncResponse response = client.syncRequestWithSession(request);
//        FileIO.write2File("./a.png", response.getDataByteArray());
//        System.out.println(response.getDataByteArray());


        AsyncRequest request = new AsyncRequest(
                "http://localhost:8001",
                HttpMethod.post,
                new Parameters(
                        FileIO.read2ByteArray("/Users/acexy/Downloads/AsyncClientConfiguration.java1"), Charset.gbk
                )
        );

        AsyncResponse response = client.syncRequestWithoutSession(request);
        System.out.println(response);

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
        client.shutdown();

    }
}
