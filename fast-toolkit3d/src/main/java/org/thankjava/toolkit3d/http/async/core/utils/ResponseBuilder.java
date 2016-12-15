package org.thankjava.toolkit3d.http.async.core.utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;
import org.thankjava.toolkit3d.http.async.consts.HyperTextContentTypeValue;
import org.thankjava.toolkit3d.http.async.entity.Cookies;
import org.thankjava.toolkit3d.http.async.entity.Headers;
import org.thankjava.toolkit3d.http.async.entity.ResponseParams;

public class ResponseBuilder {

	static HyperTextContentTypeValue[] types = HyperTextContentTypeValue.values();
	
	private ResponseBuilder() {}

	public static ResponseParams builder(HttpResponse response) {
		ResponseParams responseParams = new ResponseParams();
		
		// 处理header
		Header[] headers = response.getAllHeaders();
		if (headers != null && headers.length > 0) {
			responseParams.setHeader(new Headers(headers));
		}
		
		sortResponseEntity(responseParams, response);

		return responseParams;
	}
	
	public static ResponseParams builder(HttpResponse response, List<Cookie> cookies) {
		
		ResponseParams responseParams = new ResponseParams();
		
		// 处理header
		Header[] headers = response.getAllHeaders();
		if (headers != null && headers.length > 0) {
			responseParams.setHeader(new Headers(headers));
		}
		// 处理cookie
		if(cookies != null  && cookies.size() > 0){
			responseParams.setCookies(new Cookies(cookies));
		}
		
		sortResponseEntity(responseParams, response);
		
		return responseParams;
	}

	private static void sortResponseEntity(ResponseParams responseParams, HttpResponse response) {
		HttpEntity entity = response.getEntity();
		Header contentType = entity.getContentType();
		if(contentType == null){
			try {
				responseParams.setContent(EntityUtils.toString(entity));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			
			String contentTypeValue = contentType.getValue();
			for (HyperTextContentTypeValue hyperTextContentTypeValue : types) {
				if(contentTypeValue.startsWith(hyperTextContentTypeValue.name())){
					try {
						responseParams.setBytes(EntityUtils.toByteArray(entity));
					} catch (UnsupportedOperationException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					return;
				}
			}
			try {
				responseParams.setContent(EntityUtils.toString(entity));
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
