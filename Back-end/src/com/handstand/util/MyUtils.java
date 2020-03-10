package com.handstand.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.handstand.entity.Admin;
import com.handstand.entity.Customer;

/**
 * 
 * @author MEHMET PEKDEMIR , YUSUF YUCEDAG
 *
 */
public class MyUtils {

	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION"; // Baglanti adi icin gerekli
	private static final String ATT_NAME_ADMIN_MAIL = "ATTRIBUTE_FOR_STORE_ADMIN_MAIL_IN_COOKIE";
	private static final String ATT_NAME_CUSTOMER_MAIL = "ATTRIBUTE_FOR_STORE_CUSTOMER_MAIL_IN_COOKIE";
	private static final String ADMIN = "admin";
	private static final String CUSTOMER = "customer";

	/*
	 * Baglantiyi kayit etmek icin yazildi
	 */
	public static void storeConnection(ServletRequest request, Connection connection) {
		request.setAttribute(ATT_NAME_CONNECTION, connection);
	}

	/*
	 * Kayitli Baglantiyi return etmek icin yazildi
	 */
	public static Connection getStoredConnection(ServletRequest request) {
		Connection connection = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return connection;
	}

	public static void storeLoginedAdmin(HttpSession session, Admin admin) {
		session.setAttribute(ADMIN, admin);
	}

	public static Admin getLoginedAdmin(HttpSession session) {
		Admin admin = (Admin) session.getAttribute(ADMIN);
		return admin;
	}

	/*
	 * Giris yapan customeri kayit etmek icin yazildi
	 */
	public static void storeLoginedCustomer(HttpSession session, Customer customer) {
		session.setAttribute(CUSTOMER, customer);
	}

	/*
	 * Giris yapan customeri return etmek icin yazildi
	 */
	public static Customer getLoginedCustomer(HttpSession session) {
		Customer customer = (Customer) session.getAttribute(CUSTOMER);
		return customer;
	}

	// Giriş yapan admin cookie bilgisi süresi
	public static void storeAdminCookie(HttpServletResponse response, Admin admin) {
		Cookie cookie = new Cookie(ATT_NAME_ADMIN_MAIL, admin.getEmailAddress());
		cookie.setMaxAge(24 * 60 * 60); // Süreyi degiştirebilirim.(1 günlük yaptım.)
		response.addCookie(cookie);
	}

	// Cookieyi kontrol amaçlı yazdım.
	public static String getAdminNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (ATT_NAME_ADMIN_MAIL.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	// Adminin cookiesini sildim.
	public static void deleteAdminCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(ATT_NAME_ADMIN_MAIL, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/*
	 * Kayitli customerin cookiesinin tutulmasi icin yazildi
	 */
	public static void storeCustomerCookie(HttpServletResponse response, Customer customer) {
		Cookie cookie = new Cookie(ATT_NAME_CUSTOMER_MAIL, customer.getEmailAddress());
		cookie.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie);
	}

	/*
	 * Customer cookiesi ile email adresinin return edilmesi icin yazildi
	 */
	public static String getCustomerNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (ATT_NAME_CUSTOMER_MAIL.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/*
	 * Customer cookiesinin silinmesi icin yazildi
	 */
	public static void deleteCustomerCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(ATT_NAME_CUSTOMER_MAIL, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
