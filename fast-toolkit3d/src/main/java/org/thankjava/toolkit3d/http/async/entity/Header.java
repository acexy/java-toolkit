package org.thankjava.toolkit3d.http.async.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.message.BasicHeader;

public class Header {

	private List<org.apache.http.Header> headers = null;

	public Header(String name, String value) {
		headers = new ArrayList<org.apache.http.Header>();
		org.apache.http.Header header = new BasicHeader(name, value);
		headers.add(header);
	}

	public Header(Map<String, String> headers) {
		if (headers == null || headers.size() == 0) {
			return;
		}
		this.headers = new ArrayList<org.apache.http.Header>();
		for (Map.Entry<String, String> entry : headers.entrySet()) {
			this.headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
		}
	}

	public void append(String name, String value) {
		headers.add(new BasicHeader(name, value));
	}

	public org.apache.http.Header[] toArray(){
		if(headers == null){
			return null;
		}
		int size = headers.size();
		org.apache.http.Header[] headerArray = new org.apache.http.Header[size];
		for (int index = 0; index < size; index ++) {
			headerArray[index] = headers.get(index);
		}
		return headerArray;
	}
}
