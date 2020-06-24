package reflect.bean.vo;

import java.util.List;

public class Vo1 {

	private String str;
	private boolean bol;
	private int inte;
	private List<String> listStr;
	private String[] strArr;
	private List<Vo> vos;
	
	
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
	public void setBol(boolean bol) {
		this.bol = bol;
	}
	public void setInte(int inte) {
		this.inte = inte;
	}

	public boolean isBol() {
		return bol;
	}

	public List<Vo> getVos() {
		return vos;
	}

	public void setVos(List<Vo> vos) {
		this.vos = vos;
	}
}
