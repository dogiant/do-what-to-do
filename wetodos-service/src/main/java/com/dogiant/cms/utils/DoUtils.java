package com.dogiant.cms.utils;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoUtils {

	/**
	 * 
	 * @param source
	 *            源
	 * @param len
	 *            字节
	 * @param symbol
	 *            符号
	 * @return
	 */
	public static String biteOffString(String source, Integer len, String symbol) {
		int counterOfDoubleByte = 0;
		byte[] b;
		String temp = "";

		try {
			b = source.getBytes("GBK");
			if (b.length <= len) {
				return source;
			}
			for (int i = 0; i < len; i++) {
				if (b[i] < 0) {
					counterOfDoubleByte++;
				}
			}
			if (counterOfDoubleByte % 2 == 0) {
				temp = new String(b, 0, len, "GBK") + symbol;
			} else {
				temp = new String(b, 0, len - 1, "GBK") + symbol;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return temp;
	}

	public static boolean isMobileNO(String mobiles){       
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");       
        Matcher m = p.matcher(mobiles);   
        return m.matches();       
    } 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
