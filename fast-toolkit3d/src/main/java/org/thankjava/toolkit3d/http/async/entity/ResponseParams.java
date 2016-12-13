package org.thankjava.toolkit3d.http.async.entity;

import java.io.InputStream;

public class ResponseParams {

	private Headers header;
	private Cookies cookies;
	private String content;
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
