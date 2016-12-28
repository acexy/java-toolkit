package com.thankjava.toolkit3d.vo.api.aikuaidi;

import java.util.List;

public class ExpreData {

	private String id;
	
	private String name;
	
	private String order;
	
	private Integer num;
	
	private String updateTime;
	
	private String message;
	
	private String errCode;
	
	private String status;
	
	private List<ExpreResult> data;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ExpreResult> getData() {
		return data;
	}

	public void setData(List<ExpreResult> data) {
		this.data = data;
	}
	
	
	
}
