package com.thankjava.toolkit3d.http.async.consts;

public enum Charset {

	utf8("utf-8"),
	gb2312("gb2312"),
	gbk("gbk"),

	;
	
	public String charset;
	
	private Charset(String code){
		this.charset = code;
	}
	
}
