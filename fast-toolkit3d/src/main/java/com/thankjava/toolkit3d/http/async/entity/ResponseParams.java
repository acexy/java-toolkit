package com.thankjava.toolkit3d.http.async.entity;

public class ResponseParams {

	/**
	 * 返回http状态码
	 */
	private int httpCode;
	/**
	 * 返回的头部信息
	 */
	private Headers header;
	
	/**
	 * 返回的cookies信息
	 */
	private Cookies cookies;
	
	/**
	 * 返回的内容信息
	 */
	private String content;
	
	/**
	 * 返回的流 byte数组
	 */
	private byte[] bytes;
	
	public Headers getHeader() {
		return header;
	}
	public void setHeader(Headers header) {
		this.header = header;
	}
	public Cookies getCookies() {
		return cookies;
	}
	public void setCookies(Cookies cookies) {
		this.cookies = cookies;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public byte[] getBytes() {
		return bytes;
	}
	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
	public int getHttpCode() {
		return httpCode;
	}
	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}
	
	
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("httpCode: [");
		buffer.append(String.valueOf(httpCode));
		buffer.append("] header: [");
		buffer.append(header == null ? "" : header.toString());
		buffer.append("] cookies: [");
		buffer.append(cookies == null ? "" : cookies.toString());
		buffer.append("] content: [");
		buffer.append(content == null ? "" : content);
		buffer.append("]");
		
		return buffer.toString();
	}
	
	/**
	 * 返回的content内容是否为空
	* <p>Function: isEmptyContent</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2017年6月13日 上午10:49:35
	* @version 1.0
	* @return
	 */
	public boolean isEmptyContent(){
		if(content == null || content.length() == 0){
			return true;
		}
		return false;
	}
}
