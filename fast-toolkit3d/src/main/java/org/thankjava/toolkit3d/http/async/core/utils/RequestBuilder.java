package org.thankjava.toolkit3d.http.async.core.utils;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.thankjava.toolkit3d.http.async.consts.HttpMethod;
import org.thankjava.toolkit3d.http.async.entity.Headers;
import org.thankjava.toolkit3d.http.async.entity.Parameters;
import org.thankjava.toolkit3d.http.async.entity.RequestParams;


/**
 * 负责构建HttpGet/HttpPost对象
* <p>Function: RequestBuilder</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年12月12日 下午4:44:39
* @version 1.0
 */
public class RequestBuilder {

	/**
	 * 创建get请求对象
	* <p>Function: builderGet</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月12日 下午4:46:22
	* @version 1.0
	* @param requestParams
	* @return
	 */
	public static HttpGet builderGet(RequestParams requestParams){
		return (HttpGet)builderRequest(requestParams);
	}
	
	/**
	 * 创建post请求对象
	* <p>Function: builderPost</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年12月12日 下午6:27:34
	* @version 1.0
	* @param requestParams
	* @return
	 */
	public static HttpPost builderPost(RequestParams requestParams){
		return (HttpPost)builderRequest(requestParams);
	}
	
	
	// Impl
	
	private static Object builderRequest(RequestParams requestParams){
		Object request = null;
		
		if(HttpMethod.post.equals(requestParams.getHttpMethod())){
			request = addParamsPost(requestParams);
		} else if(HttpMethod.get.equals(requestParams.getHttpMethod())){
			request = addParamsGet(requestParams);
		}
		return request;
	}
	
	private static Object addParamsPost(RequestParams requestParams){
		
		HttpPost post = new HttpPost(requestParams.getUrl());
		Headers header = requestParams.getHeader();
		if(header != null){
			post.setHeaders(header.toArray());
		}
		
		Parameters parameter = requestParams.getParameter();
		if(parameter != null){
			try {
				post.setEntity(new UrlEncodedFormEntity(parameter.getNameValuePair(), requestParams.getCharset().charset));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} 
		}
		
		return post;
	}
	
	private static Object addParamsGet(RequestParams requestParams){
		
		HttpGet get;
		Parameters parameter = requestParams.getParameter();
		if(parameter != null){
			get = new HttpGet(requestParams.getUrl() + parameter.toUrlParams());
		}else{
			get = new HttpGet(requestParams.getUrl());
		}
		
		Headers header = requestParams.getHeader();
		if(header != null){
			get.setHeaders(header.toArray());
		}
		
		return get;
	}
}
