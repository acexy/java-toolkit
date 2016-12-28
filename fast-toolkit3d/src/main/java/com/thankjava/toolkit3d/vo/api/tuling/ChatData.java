package com.thankjava.toolkit3d.vo.api.tuling;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class ChatData {

	private String code;
	
	private String text = "";
	
	private String url = "";

	@JSONField(name = "list")
	private List<NewsResult> newsResult;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<NewsResult> getNewsResult() {
		return newsResult;
	}

	public void setNewsResult(List<NewsResult> newsResult) {
		this.newsResult = newsResult;
	}
	
	
	
}
