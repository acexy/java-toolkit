package org.thankjava.toolkit3d.vo.api.baidu.apistore.shortlink;

public class ShortLinkData {

	private ShortLinkResult data;
	
	private String error;
	
	private String msg;

	public ShortLinkResult getData() {
		return data;
	}

	public void setData(ShortLinkResult data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
