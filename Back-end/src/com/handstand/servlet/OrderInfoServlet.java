package com.handstand.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handstand.entity.Customer;
import com.handstand.entity.Order;
import com.handstand.entity.OrderItem;
import com.handstand.service.impl.OrderServiceImpl;
import com.handstand.util.CheckUtils;
import com.handstand.util.MyUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet(urlPatterns = { "/orderInfo" })
public class OrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE_TEXT = "Sepetiniz boþ. Ürünlerimize göz attýnýz mý?";
	private static final String ERROR = "errorMessage";
	private static final String ORDER = "order";
	private static final String TOTAL_ORDER_PRICE = "totalOrderPrice";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Customer customer = MyUtils.getLoginedCustomer(session);
		String emailAddress = customer.getEmailAddress();
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		Collection<OrderItem> checkOrderItem = new ArrayList<OrderItem>();
		Order order = null;

		try {
			order = orderServiceImpl.findOrder(emailAddress);
		} catch (ClassNotFoundException | SQLException | ParseException exception) {
			exception.printStackTrace();
		}
		if (order != null) {
			checkOrderItem = order.getOrderItems();
		}
		if (order == null || checkOrderItem.isEmpty()) {
			request.setAttribute(ERROR, ERROR_MESSAGE_TEXT);
			RequestDispatcher dispatcher = this.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
			dispatcher.forward(request, response);
			return;
		}

		double totalOrderPrice = CheckUtils.calculateTotalPrice(order.getOrderItems());
		request.setAttribute(TOTAL_ORDER_PRICE, totalOrderPrice);
		request.setAttribute(ORDER, order);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/orderInfoView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
