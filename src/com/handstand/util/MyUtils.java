package com.handstand.util;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.handstand.entity.Admin;
import com.handstand.entity.User;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class MyUtils {

	public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTİON"; // Baglantı adı için gerekli
	private static final String ATT_NAME_USER_MAIL = "ATTRIBUTE_FOR_STORE_USER_MAIL_IN_COOKIE";
	private static final String USER = "user";

	public static void storeConnection(ServletRequest request, Connection connection) {
		request.setAttribute(ATT_NAME_CONNECTION, connection);
	}

	public static Connection getStoredConnection(ServletRequest request) {
		Connection connection = (Connection) request.getAttribute(ATT_NAME_CONNECTION);
		return connection;
	}

	// Giriş yapan kullanıcı oturumu
	public static void storeLoginedUser(HttpSession session, User user) {
		session.setAttribute(USER, user);
	}

	public static User getLoginedUser(HttpSession session) {
		User user = (User) session.getAttribute(USER);
		return user;
	}

	// Giriş yapan admin cookie bilgisi süresi
	public static void storeAdminCookie(HttpServletResponse response, Admin admin) {
		Cookie cookie = new Cookie(ATT_NAME_USER_MAIL, admin.getEmailAddress());
		cookie.setMaxAge(24 * 60 * 60); // Süreyi degiştirebilirim.(1 günlük yaptım.)
		response.addCookie(cookie);
	}

	// Cookieyi kontrol amaçlı yazdım.
	public static String getAdminNameInCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (ATT_NAME_USER_MAIL.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	// Adminin cookiesini sildim.
	public static void deleteAdminCookie(HttpServletResponse response) {
		Cookie cookie = new Cookie(ATT_NAME_USER_MAIL, null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
}
