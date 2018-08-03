package httpclient;

import java.util.HashMap;
import java.util.Map;

import com.thankjava.toolkit.httpclient.DefaultHttpClient;

public class HttpClientTest {

	
	public static void main(String[] args) {
	
		
//		String httpArg = "{			    \"type\": 1, 			    \"url\": [ 			        \"http://www.baidu.com\",			        \"http://www.163.com\"			    ]			}";
//		Map<String, String> props = new HashMap<>();
//		props.put("apikey", "e4beccedfcb29b0e791b28d0384aea14");
//		props.put("Content-Type", "application/json;charset=utf-8");
//		System.out.println(DefaultHttpClient.post("http://apis.baidu.com/chazhao/shorturl/shorturl", httpArg,props));
		
		
//		String httpArg = "user=可以&title=路边小吃&price=18";
//		System.out.println(DefaultHttpClient.post("http://localhost:7788/koa/test/index", httpArg,null,null));
		
//		String html = DefaultHttpClient.get("http://elm.f-road.com.cn/");
//		html = html.substring(html.indexOf("<div class=\"peoplelist\">"));
//		System.out.println(html.substring(0,html.indexOf("<div class=\"foot\">")));
//		System.out.println('\u0000');
//		while(true)
//		System.out.println(DefaultHttpClient.post("http://www.baidu.com", null,null,"{\"appKey\":\"9f8b1480a82011e4a5ed90b11c4a15da\",\"version\":\"1.0.1\",\"type\":\"21\"}"));
		
		
//		String url = "http://localhost:6004/area/selectCity";
//		System.out.println(DefaultHttpClient.get(url));
		
		
//		String html = DefaultHttpClient.get("http://10.24.248.50:60000/code?mobile=13527459070");
//		System.out.println(html);
		System.out.println(DefaultHttpClient.post("http://localhost:11548/funpay/merchant/add", null, null, "{\"a\":1}"));
	}
	
}
