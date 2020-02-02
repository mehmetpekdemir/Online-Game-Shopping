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

import com.handstand.entity.Admin;
import com.handstand.service.impl.AdminServiceImpl;
import com.handstand.util.MyUtils;
/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@WebServlet("/editAdmin")
public class AdminEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADMIN = "admin";

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext()
				.getRequestDispatcher("/WEB-INF/views/adminInfoView.jsp");
		requestDispatcher.forward(request, response);
	}

//Adminin bilgilerini güncellemek icin tutuyorum.
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		Admin admin = new Admin(firstName, lastName, emailAddress, password);
		MyUtils.storeLoginedAdmin(session, admin);
		AdminServiceImpl adminService = new AdminServiceImpl();

		try {
			adminService.updateAdmin(admin);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}

		request.setAttribute(ADMIN, admin);
		response.sendRedirect(request.getContextPath() + "/adminInfo");
	}

}
