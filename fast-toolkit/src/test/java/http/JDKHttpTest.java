package http;

import com.thankjava.toolkit.bean.common.Charset;
import com.thankjava.toolkit.core.http.JDKHttp;


public class JDKHttpTest {


    public static void main(String[] args) {

        // 发起get请求
        System.out.println(new JDKHttp("https://www.baidu.com").doGetResponseString());
        System.out.println(new JDKHttp("https://www.baidu.com")
                .setResponseCharset(Charset.utf8)     // 设置response字符串编码 （默认utf8）
                .doGetResponseString());
        System.out.println(new JDKHttp("https://www.baidu.com").doGetResponseByteArray());

        // 发起post请求
        System.out.println(new JDKHttp("https://www.baidu.com").doPostResponseString());
        System.out.println(new JDKHttp("http://localhost:8080").setPostBody("你好", Charset.gbk).doPostResponseString());
        System.out.println(new JDKHttp("http://localhost:8080").setPostBody("你好".getBytes()).doPostResponseString()); // 设置请求内容为byte数组数据

    }
}
