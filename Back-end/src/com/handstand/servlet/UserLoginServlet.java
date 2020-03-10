package com.handstand.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handstand.entity.Admin;
import com.handstand.entity.Customer;
import com.handstand.service.AdminServiceImpl;
import com.handstand.service.impl.CustomerServiceImpl;
import com.handstand.util.AuthenticationHelper;
import com.handstand.util.CheckUtils;
import com.handstand.util.MyUtils;

/**
 * 
 * @author MEHMET PEKDEMIR ,YUSUF YUCEDAG
 *
 */
@WebServlet(urlPatterns = { "/userLogin" })
public class UserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String ADMIN = "admin";
	private static final String CUSTOMER = "customer";
	private static final String ERROR_MESSAGE = "Kullanıcı adı veya şifreniz yanlış.Lütfen tekrar deneyiniz.";
	private static final String ERROR = "errorMessage";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		boolean remember = CheckUtils.rememberMeCheck(rememberMe);
		boolean check = AuthenticationHelper.isAdmin(emailAddress, password);

		if (check) {
			AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
			Admin admin = null;
			try {
				admin = adminServiceImpl.findAdmin(emailAddress, password);
			} catch (ClassNotFoundException | SQLException exception) {
				exception.printStackTrace();
			}

			if (admin == null) {
				request.setAttribute(ERROR, ERROR_MESSAGE);
				RequestDispatcher dispatcher = this.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
				dispatcher.forward(request, response);
				return;
			}
			HttpSession session = request.getSession();
			MyUtils.storeLoginedAdmin(session, admin);
			request.setAttribute(ADMIN, admin);

			if (remember) {
				MyUtils.storeAdminCookie(response, admin);
			} else {
				MyUtils.deleteAdminCookie(response);
			}

			response.sendRedirect(request.getContextPath() + "/adminInfo");

		} else {
			// cutomerService ile customerDao yu çağırıp findCustomer fonk. çalıştırıyorum
			CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
			Customer customer = null;
			try {
				customer = customerServiceImpl.findCustomer(emailAddress, password);
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}

			if (customer == null) {
				request.setAttribute(ERROR, ERROR_MESSAGE);
				RequestDispatcher dispatcher = this.getServletContext()
						.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
				dispatcher.forward(request, response);
				return;
			}
			// oturumu alıyorum
			HttpSession session = request.getSession();
			// Giriş yapan customerı kaydediyorum
			MyUtils.storeLoginedCustomer(session, customer);
			request.setAttribute(CUSTOMER, customer);

			if (remember) {
				MyUtils.storeCustomerCookie(response, customer);
			} else {
				MyUtils.deleteCustomerCookie(response);
			}

			response.sendRedirect(request.getContextPath() + "/customerInfo");
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		dispatcher.forward(request, response);
	}

}
