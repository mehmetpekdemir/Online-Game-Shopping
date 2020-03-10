package com.handstand.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import com.handstand.entity.OrderItem;

/**
 * 
 * @author MEHMET PEKDEMÄ°R , YUSUF YUCEDAG
 *
 */
public class CheckUtils {

	private static final String REMEMBER_ME_YES = "Yes";
	private static final String STOCK_MESSAGE = "isimli �r�n(ler) sto�umuzda mevcut de�ildir.L�tfen sepetinizi tekrar kontrol ediniz.";

	// Double cast ediyorum.
	public static double castDouble(String castString) {
		double castDouble = 0;
		try {
			castDouble = Double.parseDouble(castString);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return castDouble;
	}

	public static int castInt(String castString) {
		int castInt = 0;
		try {
			castInt = Integer.parseInt(castString);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return castInt;
	}

	// Regex control
	public static boolean isRegexControl(String itemCode) {
		String regex = "\\w+";
		if (itemCode == null || !itemCode.matches(regex)) {
			return false;
		}
		return true;
	}

	// Beni Hatirla kontrol ediliyor.
	public static boolean rememberMeCheck(String rememberMe) {
		return REMEMBER_ME_YES.equals(rememberMe);
	}

	/*
	 * Sepete eklenen urunun adetine gore fiyatinin hesaplanmasi input : adet sayisi
	 * ve bir urunun ucreti return : double ,toplam fiyat author : YUSUF YUCEDAG
	 */
	public static double calculatePrice(int quantity, double price) {
		return price * quantity;
	}

	/*
	 * Date veri tipinin formatlanarak Stringe cevrilmesi icin yazildi input : date
	 * return : String author : YUSUF YUCEDAG
	 */
	public static String dateToString(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.format(date);
		return dateFormat.format(date);
	}

	/*
	 * String veri tipinin formatlanarak Date veri tipine cevrilmesi icin yazildi
	 * input : dateString, cevrilmek istenen String return : Date author : YUSUF
	 * YUCEDAG
	 */
	public static Date stringToDate(String dateString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateFormat.parse(dateString);
		return date;
	}

	/*
	 * Sepetin toplam fiyatini hesaplamak icin yazildi input: OrderItem sınıfının
	 * nesnelerinin collectionı return: toplam fiyat degeri author : YUSUF YUCEDAG
	 */
	public static double calculateTotalPrice(Collection<OrderItem> orderItems) {
		double totalPrice = 0;
		for (OrderItem orderItem : orderItems) {
			totalPrice += orderItem.getTotalPrice();
		}
		return totalPrice;
	}

	/*
	 * Stokta olmayan urunlerin isimlerini duzenlemek amaciyla yazildi
	 * input: OrderItem sınıfının nesnelerinin collectionu
	 * return: Sepette olmayan urunlerin isimlerinin duzenlenmis hali
	 * author : YUSUF YUCEDAG
	 * */
	public static StringBuilder namesOfProductsNotInStock(Collection<OrderItem> orderItems) {
		StringBuilder namesOfProducts = new StringBuilder();
		String templateString = null;
		for (OrderItem orderItem : orderItems) {
			templateString = orderItem.getProduct().getProductName();
			namesOfProducts.append(templateString);
			namesOfProducts.append(", ");
		}
		namesOfProducts.append(STOCK_MESSAGE);
		return namesOfProducts;
	}
}
