package org.thankjava.toolkit.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 可逆加解密3DS
* <p>Function: ThreeEndeSecurity</p>
* <p>Description: </p>
* @author zhaoxy@thankjava.com
* @date 2015年12月30日 上午11:08:20
* @version 1.0
 */
public final class ThreeEndeSecurity {

	private ThreeEndeSecurity(){}
	
	public static String encryptStr(String key, String encryptStr) {
		key += "0000000000000000";
		key = key.substring(0, 16);
		String linshiKey = diverse(KEY, key);

		String str = new String(hex(encryptStr.getBytes()));
		str += "80";
		while (str.length() % 16 != 0) {
			str += "0";
		}
		return encrypt(linshiKey, str);
	}
	public static String decodeStr(String key, String decodeStr) {
		key += "0000000000000000";
		key = key.substring(0, 16);
		String linshiKey = diverse(KEY, key);
		String resul = deEncrypt(linshiKey, decodeStr);
		if (resul.length() % 16 == 0) {
			int dex = resul.lastIndexOf("80");
			String cutStr = resul.substring(0, dex);
			String resultStr = new String(unhexba(cutStr));
			return resultStr;
		} else {
			return "";
		}
	}
	
	private static final String KEY = "0547CD5B408B47ACBC95F94B9CB88879";

	private static String diverse(String dkey, String random) {
		byte[] rand = unhexba(random);
		byte[] key = unhexba(dkey);
		byte[] tleft = do3des(rand, key);
		byte[] tright = do3des(not(rand), key);
		byte[] tkey = bajoin(tleft, tright);
		return hex(tkey);
	}

	private static String  encrypt(String key, String date) {
		return hex(do3des(unhexba(date), unhexba(key)));
	}

	private static String deEncrypt(String key, String date) {
		return hex(do3desun(unhexba(date), unhexba(key)));
	}

	
	private static String[] byteHexTable = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "0A", "0B",
			"0C", "0D", "0E", "0F", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "1A", "1B", "1C", "1D",
			"1E", "1F", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "2A", "2B", "2C", "2D", "2E", "2F",
			"30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "3A", "3B", "3C", "3D", "3E", "3F", "40", "41",
			"42", "43", "44", "45", "46", "47", "48", "49", "4A", "4B", "4C", "4D", "4E", "4F", "50", "51", "52", "53",
			"54", "55", "56", "57", "58", "59", "5A", "5B", "5C", "5D", "5E", "5F", "60", "61", "62", "63", "64", "65",
			"66", "67", "68", "69", "6A", "6B", "6C", "6D", "6E", "6F", "70", "71", "72", "73", "74", "75", "76", "77",
			"78", "79", "7A", "7B", "7C", "7D", "7E", "7F", "80", "81", "82", "83", "84", "85", "86", "87", "88", "89",
			"8A", "8B", "8C", "8D", "8E", "8F", "90", "91", "92", "93", "94", "95", "96", "97", "98", "99", "9A", "9B",
			"9C", "9D", "9E", "9F", "A0", "A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "A9", "AA", "AB", "AC", "AD",
			"AE", "AF", "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BA", "BB", "BC", "BD", "BE", "BF",
			"C0", "C1", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "CA", "CB", "CC", "CD", "CE", "CF", "D0", "D1",
			"D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "DA", "DB", "DC", "DD", "DE", "DF", "E0", "E1", "E2", "E3",
			"E4", "E5", "E6", "E7", "E8", "E9", "EA", "EB", "EC", "ED", "EE", "EF", "F0", "F1", "F2", "F3", "F4", "F5",
			"F6", "F7", "F8", "F9", "FA", "FB", "FC", "FD", "FE", "FF", };

	private static String hex(byte[] dst) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < dst.length; i++) {
			sb.append(byteHexTable[dst[i] & 0xff]);
		}
		return sb.toString();
	}

	private static byte h2b(byte c) {
		if (c <= '9' && c >= '0') {
			return (byte) (c - '0');
		} else if (c >= 'a' && c <= 'z') {
			return (byte) (c - 'a' + 10);
		} else {
			return (byte) (c - 'A' + 10);
		}
	}

	private static byte[] unhexba(String str) {
		str = str.replaceAll(" ", "");
		byte[] origBe = str.getBytes();
		byte[] newBe = new byte[str.length() / 2];

		for (int i = 0; i < str.length(); i += 2) {
			newBe[i / 2] = (byte) (h2b(origBe[i]) * 0x10 + h2b(origBe[i + 1]));
		}
		return newBe;
	}

	private static byte[] bajoin(byte[] left, byte[] right) {
		return unhexba(hex(left) + hex(right));
	}

	private static byte[] not(byte[] be) {
		byte[] x = new byte[be.length];
		for (int i = 0; i < be.length; i++) {
			x[i] = (byte) ~ be[i];
		}
		return x;
	}

	private static byte[] do3des(byte[] data, byte[] key) {
		String str = hex(key);
		String b = str + str.substring(0, 16);
		byte[] key1 = unhexba(b);
		try {
			SecretKey deskey = new SecretKeySpec(key1, "DESede");
			Cipher cipher = Cipher.getInstance("DESede/ECB/NOPADDING");
			cipher.init(Cipher.ENCRYPT_MODE, deskey);
			return cipher.doFinal(data);
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static byte[] do3desun(byte[] data, byte[] key) {
		String a = hex(key);
		String b = a + a.substring(0, 16);
		byte[] key1 = unhexba(b);
		try {
			SecretKey deskey = new SecretKeySpec(key1, "DESede");
			Cipher c1 = Cipher.getInstance("DESede/ECB/NOPADDING");
			c1.init(Cipher.DECRYPT_MODE, deskey);
			return c1.doFinal(data);
		} catch (java.security.NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (javax.crypto.NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (java.lang.Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

}
