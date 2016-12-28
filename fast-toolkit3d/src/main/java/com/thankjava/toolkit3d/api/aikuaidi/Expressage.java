package com.thankjava.toolkit3d.api.aikuaidi;

import com.thankjava.toolkit.httpclient.DefaultHttpClient;
import com.thankjava.toolkit3d.enums.api.aikuaidi.ExpreService;
import com.thankjava.toolkit3d.fastjson.FastJson;
import com.thankjava.toolkit3d.vo.api.aikuaidi.ExpreData;

/**
 * 使用快递100接口进行快递查询
* <p>Function: Expressage</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2015年12月30日 下午1:44:16
* @version 1.0
 */
public class Expressage {

	private static String URL = "http://www.aikuaidi.cn/rest";
	
	private String appKey;
	
	public Expressage(String appKey){
		this.appKey = appKey;
	}
	
	
	/**
	 * 查询快递信息
	* <p>Function: query</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2015年12月30日 下午2:54:11
	* @version 1.0
	* @param expNo 快递单号
	* @param service 快递供应商
	* @return
	 */
	public ExpreData query(String expNo,ExpreService service){
		if(expNo == null || expNo.trim().length() == 0){
			return null;
		}
		if(service == null){
			return null;
		}
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(URL);
		stringBuffer.append("?key=" + appKey);
		stringBuffer.append("&order=" + expNo);
		stringBuffer.append("&id=" + service.name());
		stringBuffer.append("&ord=desc");
		stringBuffer.append("&show=json");
		
		return FastJson.toObject(DefaultHttpClient.get(stringBuffer.toString()), ExpreData.class);
	}
}
