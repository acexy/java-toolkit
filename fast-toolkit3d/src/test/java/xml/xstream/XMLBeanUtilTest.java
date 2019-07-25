package xml.xstream;


import com.thankjava.toolkit3d.core.fastjson.FastJson;
import com.thankjava.toolkit3d.core.xml.xstream.XMLBeanUtil;

import java.util.HashMap;
import java.util.Map;

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


        Entity entity;
        Map<String, Class<?>> classMap = new HashMap<>();
        classMap.put("root", Entity.class);
        classMap.put("entityA", EntityA.class);
        entity = XMLBeanUtil.xml2Bean("root", xmlStr, Entity.class, false);
        System.out.println(FastJson.toFormatJSONString(entity));

        EntityB entityB = null;
        Map<String, Class<?>> classMapB = new HashMap<String, Class<?>>();
        classMapB.put("a", EntityB.class);
        classMapB.put("dous", String.class);
        entityB = XMLBeanUtil.xml2Bean(classMapB, xmlStr1, EntityB.class, false);
        System.out.println(FastJson.toFormatJSONString(entityB));

        entityB = new EntityB();
        entityB.setDou(new String[]{"a", "b"});
        System.out.println(XMLBeanUtil.bean2Xml(entityB));
    }
}