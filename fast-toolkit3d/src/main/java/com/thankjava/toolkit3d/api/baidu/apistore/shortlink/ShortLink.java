package com.thankjava.toolkit3d.api.baidu.apistore.shortlink;

import java.util.HashMap;
import java.util.Map;

import com.thankjava.toolkit.httpclient.DefaultHttpClient;
import com.thankjava.toolkit3d.enums.api.baidu.apistore.shortlink.ShortLinkType;
import com.thankjava.toolkit3d.fastjson.FastJson;
import com.thankjava.toolkit3d.vo.api.baidu.apistore.shortlink.ShortLinkData;

/**
 * 短链接生成
* <p>Function: ShortLink</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年1月4日 下午6:24:37
* @version 1.0
 */
public class ShortLink {

	private static final String URL = "http://apis.baidu.com/chazhao/shorturl/shorturl";
	
	private String appKey;
	
	public ShortLink(String appKey){
		this.appKey = appKey;
	}
	
	public ShortLinkData link(ShortLinkType shortLinkType,String[] originalUrls){
		if(originalUrls == null || originalUrls.length == 0){
			return null;
		}
		shortLinkType = shortLinkType == null ? ShortLinkType.BAIDU_LIN : shortLinkType; 
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("{\"type\":");
		stringBuffer.append(shortLinkType.getCode());
		stringBuffer.append(",\"url\":");
		stringBuffer.append(DefaultHttpClient.encode(FastJson.toJsonString(originalUrls)));
		stringBuffer.append("}");
		Map<String, String> props = new HashMap<String,String>();
		props.put("apikey", appKey);
		props.put("Content-Type", "application/json;charset=utf-8");
		return FastJson.toObject(DefaultHttpClient.post(URL, stringBuffer.toString(), props, null), ShortLinkData.class);
	}
}
