package com.thankjava.toolkit3d.vo.api.baidu.apistore.shortlink;

import java.util.List;

public class ShortLinkResult {

	private int type;
	
	private List<ShortLinkUrl> urls;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<ShortLinkUrl> getUrls() {
		return urls;
	}

	public void setUrls(List<ShortLinkUrl> urls) {
		this.urls = urls;
	}
	
	
}
