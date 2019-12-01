package com.erajiezhang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 短链接生成算法
 * @author EraJieZhang
 * @data 2019/12/3
 */
public class ShortUrlGenerator {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String sLongUrl = "https://next.survey-expert.cn/next/answer/answerSurvey?U=0001063063675062147&testID=test";

		
		String[] aResult = shortUrl(sLongUrl,"key");
		// 打印出结果
		System.out.println("打印短链接：" + aResult[0]);
		for (int i = 0; i < aResult.length; i++) {
		
		}
	}
	
	/**
	 * 生成短链接
	 * @param url
	 * @param key
	 * @return
	 */
	public static String[] shortUrl(String url,String key) {
		
		// 要使用生成 URL 的字符
		String[] chars = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x",
			"y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P",
			"Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"
			
		};
		String sMD5EncryptResult = md5(key +  url);
		String hex = sMD5EncryptResult;
		
		String[] resUrl = new String[4];
		for (int i = 0; i < 4; i++) {
			
			// 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
			String sTempSubString = hex.substring(i * 8, i * 8 + 8);
			
			// 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用
			// long ，则会越界
			long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
			String outChars = "";
			for (int j = 0; j < 6; j++) {
				// 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
				long index = 0x0000003D & lHexLong;
				// 把取得的字符相加
				outChars += chars[(int) index];
				// 每次循环按位右移 5 位
				lHexLong = lHexLong >> 5;
			}
			// 把字符串存入对应索引的输出数组
			resUrl[i] = outChars;
		}
		return resUrl;
	}
	
	public static String md5(String string) {
		if (string == null || string == "") {
			return "";
		}
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(string.getBytes());
			StringBuilder result = new StringBuilder();
			for (byte b : bytes) {
				String temp = Integer.toHexString(b & 0xff);
				if (temp.length() == 1) {
					temp = "0" + temp;
				}
				result.append(temp);
			}
			return result.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
}
