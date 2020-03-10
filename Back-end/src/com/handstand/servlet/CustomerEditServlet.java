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

import com.handstand.entity.Customer;
import com.handstand.service.impl.CustomerServiceImpl;
import com.handstand.util.MyUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet("/editCustomer")
public class CustomerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CUSTOMER = "customer";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/customerInfoView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		String phoneNumber = request.getParameter("phoneNumber");

		HttpSession session = request.getSession();
		Customer customer = new Customer(phoneNumber, firstName, lastName, emailAddress, password);
		MyUtils.storeLoginedCustomer(session, customer);
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();

		try {
			customerServiceImpl.updateCustomer(customer);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		request.setAttribute(CUSTOMER, customer);
		response.sendRedirect(request.getContextPath() + "/customerInfo");
	}
}
