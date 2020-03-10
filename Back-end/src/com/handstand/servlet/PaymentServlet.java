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

import com.handstand.entity.Address;
import com.handstand.entity.Customer;
import com.handstand.entity.Order;
import com.handstand.entity.OrderItem;
import com.handstand.service.impl.AddressServiceImpl;
import com.handstand.service.impl.OrderItemServiceImpl;
import com.handstand.service.impl.OrderServiceImpl;
import com.handstand.util.CheckUtils;
import com.handstand.util.MyUtils;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet("/payment")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR = "errorMessage";
	private static final String ERROR_MESSAGE_TEXT = "Kay�tl� Adresiniz bulunmamaktad�r.L�tfen profil ekran�n�zdan adres ekleyiniz.";
	private static final String CUSTOMER = "customer";
	private static final String TOTAL_ORDER_PRICE = "totalOrderPrice";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Customer customer = MyUtils.getLoginedCustomer(session);
		String emailAddress = customer.getEmailAddress();
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		Collection<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		try {
			orderItems = orderServiceImpl.controlStockOrderItems(emailAddress);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		if (!orderItems.isEmpty()) {
			StringBuilder namesOfProducts = CheckUtils.namesOfProductsNotInStock(orderItems);
			OrderItemServiceImpl orderItemServiceImpl = new OrderItemServiceImpl();
			int productId = 0;
			try {
				for (OrderItem orderItem : orderItems) {
					productId = orderItem.getProduct().getId();
					orderItemServiceImpl.deleteOrderItems(productId, emailAddress);
				}
			} catch (ClassNotFoundException | SQLException exception) {
				exception.printStackTrace();
			}

			request.setAttribute(ERROR, namesOfProducts);
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		
		Collection<Address> addresses = new ArrayList<Address>();
		try {
			AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
			addresses = addressServiceImpl.listAddress(emailAddress);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		if (addresses.isEmpty()) {
			request.setAttribute(ERROR, ERROR_MESSAGE_TEXT);
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		customer.setAddresses(addresses);
		
		Order order = null;
		try {
			order = orderServiceImpl.findOrder(emailAddress);
		} catch (ClassNotFoundException | SQLException | ParseException exception) {
			exception.printStackTrace();
		}

		double totalOrderPrice = CheckUtils.calculateTotalPrice(order.getOrderItems());
		request.setAttribute(TOTAL_ORDER_PRICE, totalOrderPrice);
		request.setAttribute(CUSTOMER, customer);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/checkOrderDetailsView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
