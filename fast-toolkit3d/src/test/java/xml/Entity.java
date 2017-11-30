package xml;

public class Entity {

	public String str;
	public EntityA entityA;
	public String enums;
	
	
	public String getStr() {
		return str;
	}
	public void setStr(String str) {
		this.str = str;
	}
	public EntityA getEntityA() {
		return entityA;
	}
	public void setEntityA(EntityA entityA) {
		this.entityA = entityA;
	}
	public Enums getEnums() {
		Enums[] enums = Enums.values();
		for (Enums e : enums) {
			if(e.code.equals(this.enums)){
				return e;
			}
		}
		return null;
	}
	public void setEnums(String enums) {
		this.enums = enums;
	}
	
}
