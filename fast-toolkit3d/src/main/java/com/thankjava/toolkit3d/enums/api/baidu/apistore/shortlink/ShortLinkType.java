package com.thankjava.toolkit3d.enums.api.baidu.apistore.shortlink;

public enum ShortLinkType {

	SINA_LINK(1),
	BAIDU_LIN(2);
	
	private int code;
	
	private ShortLinkType(int code){
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
}
