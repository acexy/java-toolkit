package http;

import com.thankjava.toolkit.core.http.JDKHttp;


public class JDKHttpTest {


    public static void main(String[] args) {

        System.out.println(JDKHttp.get("http://localhost:8080"));
        System.out.println(JDKHttp.post("http://localhost:8080", "数据来自bodyString", null));
        System.out.println(JDKHttp.post("http://localhost:8080", null, "数据来自bodyByteArray".getBytes()));
    }

}
