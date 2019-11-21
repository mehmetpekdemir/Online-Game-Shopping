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
import com.handstand.service.AdminService;
import com.handstand.util.AuthenticationHelper;
import com.handstand.util.CheckUtils;
import com.handstand.util.MyUtils;
/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
@WebServlet(urlPatterns = { "/userLogin" })
public class UserLoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String emailAddress = request.getParameter("emailAddress");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");
		boolean remember = CheckUtils.rememberMeCheck(rememberMe);

		if (AuthenticationHelper.isAdmin(emailAddress, password)) {
			AdminService adminService = new AdminService();
			Admin admin = null;
			try {
				admin = adminService.findAdmin(emailAddress, password);
			} catch (ClassNotFoundException | SQLException exception) {
				exception.printStackTrace();
			}

			HttpSession session = request.getSession();
			MyUtils.storeLoginedUser(session, admin);

			if (remember) {
				MyUtils.storeAdminCookie(response, admin);
			} else {
				MyUtils.deleteAdminCookie(response);
			}

			response.sendRedirect(request.getContextPath() + "/adminInfo");

		} else {
			response.sendRedirect(request.getContextPath() + "/customer");
		}

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/loginView.jsp");
		dispatcher.forward(request, response);
	}

}
