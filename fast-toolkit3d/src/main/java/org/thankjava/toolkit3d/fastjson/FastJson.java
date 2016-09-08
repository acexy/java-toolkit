package org.thankjava.toolkit3d.fastjson;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
/**
 * Alibaba FastJson
* 依赖于 maven[com.alibaba:fastjson]
* <p>Function: FastJson</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2015年12月30日 下午12:01:26
* @version 1.0
 */
public class FastJson {

	/**
	 * 将jsonstr 转换成对象
	* <p>Function: toObject</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2015年12月30日 下午12:01:44
	* @version 1.0
	* @param json
	* @param clazz
	* @return
	 */
	public static <T> T toObject(String json,Class<T> clazz){
		if(json == null || json.trim().length() == 0){
			return null;
		}
		T t = null;
		try {
			t = JSON.parseObject(json, clazz);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return t;
	}
	
	/**
	 * 将对象转json字符串
	* <p>Function: toJsonString</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2015年12月30日 下午12:26:29
	* @version 1.0
	* @param object
	* @return
	 */
	public static String toJsonString(Object object){
		if(object == null){
			return null;
		}
		return JSONObject.toJSONString(object);
	}
	
	/**
	 * 对象转Map
	* <p>Function: toMap</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月8日 下午5:01:19
	* @version 1.0
	* @param object
	* @return
	 */
	public static Map<String, Object> toMap(Object object){
		if(object == null){
			return null;
		}
		return toMap(toJsonString(object));
	}
	
	
	/**
	 * 将jsonstr 转换成Map对象
	* <p>Function: toMap</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月19日 下午5:56:52
	* @version 1.0
	* @param json
	* @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(String json){
		return (Map<String, Object>) JSON.parse(json);
	}
}
