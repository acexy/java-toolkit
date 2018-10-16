package httpclient;

import com.thankjava.toolkit.http.JDKHttp;

import java.util.HashMap;
import java.util.Map;


public class JDKHttpTest {


    public static void main(String[] args) {

        System.out.println(JDKHttp.get("http://localhost:8080"));
        System.out.println(JDKHttp.post("http://localhost:8080", "数据来自bodyString", null));
        System.out.println(JDKHttp.post("http://localhost:8080", null, "数据来自bodyByteArray".getBytes()));
    }

}
