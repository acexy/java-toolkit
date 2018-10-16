package com.thankjava.toolkit3d.core.enums.zip4j;

/**
 * 压缩方式
* <p>Function: Zip4jType</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2016年8月16日 上午10:18:32
* @version 1.0
 */
public enum ZipType {

	COMP_STORE(0),
	COMP_DEFLATE(8),
	COMP_AES_ENC(99)
	;
	
	private int code;
	
	private ZipType(int code){
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	
}
