package db.mongodb.po;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Test {

	@JSONField(name = "_id")
	private String id;
	private String value;
	private Long time;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
