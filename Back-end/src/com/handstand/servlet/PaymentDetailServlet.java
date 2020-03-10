package com.handstand.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handstand.entity.Customer;
import com.handstand.service.impl.BillServiceImpl;
import com.handstand.service.impl.OrderServiceImpl;
import com.handstand.util.CheckUtils;
import com.handstand.util.MyUtils;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet(urlPatterns = { "/paymentDetail" })
public class PaymentDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String COMPLETE_MESSAGE_TEXT = "Sipariþiniz baþarýlý bir þekilde alýnmýþtýr. Bizi tercih ettiðiniz için teþekkür ederiz";
	private static final String ERROR = "errorMessage";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/checkOrderDetailsView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String addressIdString = request.getParameter("addressId");
		int addressId = CheckUtils.castInt(addressIdString);
		String paymentOptionString = request.getParameter("paymentOption");
		int paymentOption = CheckUtils.castInt(paymentOptionString);
		HttpSession session = request.getSession();
		Customer customer = MyUtils.getLoginedCustomer(session);
		String emailAddress = customer.getEmailAddress();
		Date date = new Date();

		BillServiceImpl billServiceImpl = new BillServiceImpl();
		try {
			billServiceImpl.insertBill(emailAddress, addressId, date, paymentOption);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}

		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		try {
			orderServiceImpl.updateOrder(emailAddress, date);
		} catch (ClassNotFoundException | SQLException | ParseException exception) {
			exception.printStackTrace();
		}
		
		request.setAttribute(ERROR, COMPLETE_MESSAGE_TEXT);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
		requestDispatcher.forward(request, response);

	}
}