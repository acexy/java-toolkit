package org.thankjava.toolkit.utils.httpclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;


/**
* 默认JDK HTTP-CLIENT
* <p>Function: MailSender</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年1月4日 下午6:16:08
* @version 1.0
*/
public final class DefaultHttpClient {

	private DefaultHttpClient(){}
	
	private static final String CHARSET = "utf-8";
	
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:43.0) Gecko/20100101 Thankjava/fast-toolkit";

	/**
	 * 使用GET方式请求目标网站
	* <p>Function: get</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月4日 下午5:59:17
	* @version 1.0
	* @param url	完整的请求url
	* @return
	 */
	public static String get(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent", USER_AGENT);
			connection.connect();
			
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 使用POST方式请求目标网站
	* <p>Function: post</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月4日 下午5:59:17
	* @version 1.0
	* @param url	请求url
	* @param param	请求参数string结构
	* @param props	头部属性
	* @return
	 */
	public static String post(String url, String param, Map<String, String> props,String strStream) {
		BufferedReader in = null;
		String result = "";
		
		try {
			URL realUrl = new URL(url);
			URLConnection connection = realUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("content-type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("user-agent", USER_AGENT);
			
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.connect();

			//通过kv传送数据
			if (props != null && props.size() > 0) {
				for (Map.Entry<String, String> prop : props.entrySet()) {
					connection.setRequestProperty(prop.getKey(), prop.getValue());
				}
			}
			
			
			//post param 传输数据
			if (param != null) {
				OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
				osw.write(param);
				osw.flush();
				osw.close();
			}
			
			//String流传输数据
			if(strStream != null){
				OutputStream outputStream = connection.getOutputStream();
				outputStream.write(strStream.getBytes(CHARSET));
			}
			
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 参数Encode
	* <p>Function: encode</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月12日 上午11:55:53
	* @version 1.0
	* @param str
	* @return
	 */
	public static String encode(String str){
		try {
			return URLEncoder.encode(str,CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
