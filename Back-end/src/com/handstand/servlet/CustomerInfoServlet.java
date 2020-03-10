package com.handstand.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handstand.entity.Customer;
import com.handstand.util.MyUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet("/customerInfo")
public class CustomerInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CUSTOMER = "customer";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = (Customer) MyUtils.getLoginedCustomer(session);
		if (customer == null) {
			response.sendRedirect(request.getContextPath() + "/userLogin");
			return;
		}
		request.setAttribute(CUSTOMER, customer);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/customerInfoView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
