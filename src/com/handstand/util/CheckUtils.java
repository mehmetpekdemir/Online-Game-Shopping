package com.handstand.util;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class CheckUtils {

	private static final String REMEMBER_ME_YES = "Yes";

	// double cast ediyorum. Input : String Output : double
	public static double castDouble(String castString) {
		double castDouble = 0;
		try {
			castDouble = Double.parseDouble(castString);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return castDouble;
	}

	// int cast ediyorum Input:String Output : int
	public static int castInt(String castString) {
		int castInt = 0;
		try {
			castInt = Integer.parseInt(castString);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return castInt;
	}

	// Regex control yapyıorum. Input:String Output:boolean
	public static boolean isRegexControl(String itemCode) {
		String regex = "\\w+";
		if (itemCode == null || !itemCode.matches(regex)) {
			return false;
		}
		return true;
	}

	// Beni Hatırla kontrol ediliyor. Input:String Output : boolean
	public static boolean rememberMeCheck(String rememberMe) {
		return REMEMBER_ME_YES.equals(rememberMe);
	}

}
