package com.thankjava.toolkit3d.zip4j;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import com.thankjava.toolkit3d.enums.zip4j.ZipLevel;
import com.thankjava.toolkit3d.enums.zip4j.ZipType;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

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
	private static final String SUFFIX = ".zip";
	
	/**
	 * 解压压缩文件
	* <p>Function: unzip</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月12日 下午4:08:54
	* @version 1.0
	* @param zipFilePath 压缩文件位置
	* @param destFolderPath 解压文件夹位置
	* @param pwd 解压密码(如果有)
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
				destFilePath.mkdirs();
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
	
	/**
	 * 压缩资源
	* <p>Function: zip</p>
	* <p>Description: </p>
	* @author zhaoxy@thankjava.com
	* @date 2016年8月16日 下午1:50:18
	* @version 1.0
	* @param srcPath	需要压缩的资源 可以是文件或文件夹地址
	* @param zipFilePath	压缩后资源位置 可以是指定的压缩后文件名或压缩后存放的文件夹地址(此时生成的压缩文件名取决于压缩的原始资源)
	* @param zipType	压缩类型
	* @param zipLevel	压缩等级
	* @param pwd	密码（可选）
	* @return
	 */
	public static boolean zip(String srcPath,String zipFilePath,ZipType zipType,ZipLevel zipLevel,String... pwd){
		File srcFile = new File(srcPath);
		boolean isSrcDirectory = true;
		if(srcFile.isDirectory()){//压缩文件夹
			if(!srcFile.exists()){
				//目标资源不存在
				new ZipException("Target resource does not exist").printStackTrace();
				return false;
			}
		}else{
			//压缩文件
			isSrcDirectory = false;
		}
		
		
		//检查目标路径 生成压缩后的位置 判断目标生成的压缩文件路径
		File file = new File(zipFilePath);
		String zipPath = null; //最终生成文件的完整路径
		if(!file.isDirectory()){//指定的目标路径是一个文件
			File temp = file.getParentFile();
			if(!temp.exists()){//如果路径不存在则创建
				if(temp.mkdirs()){
					zipPath = file.getPath();
				}else{
					//自动创建解压的路径失败
					new ZipException("Automatically creates extraction path failure").printStackTrace();
					return false;
				}
			}else{
				zipPath = file.getPath();
			}
			
		}else{
			if(!file.exists()){
				if(!file.mkdirs()){
					//自动创建解压的路径失败
					new ZipException("Automatically creates extraction path failure").printStackTrace();
					return false;
				}
			}
			zipPath = file.getPath() + File.separator + srcFile.getName() + SUFFIX;
		}
		
		
		ZipParameters zipParameters = new ZipParameters();
		
		//设置压缩方式
		zipParameters.setCompressionMethod(zipType.getCode());
		//设置压缩等级
		zipParameters.setCompressionLevel(zipLevel.getCode());
		
		if(pwd != null && pwd.length > 0){
			zipParameters.setEncryptFiles(true);
			zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD); // 加密方式
			zipParameters.setPassword(pwd[0]);
		}else{
			zipParameters.setEncryptFiles(false);
		}
		
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(zipPath);
			if(isSrcDirectory){
				File[] files = srcFile.listFiles();
				if(files == null || files.length == 0){
					new ZipException("Invalid resource specified").printStackTrace();
					return false;
				}
				ArrayList<File> fileList= new ArrayList<File>();  
				Collections.addAll(fileList, files);
				zipFile.addFiles(fileList, zipParameters);
			}else{
				zipFile.addFile(srcFile, zipParameters);
			}
		} catch (ZipException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
