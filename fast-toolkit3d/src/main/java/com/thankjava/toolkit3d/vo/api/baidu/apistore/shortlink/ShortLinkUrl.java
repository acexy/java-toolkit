package com.thankjava.toolkit3d.vo.api.baidu.apistore.shortlink;

import com.alibaba.fastjson.annotation.JSONField;

public class ShortLinkUrl {
	
	@JSONField(name = "short_url")
	private String shortUrl;
	
	@JSONField(name = "src_url")
	private String srcUrl;

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getSrcUrl() {
		return srcUrl;
	}

	public void setSrcUrl(String srcUrl) {
		this.srcUrl = srcUrl;
	}

	
}
