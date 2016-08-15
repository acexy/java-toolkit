package utils;


import org.thankjava.toolkit3d.utils.xml.XMLBeanUtil;

public class XMLBeanUtilTest {
	
	
	public static void main(String[] args) {
		
		String xmlStr = "<root>"
				+ "				<str>a</str>"
				+ "				<enums>001</enums>"
				+ "				<entityA>"
				+ "					<integer>1</integer>"
				+ "					<entityB>"
				+ "						<dou>1.2</dou>"
				+ "					</entityB>"
				+ "				</entityA>"
				+ "		</root>";
		Entity entity ;
//		Map<String, Class<?>> classMap = new HashMap<>();
//		classMap.put("root", Entity.class);
//		classMap.put("entityA", EntityA.class);
		entity = XMLBeanUtil.xml2Bean("root", xmlStr, Entity.class,false);
		System.out.println(entity.getEnums().P);
//		System.out.println(XMLBeanUtil.bean2Xml(entity));
		
		System.out.println(Long.MAX_VALUE);
	}
	
	
}
