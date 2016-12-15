package org.thankjava.toolkit3d.http.async.entity;

public class ResponseParams {

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
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("header: [");
		buffer.append(header == null ? "" : header.toString());
		buffer.append("] cookies: [");
		buffer.append(cookies == null ? "" : cookies.toString());
		buffer.append("] content: [");
		buffer.append(content == null ? "" : content);
		buffer.append("]");
		return buffer.toString();
	}
	
}
