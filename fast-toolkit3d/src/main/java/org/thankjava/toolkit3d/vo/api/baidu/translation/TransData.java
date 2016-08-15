package org.thankjava.toolkit3d.vo.api.baidu.translation;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class TransData {

	private String from;
	private String to;
	@JSONField(name = "trans_result")
	private List<TransResult> transResult = new ArrayList<TransResult>();
	
	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public List<TransResult> getTransResult() {
		return transResult;
	}

	public void setTransResult(List<TransResult> transResult) {
		this.transResult = transResult;
	}
}
