package com.dhcc.ecm.business.util;

import com.github.pagehelper.StringUtil;
/**
 * @ClassName StringBusinessUtil
 * @Description String相关操作
 * @author wangaobing wangaobing001@dhcc.com.cn
 * @date 2017-04-17
 */
public class StringBusinessUtil {
	/**
	 * 将字符串左边的零去掉
	 * @param str
	 * @return
	 */
	public static Integer[] getarchivesBoxNumAndLength(String str) {
		Integer[] arr = new Integer[2];
		if (!StringUtil.isEmpty(str)) {
			String newStr = str.replaceAll("^(0+)", "");
			arr[0] = Integer.parseInt(newStr);
			arr[1] = str.length();
			System.out.println(newStr);
		}
		return arr;
	}

	/**
	 * 字符串左端补零，按照长度
	 * 
	 * @param str
	 *            字符串
	 * @param strLength
	 *            需要补充零后的长度
	 * @return
	 */
	public static String setArchivesBoxNumZero(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				// sb.append(str).append("0");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}
		System.out.println("左端补零内容==================="+str);
		return str;
	}

	public static void main(String[] args) {
//		String str = "000000001234034120";
//		String str = "1234034120";
	}
}
