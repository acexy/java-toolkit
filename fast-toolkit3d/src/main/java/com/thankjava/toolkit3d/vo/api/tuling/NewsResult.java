package com.thankjava.toolkit3d.vo.api.tuling;

import com.alibaba.fastjson.annotation.JSONField;

public class NewsResult {

	private String article;
	private String source;
	private String icon;
	@JSONField(name = "detailurl")
	private String detailUrl;
	
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getDetailUrl() {
		return detailUrl;
	}
	public void setDetailUrl(String detailUrl) {
		this.detailUrl = detailUrl;
	}
	
	
}
