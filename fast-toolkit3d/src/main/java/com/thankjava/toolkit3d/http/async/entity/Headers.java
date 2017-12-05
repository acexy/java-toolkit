package com.thankjava.toolkit3d.http.async.entity;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

/**
 * 包装header信息
 * <p>Function: Header</p>
 * <p>Description:</p>
 * @author acexy@thankjava.com
 * @date 2016年12月13日 上午10:43:11
 * @version 1.0
 */
public class Headers {

	private Map<String, Header> headers = null;

	public Headers(String name, String value) {
		headers = new HashMap<String, Header>();
		headers.put(name, new BasicHeader(name, value));
	}

	public Headers(Map<String, String> headers) {
		if (headers == null || headers.size() == 0) {
			return;
		}
		this.headers = new HashMap<String, Header>();
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			this.headers.put(entry.getKey(), new BasicHeader(entry.getKey(), entry.getValue()));
		}
	}

	public Headers(Header[] headers) {
		if (headers == null || headers.length == 0) {
			return;
		}
		this.headers = new HashMap<String, Header>();
		for (Header header : headers) {
			this.headers.put(header.getName(), header);
		}
	}

	public void append(String name, String value) {
		headers.put(name, new BasicHeader(name, value));
	}

	public String getHeaderValue(String name) {
		Header header = headers.get(name);
		if (header == null) {
			return null;
		}
		return header.getValue();
	}

	public Header[] toHeaderArray() {
		Header[] headerArray = new Header[headers.size()];
		int index = 0;
		for (Map.Entry<String, Header> entry : headers.entrySet()) {
			headerArray[index] = entry.getValue();
			index ++;
		}
		return headerArray;
	}

	@Override
	public String toString() {
		return headers.toString();
	}
}
