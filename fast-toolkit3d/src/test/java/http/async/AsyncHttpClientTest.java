package http.async;

import com.thankjava.toolkit3d.http.async.AsyncHttpClient;
import com.thankjava.toolkit3d.http.async.AsyncHttpClientBuilder;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;
import com.thankjava.toolkit3d.http.async.entity.AsyncRequest;
import com.thankjava.toolkit3d.http.async.entity.AsyncResponse;
import com.thankjava.toolkit3d.http.async.entity.ResponseCallback;

public class AsyncHttpClientTest {

    public static void main(String[] args) {
        AsyncHttpClient client = AsyncHttpClientBuilder.createDefault();
        AsyncRequest request = new AsyncRequest("https://www.baidu.com/", HttpMethod.post);

        long st = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            client.asyncRequestWithSession(request, new ResponseCallback() {
                @Override
                public void completed(AsyncResponse asyncResponse) {
                    System.out.println(asyncResponse.getHeader());
                }

                @Override
                public void failed(Exception e) {

                }

                @Override
                public void cancelled() {

                }
            });
        }
        System.out.println("发起10次异步请求耗时: " + (System.currentTimeMillis() - st));

        st = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            AsyncResponse response = client.syncRequestWithSession(request);
            System.out.println(response.getHeader());
        }
        System.out.println("发起10次同步请求耗时: " + (System.currentTimeMillis() - st));
        client.shutdown();

    }
}
