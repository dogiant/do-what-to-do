package com.dogiant.cms.utils;

import java.util.Random;

public class RandomUtil {

	public static char[] charUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();;
	public static char[] charUpperAndD = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			.toCharArray();
	public static char[] charLower = "abcdefghijklmnopqrstuvwxyz".toCharArray();;
	public static char[] charLowerAndD = "0123456789abcdefghijklmnopqrstuvwxyz"
			.toCharArray();
	public static char[] charD = "0123456789".toCharArray();
	public static char[] char16 = "0123456789abcdef".toCharArray();

	public static String getRandomString(char[] chars, int randomCount) {
		try {
			if (chars == null || chars.length < 1)
				return null;
			int charsInt = chars.length;
			char[] rs = new char[randomCount];
			Random r = new Random();
			for (int i = 0; i < randomCount; i++) {
				int rthis = r.nextInt(charsInt);
				rs[i] = chars[rthis];
			}
			String rsstring = String.valueOf(rs);
			return rsstring;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getRandomInteger(int max) {
		try {
			Random r = new Random();
			int rthis = r.nextInt(max);
			return rthis;
		} catch (Exception e) {
			e.printStackTrace();
			return Integer.MIN_VALUE;
		}
	}

	public static void main(String[] args) {

		// java.util.Random r=new java.util.Random();
		for (int i = 0; i < 100; i++) {
			System.out.println(getRandomInteger(100));
		}
	}
}
