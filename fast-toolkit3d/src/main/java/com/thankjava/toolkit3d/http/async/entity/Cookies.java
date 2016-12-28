package com.thankjava.toolkit3d.http.async.entity;

import java.util.ArrayList;
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
	
	public List<Cookie> getAllCookies(){
		List<Cookie> list = new ArrayList<Cookie>();
		for (Map.Entry<String, Cookie> cookie : cookies.entrySet()) {
			list.add(cookie.getValue());
		}
		return list;
	}
	@Override
	public String toString() {
		return cookies.toString();
	}
}
