package com.thankjava.toolkit3d.enums.mail;

/**
 * 邮箱供应服务商
* <p>Function: ServiceType</p>
* <p>Description: </p>
* @author acexy@thankjava.com
* @date 2014年10月29日 下午5:29:30
* @version 1.0
 */
public enum MailService {

	TENCENT("25","smtp.qq.com"),
	EASYNET163("25","smtp.163.com"),
	ALIYUN("25","smtp.aliyun.com"),
	SINA("25","smtp.sina.com"),
	;
	
	private String port;
	private String service;
	
	private MailService(String port,String service){
		this.port = port;
		this.service = service;
	}
	public String getPort() {
		return port;
	}
	public String getService() {
		return service;
	}
}
