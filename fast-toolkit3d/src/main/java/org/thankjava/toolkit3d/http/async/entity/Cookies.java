package org.thankjava.toolkit3d.http.async.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;

public class Cookies {

	private Map<String, Cookie> cookies = null;
	
	public Cookies(List<Cookie> cookies) {
		if(cookies == null || cookies.size() == 0){
			return;
		}
		this.cookies = new HashMap<String, Cookie>();
		for (Cookie cookie : cookies) {
			this.cookies.put(cookie.getName(), cookie);
		}
	}

	public Cookie getCookie(String name){
		return cookies.get(name);
	}
	
//	public Headers(Map<String, String> headers) {
//		if (headers == null || headers.size() == 0) {
//			return;
//		}
//		this.headers = new ArrayList<org.apache.http.Header>();
//		for (Map.Entry<String, String> entry : headers.entrySet()) {
//			this.headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
//		}
//	}
//
//	public void append(String name, String value) {
//		headers.add(new BasicHeader(name, value));
//	}
//
//	public org.apache.http.Header[] toArray(){
//		if(headers == null){
//			return null;
//		}
//		int size = headers.size();
//		org.apache.http.Header[] headerArray = new org.apache.http.Header[size];
//		for (int index = 0; index < size; index ++) {
//			headerArray[index] = headers.get(index);
//		}
//		return headerArray;
//	}
	
	@Override
	public String toString() {
		return cookies.toString();
	}
}
