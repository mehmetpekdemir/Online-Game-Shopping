package com.handstand.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.service.impl.AddressServiceImpl;
import com.handstand.util.CheckUtils;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
@WebServlet(urlPatterns = { "/deleteAddress" })
public class AddressDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idString = request.getParameter("id");
		int id = CheckUtils.castInt(idString);
		try {
			AddressServiceImpl addressServiceImpl = new AddressServiceImpl();
			addressServiceImpl.deleteAddress(id);

		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/listAddress");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
