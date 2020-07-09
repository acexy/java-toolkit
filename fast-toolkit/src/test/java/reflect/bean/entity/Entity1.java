package reflect.bean.entity;

import java.util.List;

public class Entity1 {

	private String str;
	private Boolean bol;
	private Integer inte;
	private List<String> listStr;
	private String[] strArr;
	private List<Entity> list;
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public Boolean getBol() {
		return bol;
	}
	public void setBol(Boolean bol) {
		this.bol = bol;
	}
	public Integer getInte() {
		return inte;
	}
	public void setInte(Integer inte) {
		this.inte = inte;
	}
	public List<String> getListStr() {
		return listStr;
	}
	public void setListStr(List<String> listStr) {
		this.listStr = listStr;
	}
	public String[] getStrArr() {
		return strArr;
	}
	public void setStrArr(String[] strArr) {
		this.strArr = strArr;
	}

	public List<Entity> getList() {
		return list;
	}

	public void setList(List<Entity> list) {
		this.list = list;
	}
}
