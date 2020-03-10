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

import com.handstand.entity.Address;
import com.handstand.entity.Customer;
import com.handstand.service.impl.AddressServiceImpl;
import com.handstand.util.MyUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet("/insertAddress")
public class AddressInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADDRESS = "address";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/insertAddressView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String addressTitle = request.getParameter("addressTitle");
		String addressDetail = request.getParameter("addressDetail");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String postCode = request.getParameter("postCode");
		
		HttpSession session = request.getSession();
		Customer customer = (Customer) MyUtils.getLoginedCustomer(session);
		String emailAddress = customer.getEmailAddress();
		Address address = new Address(addressTitle, addressDetail, city, district, postCode);
		
		AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
		try {
			addressServiceImpl.insertAddress(address, emailAddress);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		request.setAttribute(ADDRESS, address);
		response.sendRedirect(request.getContextPath() + "/customerInfo");
	}
}
