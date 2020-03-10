package com.handstand.servlet;

import java.io.IOException;
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
import com.handstand.service.impl.AddressServiceImpl;
import com.handstand.util.MyUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet("/listAddress")
public class AddressListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CUSTOMER = "customer";
	private static final String ERROR = "errorMessage";
	private static final String ERROR_MESSAGE_TEXT = "Kayıtlı adresiniz bulunmamaktadır.Lütfen profil ekranınızdan adres ekleyiniz.";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Customer customer = (Customer) MyUtils.getLoginedCustomer(session);
		String emailAddress = customer.getEmailAddress();
		Collection<Address> addresses = new ArrayList<Address>();
		try {
			AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
			addresses = addressServiceImpl.listAddress(emailAddress);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		if(addresses.isEmpty()) {
			request.setAttribute(ERROR, ERROR_MESSAGE_TEXT);
			RequestDispatcher requestDispatcher = getServletContext()
					.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
			requestDispatcher.forward(request, response);
			return;
		}
		customer.setAddresses(addresses);
		request.setAttribute(CUSTOMER, customer);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/listAddressView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
