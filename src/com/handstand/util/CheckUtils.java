package com.handstand.util;
/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class CheckUtils {

	private static final String REMEMBER_ME_YES = "Yes";

	// Floata cast ediyorum.
	public static float castFloat(String priceString) {
		float price = 0;
		try {
			price = Float.parseFloat(priceString);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return price;
	}
	
	public static int castInt(String stockAmountString) {
		int stockAmount = 0;
		try {
			stockAmount = Integer.parseInt(stockAmountString);
		} catch (Exception exception) {
			exception.printStackTrace();		
		}
		return stockAmount;
	}
	
	// Regex control
	public static String regexControl(String itemCode) {
		String errorString = null;
		String regex = "\\w+";
		if (itemCode == null || !itemCode.matches(regex)) {
			errorString = "Ürün kodu kullanımda";
		}
		return errorString;
	}

	// Beni Hatırla kontrol ediliyor.
	public static boolean rememberMeCheck(String rememberMe) {
		return REMEMBER_ME_YES.equals(rememberMe);
	}

}
