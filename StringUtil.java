package com.danny.tool;

import java.util.regex.Pattern;

/**
 * java判断字符串是否为数字或中文或字母或为空
 * Created by danny on 2018/5/2.
 */
public class StringUtil {

	/**
	 * 1.判断字符串是否仅为数字:
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric1(String str) {
		for (int i = str.length(); --i >= 0;) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 用正则表达式:判断字符串是否仅为数字:
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric2(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	/**
	 * 用ASCII码:判断字符串是否仅为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric3(String str) {
		for (int i = str.length(); --i >= 0;) {
			int chr = str.charAt(i);
			if (chr < 48 || chr > 57)
				return false;
		}
		return true;
	}

	/**
	 * 2.判断一个字符串的首字符是否为字母
	 * 
	 * @param s
	 * @return
	 */
	public static boolean test(String s) {
		char c = s.charAt(0);
		int i = (int) c;
		if ((i >= 65 && i <= 90) || (i >= 97 && i <= 122)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 判断一个字符串的首字符是否为字母
	 * 
	 * @param s
	 * @return
	 */
	public static boolean check(String fstrData) {
		char c = fstrData.charAt(0);
		if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 3 .判断是否为汉字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean vd(String str) {

		char[] chars = str.toCharArray();
		boolean isGB2312 = false;
		for (int i = 0; i < chars.length; i++) {
			byte[] bytes = ("" + chars[i]).getBytes();
			if (bytes.length == 2) {
				int[] ints = new int[2];
				ints[0] = bytes[0] & 0xff;
				ints[1] = bytes[1] & 0xff;
				if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40
						&& ints[1] <= 0xFE) {
					isGB2312 = true;
					break;
				}
			}
		}
		return isGB2312;
	}

	/** 判断字符串是否有值，如果为null或者是空字符串或者只有空格或者为"null"字符串，则返回true，否则则返回false */
	public static boolean isEmpty(String value) {
		if (value != null && !"".equalsIgnoreCase(value.trim()) && !"null".equalsIgnoreCase(value.trim())) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * 获取字符高度
	 * 返回边界最小的矩形（由调用者分配）
	 *
	 * @param paint 当前画笔对象
	 * @param s 字符
	 * @return 高度
	 */
	private int getAlphabetHeight(Paint paint, String s) {
		Rect rect = new Rect();
		paint.getTextBounds(s, 0, s.length(), rect);//设置后可在Rect中得到高度
		return rect.height();
	}
	
	/**
	 * 判断两个字符串数据是否相等,字符串是否改变
	 *
	 * @param str1 字符串1
	 * @param str2 字符串2
	 * @return true:不相等		false:相等
	 */
	private static boolean haveContentsChanged(CharSequence str1, CharSequence str2) {
		//判断字符是否为null,两个为null字符串使用 != 比较结果为false
		if ((str1 == null) != (str2 == null)) {//进入这里说明有一个字符串为null
			return true;
		} else if (str1 == null) {//进入这里说明两字符串都为null
			return false;
		}

		//长度比较
		final int length = str1.length();
		if (length != str2.length()) {
			return true;
		}

		//循环遍历比较每个字符
		for (int i = 0; i < length; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				return true;
			}
		}
		return false;
	}
}
