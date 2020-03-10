package com.handstand.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.entity.Address;
import com.handstand.service.impl.AddressServiceImpl;
import com.handstand.util.CheckUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet(urlPatterns = { "/editAddress" })
public class AddressEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADDRESS = "address";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = CheckUtils.castInt(idString);
		Address address = null;
		try {
			AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
			address = addressServiceImpl.findAddress(id);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		if (address == null) {
			response.sendRedirect(request.getServletPath() + "/customerInfo");
			return;
		}
		request.setAttribute(ADDRESS, address);
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/editAddressView.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		String addressTitle = request.getParameter("addressTitle");
		String addressDetail = request.getParameter("addressDetail");
		String city = request.getParameter("city");
		String district = request.getParameter("district");
		String postCode = request.getParameter("postCode");
		int id = CheckUtils.castInt(idString);
		Address address = new Address(id, addressTitle, addressDetail, city, district, postCode);
		try {
			AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
			addressServiceImpl.updateAddress(address);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		request.setAttribute(ADDRESS, address);
		response.sendRedirect(request.getContextPath() + "/customerInfo");
	}
}
