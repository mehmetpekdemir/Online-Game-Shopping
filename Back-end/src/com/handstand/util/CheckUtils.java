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

		ProductService productService = new ProductService();
		Product product = null;
		try {
			product = productService.findProduct(itemCode);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}

		String regex = "\\w+";
		if (product == null || !itemCode.matches(regex)) {
			return true;
		}
		return false;
	}

	// Beni Hatırla kontrol ediliyor. Input:String Output : boolean
	public static boolean rememberMeCheck(String rememberMe) {
		return REMEMBER_ME_YES.equals(rememberMe);
	}

}
