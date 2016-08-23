package org.thankjava.toolkit3d.utils.api.tuling;


import org.thankjava.toolkit.utils.httpclient.DefaultHttpClient;
import org.thankjava.toolkit3d.utils.fastjson.FastJson;
import org.thankjava.toolkit3d.vo.api.tuling.ChatData;

/**
 * 图灵聊天机器人
* <p>Function: ChatRobot</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年1月4日 下午4:53:40
* @version 1.0
 */
public class ChatRobot {

	private static String URL = "http://www.tuling123.com/openapi/api";
	
	/**
	 * 授权密钥
	 */
	private String appKey;

	public ChatRobot(String appKey){
		this.appKey = appKey;
	}
	
	/**
	 * 使用图灵接口进行机器人交谈
	* <p>Function: chat</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月4日 下午5:59:17
	* @version 1.0
	* @param userId		接入层分配的用户会话id(主要用于上下文)
	* @param content	对话内容
	* @return
	 */
	public ChatData chat(String userId,String content){
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("key=");
		stringBuffer.append(appKey);
		stringBuffer.append("&userid=");
		stringBuffer.append( userId);
		stringBuffer.append("&info=");
		stringBuffer.append(DefaultHttpClient.encode(content));
		return FastJson.toObject(DefaultHttpClient.post(URL, stringBuffer.toString(), null, null),ChatData.class);
	}
	
}
