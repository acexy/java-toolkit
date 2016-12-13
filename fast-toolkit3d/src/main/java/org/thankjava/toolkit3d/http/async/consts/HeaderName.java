package org.thankjava.toolkit3d.http.async.consts;

public enum HeaderName {

	content_type("Content-Type")
	;
	
	public String name;
	
	private HeaderName(String name){
		this.name = name;
	}
}
