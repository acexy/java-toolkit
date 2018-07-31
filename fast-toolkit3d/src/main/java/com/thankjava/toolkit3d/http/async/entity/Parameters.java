package com.thankjava.toolkit3d.http.async.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Parameters {

	private List<NameValuePair> nameValuePairs = null;
	
	public Parameters(String name,String value){
		nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair(name, value));
	}
	
	public Parameters(Map<String, String> parameters) {
		if (parameters == null || parameters.size() == 0) {
			return;
		}
		this.nameValuePairs = new ArrayList<NameValuePair>();
		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			this.nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
	}

	public void append(String name, String value) {
		nameValuePairs.add(new BasicNameValuePair(name, value));
	}
	
	public List<NameValuePair> getNameValuePair(){
		return nameValuePairs;
	}
	
}
