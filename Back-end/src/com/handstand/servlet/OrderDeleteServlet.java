package com.handstand.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handstand.entity.Customer;
import com.handstand.service.impl.OrderItemServiceImpl;
import com.handstand.util.MyUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet(urlPatterns = { "/deleteOrder" })
public class OrderDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = MyUtils.getLoginedCustomer(session);
		String emailAddress = customer.getEmailAddress();

		try {
			OrderItemServiceImpl orderItemServiceImpl = new OrderItemServiceImpl();
			orderItemServiceImpl.deleteOrder(emailAddress);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/orderInfo");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
