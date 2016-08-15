package org.thankjava.toolkit3d.utils.zip4j;

import java.io.File;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;

/**
 * 依赖于 maven[net.lingala.zip4j:zip4j]
* <p>Function: Zip4jHelper</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2016年8月12日 下午3:21:15
* @version 1.0
 */
public class Zip4jHelper {

	private static final String CHARSET = "utf-8";
	
	/**
	 * 解压压缩文件
	* <p>Function: unzip</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月12日 下午4:08:54
	* @version 1.0
	* @param zipFilePath 压缩文件位置
	* @param destFolderPath 解压文件夹位置
	* @param pwd 解压密码
	* @return
	 */
	public static boolean unzip(String zipFilePath,String destFolderPath,String... pwd){
		
		ZipFile zipFile;
		try {
			
			//创建压缩对象
			zipFile = new ZipFile(new File(zipFilePath));
			zipFile.setFileNameCharset(CHARSET);
			
			if(!zipFile.isValidZipFile()){
				//压缩文件校验可用性失败
				new ZipException("Invalid archive").printStackTrace();
				return false;
			}
			
			
			if(zipFile.isEncrypted()){
				if(pwd == null || pwd.length == 0){
					//需要密码访问
					new ZipException("The password is required to access the file").printStackTrace();
					return false;
				}
				
				zipFile.setPassword(pwd[0]);
			}
		} catch (ZipException e) {
			e.printStackTrace();
			return false;
		}
		
		
		//创建目标位置
		File destFilePath;
		try {
			destFilePath = new File(destFolderPath);
			if (destFilePath.isDirectory() && !destFilePath.exists()) {
				destFilePath.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		try {
			zipFile.extractAll(destFolderPath);
		} catch (ZipException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public static boolean zip(String srcPath,String zipFilePath,String... pwd){
	
		File srcFile = new File(srcPath);
		if(srcFile.isDirectory()){//压缩文件夹
			if(!srcFile.exists()){
				//目标资源不存在
				new ZipException("Target resource does not exist").printStackTrace();
				return false;
			}
		}else{
			//压缩文件
		}
		
		ZipParameters zipParameters = new ZipParameters();
//		zipParameters.setCompressionMethod(compressionMethod);
		
		return true;
	}
}
