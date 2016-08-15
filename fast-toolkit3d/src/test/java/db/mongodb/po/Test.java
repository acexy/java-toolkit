package db.mongodb.po;

public class Test {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	public Test(Long id,String value){
		this.id = id;
		this.value = value;
	}
	public Test(){}
	
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
