package com.thankjava.toolkit3d.xml;

import java.util.Map;
import java.util.WeakHashMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


/**
 * XML2JavaBean
 * 依赖于 maven[com.thoughtworks.xstream:xstream]
 * <p>Function: XMLBeanUtil</p>
 * <p>Description: </p>
 *
 * @author acexy@thankjava.com
 * @version 1.0
 * @date 2016年4月18日 上午10:13:07
 */
public class XMLBeanUtil {

    private static final String ENCODING = "utf-8";
    private static XStream xStream = new XStream(new DomDriver(ENCODING));
    private static final Map<Class<?>, XStream> xStreamInstance = new WeakHashMap<Class<?>, XStream>();

    /**
     * 将xml转java对象
     * <p>Function: xml2Bean</p>
     * <p>Description: </p>
     *
     * @param xmlRoot             xml根节点名
     * @param xml
     * @param clazz               对应的java对象
     * @param isIgnoreUnknownEles 是否容错（如果不允许容错，xml报文中有的节点 java对象中没有将抛异常）
     * @return
     * @author acexy@thankjava.com
     * @date 2016年8月12日 下午3:14:40
     * @version 1.0
     */
    public static <T> T xml2Bean(String xmlRoot, String xml, Class<T> clazz, boolean isIgnoreUnknownEles) {
        XStream xStream = xStreamInstance.get(clazz);
        if (xStream == null) {
            xStream = new XStream(new DomDriver(ENCODING));
            xStream.alias(xmlRoot, clazz);
            if (isIgnoreUnknownEles) {
                xStream.ignoreUnknownElements();
            }
        }
        return (T) xStream.fromXML(xml);
    }

    public static <T> T xml2Bean(Map<String, Class<?>> classMap, String xmlStr, Class<T> clazz, boolean isIgnoreUnknownEles) {
        XStream xStream = xStreamInstance.get(clazz);
        if (xStream == null) {
            xStream = new XStream(new DomDriver(ENCODING));
            for (String key : classMap.keySet()) {
                xStream.alias(key, classMap.get(key));
            }
            if (isIgnoreUnknownEles) {
                xStream.ignoreUnknownElements();
            }
        }
        return (T) xStream.fromXML(xmlStr);
    }

    public static String bean2Xml(Object obj) {
        return xStream.toXML(obj);
    }
}
