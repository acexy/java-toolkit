package org.thankjava.toolkit3d.api.baidu.translation;

import java.util.Random;

import org.thankjava.toolkit.httpclient.DefaultHttpClient;
import org.thankjava.toolkit.security.MD5Security;
import org.thankjava.toolkit3d.enums.api.baidu.translation.Language;
import org.thankjava.toolkit3d.fastjson.FastJson;
import org.thankjava.toolkit3d.vo.api.baidu.translation.TransData;

/**
 * 调用百度翻译API
* <p>Function: Translation</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2015年12月30日 上午10:11:24
* @version 1.0
 */
public class Translation {

	private String URL = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	
	private Random r = new Random();
	
	/**
	 * 百度授权的appID
	 */
	private String appID;
	/**
	 * 百度授权的appID对应的密钥
	 */
	private String secretKey;
	
	public Translation(String appID,String secretKey){
		this.appID = appID;
		this.secretKey = secretKey;
	}
	
	/**
	 * 翻译内容
	* <p>Function: translat</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2015年12月30日 上午10:19:34
	* @version 1.0
	* @param keyWord	需要翻译的信息
	* @param from	需要翻译信息的语言种类，可以为空
	* @param to		需要翻译成的语言种类，可以为空
	* @return
	 */
	public TransData translat(String keyWord,Language fromLang,Language toLang){
		if(keyWord == null || keyWord.trim().length() == 0){
			return null;
		}
		String salt = String.valueOf(Math.abs(r.nextLong()));
		String from = fromLang == null ? "auto" : fromLang.name();
		String to = toLang == null ? "auto" : toLang.name();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(URL);
		stringBuffer.append("?q=" + DefaultHttpClient.encode(keyWord));
		stringBuffer.append("&from=" + from);
		stringBuffer.append("&to=" + to);
		stringBuffer.append("&appid=" + appID);
		stringBuffer.append("&salt=" + salt);
		stringBuffer.append("&sign=" + MD5Security.generatePassword(appID + keyWord + salt + secretKey));
		return FastJson.toObject(DefaultHttpClient.get(stringBuffer.toString()), TransData.class);
	}
}
