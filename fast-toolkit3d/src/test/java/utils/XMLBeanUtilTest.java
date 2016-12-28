package utils;


import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

import com.thankjava.toolkit.thread.ThreadPool;
import com.thankjava.toolkit3d.fastjson.FastJson;
import com.thankjava.toolkit3d.xml.XMLBeanUtil;

public class XMLBeanUtilTest {
	
	
	public static void main(String[] args) {
		
		final String xmlStr = 
						"<root>"
				+ "				<str>a</str>"
				+ "				<enums>001</enums>"
				+ "				<entityA>"
				+ "					<integer>1</integer>"
				+ "					<entityB>"
				+ "						<dou>1.2</dou>"
				+ "					</entityB>"
				+ "				</entityA>"
				+ "		</root>";
		
		final String xmlStr1 = 
				"		<a>"
				+ "			<dou>"
				+ "			<dous>1</dous>"
				+ "			<dous>1</dous>"
				+ "			</dou>"
				+ "		</a>";
		
//		System.out.println(XMLBeanUtil.bean2Xml(entity));
		
		
//		ThreadPool pool = new ThreadPool(50, 100, 20000, 60000);
//		while(true)
//			pool.execute(new Runnable() {
//				
//				@Override
//				public void run() {
					Entity entity;
	//				Map<String, Class<?>> classMap = new HashMap<>();
	//				classMap.put("root", Entity.class);
	//				classMap.put("entityA", EntityA.class);
//					entity = XMLBeanUtil.xml2Bean("root", xmlStr, Entity.class, false);
//					System.out.println(FastJson.toJsonString(entity));
					
					EntityB entityB = null;
					Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
					classMap.put("a", EntityB.class);
					classMap.put("dous", String.class);
					entityB = XMLBeanUtil.xml2Bean(classMap, xmlStr1, EntityB.class, false);
					System.out.println(FastJson.toJsonString(entityB));
					
					entityB = new EntityB();
					entityB.setDou(new String[]{"a","b"});
					
					System.out.println(XMLBeanUtil.bean2Xml(entityB));
//				}
//			});
					
					
		String a = "0|1";
		System.out.println(FastJson.toJsonString(a.split("\\|")));
	}
	
	

}
