package com.thankjava.toolkit3d.http.async.consts;

public enum HeaderName {

	content_type("Content-Type"),
	referer("Referer"),
	connection("Connection"),
	origin("Origin"),
	host("Host"),
	;
	
	public String name;
	
	private HeaderName(String name){
		this.name = name;
	}
}
