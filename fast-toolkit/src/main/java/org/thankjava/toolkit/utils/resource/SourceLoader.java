package org.thankjava.toolkit.utils.resource;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;


/**
 * 资源加载
* <p>Function: SourceLoader</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年4月18日 上午10:33:00
* @version 1.0
 */
public class SourceLoader {

	private static ClassLoaderWrapper classLoaderWrapper = new ClassLoaderWrapper();
	
	/**
	 * 加载内部指定资源
	* <p>Function: getResourceAsReader</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月9日 下午5:06:34
	* @version 1.0
	* @param resource
	* @return
	 */
	public static Reader getResourceAsReader(String resource){
		ClassLoader loader = null;
		InputStream in = classLoaderWrapper.getResourceAsStream(resource, loader);
		if (in == null) {
			return null;
		}
		return new InputStreamReader(in);
	}
	
	
	/**
	 * 加载内部指定资源
	* <p>Function: getResourceAsReader</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年3月9日 下午5:06:34
	* @version 1.0
	* @param resource
	* @return
	 */
	public static InputStream getResourceAsInputStream(String resource){
		ClassLoader loader = null;
		InputStream in = classLoaderWrapper.getResourceAsStream(resource, loader);
		if (in == null) {
			return null;
		}
		return in;
	}
}
