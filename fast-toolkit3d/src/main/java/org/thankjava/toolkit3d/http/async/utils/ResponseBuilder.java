package org.thankjava.toolkit3d.http.async.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.util.EntityUtils;
import org.thankjava.toolkit3d.http.async.consts.HeaderName;
import org.thankjava.toolkit3d.http.async.consts.HyperTextContentTypeValue;
import org.thankjava.toolkit3d.http.async.entity.Cookies;
import org.thankjava.toolkit3d.http.async.entity.Headers;
import org.thankjava.toolkit3d.http.async.entity.ResponseParams;

public class ResponseBuilder {

	private ResponseBuilder() {
	}

	public static ResponseParams builder(HttpResponse response, HttpClientContext httpClientConetxt, CookieStore cookieStore) {

		ResponseParams responseParams = new ResponseParams();

		// 处理cookies
		List<Cookie> cookies = cookieStore.getCookies();
		if (cookies != null && cookies.size() > 0) {
			responseParams.setCookies(new Cookies(cookies));
		}

		// 处理header
		Header[] headers = response.getAllHeaders();
		if (headers != null && headers.length > 0) {
			responseParams.setHeader(new Headers(headers));
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
			HyperTextContentTypeValue[] types = HyperTextContentTypeValue.values();
			String contentTypeValue = contentType.getValue();
			for (HyperTextContentTypeValue hyperTextContentTypeValue : types) {
				if(contentTypeValue.startsWith(hyperTextContentTypeValue.name())){
//					try {
//						EntityUtils.toByteArray(entity);
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					System.out.println("超文本");
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
