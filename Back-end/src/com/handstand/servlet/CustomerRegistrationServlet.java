package com.handstand.servlet;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.entity.Customer;
import com.handstand.service.impl.CustomerServiceImpl;
import com.handstand.util.AuthenticationHelper;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet(urlPatterns = { "/customerRegister" })
public class CustomerRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CUSTOMER = "customer";
	private static final String ERROR_MESSAGE = "E-mail adresi kayýtlý bir kullanýcýya ait."
			+ "Lütfen baþka bir hesap ile kaydolmayý deneyiniz.";
	private static final String ERROR = "errorMessage";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/customerRegisterView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");
		boolean checkEmail = false;

		try {
			checkEmail = AuthenticationHelper.emailController(emailAddress);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}

		if (checkEmail) {
			Customer customer = new Customer(phoneNumber, firstName, lastName, emailAddress, password);
			CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

			try {
				customerServiceImpl.insertCustomer(customer);
			} catch (ClassNotFoundException | SQLException exception) {
				exception.printStackTrace();
			}
			request.setAttribute(CUSTOMER, customer);
			response.sendRedirect(request.getContextPath() + "/userLogin");

		} else {
			request.setAttribute(ERROR, ERROR_MESSAGE);
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
			dispatcher.forward(request, response);
		}
	}
}
