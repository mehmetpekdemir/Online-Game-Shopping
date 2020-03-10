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
import com.handstand.entity.Order;
import com.handstand.entity.OrderItem;
import com.handstand.entity.Product;
import com.handstand.service.ProductServiceImpl;
import com.handstand.service.impl.OrderItemServiceImpl;
import com.handstand.service.impl.OrderServiceImpl;
import com.handstand.util.CheckUtils;
import com.handstand.util.MyUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet(urlPatterns = { "/insertOrderItem" })
public class OrderItemInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CODE = "code";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// code u aliyorum geri set edicem viewde gozuksun diye
		String code = request.getParameter("code");
		// customer giris yapmism bakiyorum sepete eklemeden once
		HttpSession session = request.getSession();
		Customer customer = (Customer) MyUtils.getLoginedCustomer(session);
		if (customer == null) {
			response.sendRedirect(request.getContextPath() + "/userLogin");
			return;
		}

		// Order tanimli degilse tanimlamak amaciyla kontrol ediyorum
		String emailAddress = customer.getEmailAddress();
		Order checkOrder = null;
		OrderServiceImpl orderServiceImpl = new OrderServiceImpl();
		try {
			checkOrder = orderServiceImpl.findOrder(emailAddress);
		} catch (ClassNotFoundException | SQLException | ParseException exception) {
			exception.printStackTrace();
		}

		if (checkOrder == null) {
			Date date = new Date();
			Order order = new Order(date, false);
			try {
				orderServiceImpl.insertOrder(emailAddress, order);
			} catch (ClassNotFoundException | SQLException exception) {
				exception.printStackTrace();
			}
		}

		// code u set edip viewe dallaniyorum
		request.setAttribute(CODE, code);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insertOrderItemView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// customerin oturumunu alip emailAddresini cekiyorum
		HttpSession session = request.getSession();
		Customer customer = (Customer) MyUtils.getLoginedCustomer(session);
		String emailAddress = customer.getEmailAddress();

		// code ve girilen adeti cekiyorum
		String code = request.getParameter("code");
		String quantityString = request.getParameter("quantity");

		// code a gore urunu buluyorum
		Product product = null;
		try {
			ProductServiceImpl productServiceImpl = new ProductServiceImpl();
			product = productServiceImpl.findProduct(code);
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		// adeti int ceviriyorum
		int quantity = CheckUtils.castInt(quantityString);
		// adet*Fiyat yapiyorum
		double totalPrice = CheckUtils.calculatePrice(quantity, product.getPrice());

		// OrderItem tablosuna ekleme yapicam
		OrderItem orderItem = new OrderItem(quantity, totalPrice);
		OrderItemServiceImpl orderItemServiceImpl = new OrderItemServiceImpl();
		try {
			orderItemServiceImpl.insertOrderItem(code, emailAddress, orderItem);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/listProduct");
	}
}
