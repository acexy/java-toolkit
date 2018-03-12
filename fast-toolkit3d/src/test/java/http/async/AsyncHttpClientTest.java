package http.async;

import com.thankjava.toolkit3d.http.async.AsyncHttpClient;
import com.thankjava.toolkit3d.http.async.AsyncHttpClientBuilder;
import com.thankjava.toolkit3d.http.async.consts.HttpMethod;
import com.thankjava.toolkit3d.http.async.entity.AsyncRequest;
import com.thankjava.toolkit3d.http.async.entity.AsyncResponse;

public class AsyncHttpClientTest {

    public static void main(String[] args) {
        AsyncHttpClient client = AsyncHttpClientBuilder.createDefault();
        AsyncRequest request = new AsyncRequest("https://www.baidu.com/", HttpMethod.get);
        while (true) {
            AsyncResponse response = client.syncRequestWithSession(request);
            System.out.println(response.toString());
        }
    }
}
