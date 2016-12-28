package com.thankjava.toolkit.security;

import java.security.MessageDigest;

/**
 * Md5加密
* <p>Function: MD5Security</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2015年12月30日 上午11:08:43
* @version 1.0
 */
public final class MD5Security {

	private MD5Security(){}
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d","e", "f" };

	public static String generatePassword(String password) {
		return encodeByMD5(password);
	}

	public static String generatePasswordUpperCase(String password) {
		String encodeStr = encodeByMD5(password);
		return encodeStr == null ? null : encodeStr.toUpperCase();
	}

	public static boolean validatePassword(String password, String originPwd) {
		if (password.equals(encodeByMD5(originPwd))) {
			return true;
		} else {
			return false;
		}
	}

	/** 对字符串进行MD5加密 */
	private static String encodeByMD5(String originString) {
		if (originString != null) {
			try {
				// 创建具有指定算法名称的信息摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 使用指定的字节数组对摘要进行最后更新，然后完成摘要计算
				byte[] results = md.digest(originString.getBytes());
				// 将得到的字节数组变成字符串返回
				String resultString = byteArrayToHexString(results);
				return resultString;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 转换字节数组为十六进制字符串
	 * 
	 * @param 字节数组
	 * @return 十六进制字符串
	 */
	private static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	/** 将一个字节转化成十六进制形式的字符串 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}
}
