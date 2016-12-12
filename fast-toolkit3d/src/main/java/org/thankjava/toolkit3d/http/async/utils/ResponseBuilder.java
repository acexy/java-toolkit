package org.thankjava.toolkit3d.http.async.utils;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.thankjava.toolkit3d.http.async.entity.ResponseParams;

public class ResponseBuilder {

	private ResponseBuilder(){}
	
	public static ResponseParams builder(HttpResponse response){
		Header[] headers = response.getAllHeaders();
		for (Header header : headers) {
			System.out.println(header.toString());
		}
		return null;
	}
}
