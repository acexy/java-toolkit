package org.thankjava.toolkit.system;

import java.io.IOException;
import java.io.InputStream;

import org.thankjava.toolkit.enums.system.OsType;

/**
 * 系统相关工具
* <p>Function: ThreadUtil</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年1月5日 下午3:11:42
* @version 1.0
 */
public final class SystemUtil {
	
	private static OsType osType = null;
	
	private SystemUtil(){}
	
	/**
	 * 执行shell命令
	* <p>Function: execShell</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月12日 下午5:19:32
	* @version 1.0
	* @param shell
	* @param charsetName
	* @return
	 */
	public static String[] execShell(String shell,String charsetName){
		try {
			if(osType == null){
				osType = getOsType();
			}
			String[] param = null;
			switch (osType) {
			case window:
				param = new String[]{"cmd.exe","/C", shell};
				break;
			case linux:
				param = new String[]{"/bin/sh","-c", shell};
				break;
			default:
				return null; //unknow option os
			}
			Process process = Runtime.getRuntime().exec(param);
			process.waitFor();
			InputStream is = process.getInputStream();
			InputStream	isErr = process.getErrorStream();
			byte[] bts = new byte[is.available()];
			is.read(bts);
			byte[] btsErr = new byte[isErr.available()];
			isErr.read(btsErr);
			String[] arr = new String[]{new String(bts,charsetName),new String(btsErr,charsetName)};
			is.close();
			isErr.close();
			return arr;
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取系统类型
	* <p>Function: getOsType</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年1月13日 上午11:04:05
	* @version 1.0
	* @return
	 */
	private static OsType getOsType(){
		String os = System.getProperty("os.name");
		if(os.startsWith("win") || os.startsWith("Win")){
			return OsType.window;
		}
		return OsType.linux;
	} 
}
