package db.mysql.po;

import java.io.Serializable;

public class Test implements Serializable{

	private static final long serialVersionUID = 1L;

    public Test() {
    }

    public Test(String value) {
        this.value = value;
    }

    private Long id;
	private String value;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
}
