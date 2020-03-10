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
import com.handstand.entity.Customer;
import com.handstand.service.AdminServiceImpl;
import com.handstand.service.impl.CustomerServiceImpl;
import com.handstand.util.AuthenticationHelper;
import com.handstand.util.MyUtils;
import com.mysql.jdbc.Connection;

/**
 * 
 * @author MEHMET PEKDEMIR , YUSUF YUCEDAG
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
		String firstName = request.getParameter("firstName");
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		boolean isAdminCheck = AuthenticationHelper.isAdmin(emailAddress, password);

		if (isAdminCheck = true && firstName == null) {
			HttpServletRequest request2 = (HttpServletRequest) request;
			HttpSession session = request2.getSession();

			Admin adminInSession = (Admin) MyUtils.getLoginedAdmin(session);
			if (adminInSession != null) {
				session.setAttribute(COOKIE_CHECKED, CHECKED);
				chain.doFilter(request, response);
				return;
			}

			Connection connection = MyUtils.getStoredConnection(request);

			String checked2 = (String) session.getAttribute(COOKIE_CHECKED);
			if (checked2 == null && connection != null) {
				// String emailAddress2 = MyUtils.getAdminNameInCookie(request2);
				try {
					AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
					Admin admin = adminServiceImpl.findAdmin(emailAddress);
					MyUtils.storeLoginedAdmin(session, admin);
				} catch (SQLException sqlException) {
					sqlException.printStackTrace();
				} catch (ClassNotFoundException classNotFoundException) {
					classNotFoundException.printStackTrace();
				}
				session.setAttribute(COOKIE_CHECKED, CHECKED);
			}
		} else if (emailAddress != null && isAdminCheck == false) {
			HttpServletRequest requestDoFilter = (HttpServletRequest) request;
			HttpSession session = requestDoFilter.getSession();
			Customer customerInSession = (Customer) MyUtils.getLoginedCustomer(session);

			if (customerInSession != null) {
				session.setAttribute(COOKIE_CHECKED, CHECKED);
				chain.doFilter(request, response);
				return;
			}
			Connection connection = MyUtils.getStoredConnection(request);
			String checked2 = (String) session.getAttribute(COOKIE_CHECKED);

			if (connection != null && checked2 == null) {
				// String emailAddress2 = MyUtils.getCustomerNameInCookie(requestDoFilter);
				CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
				try {
					Customer customer = customerServiceImpl.findCustomer(emailAddress, password);
					MyUtils.storeLoginedCustomer(session, customer);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				session.setAttribute(COOKIE_CHECKED, CHECKED);
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}