package com.thankjava.toolkit3d.enums.zip4j;

/**
 * 压缩等级
* <p>Function: ZipLevel</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2016年8月16日 上午10:18:32
* @version 1.0
 */
public enum ZipLevel {

	DEFLATE_LEVEL_FASTEST(1),
	DEFLATE_LEVEL_FAST(3),
	DEFLATE_LEVEL_NORMAL(5),
	DEFLATE_LEVEL_MAXIMUM(7),
	DEFLATE_LEVEL_ULTRA(9)
	;
	
	private int code;
	
	private ZipLevel(int code){
		this.code = code;
	}
	
	public int getCode(){
		return code;
	}
	
	
}
