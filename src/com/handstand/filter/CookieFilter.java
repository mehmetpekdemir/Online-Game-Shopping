package com.handstand.filter;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.handstand.entity.Admin;
import com.handstand.service.AdminService;
import com.handstand.util.MyUtils;
import com.mysql.jdbc.Connection;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
@WebFilter(filterName = "cookieFilter", urlPatterns = { "/*" })
public class CookieFilter implements Filter {

	private static final String COOKIE_CHECKED = "COOKIE_CHECKED";
	private static final String CHECKED = "CHECKED";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		HttpSession session = request2.getSession();

		Admin adminInSession = (Admin) MyUtils.getLoginedUser(session);
		if (adminInSession != null) {
			session.setAttribute(COOKIE_CHECKED, CHECKED);
			chain.doFilter(request, response);
			return;
		}

		Connection connection = MyUtils.getStoredConnection(request);

		String checked = (String) session.getAttribute(COOKIE_CHECKED);
		if (checked == null && connection != null) {
			String emailAddress = MyUtils.getAdminNameInCookie(request2);
			try {
				AdminService adminService = new AdminService();
				Admin admin = adminService.findAdmin(emailAddress);
				MyUtils.storeLoginedUser(session, admin);
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			} catch (ClassNotFoundException classNotFoundException) {
				classNotFoundException.printStackTrace();
			}
			session.setAttribute(COOKIE_CHECKED, CHECKED);
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}