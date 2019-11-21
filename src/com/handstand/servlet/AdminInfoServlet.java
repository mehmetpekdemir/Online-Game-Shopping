package com.handstand.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handstand.entity.Admin;
import com.handstand.util.MyUtils;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */

@WebServlet(urlPatterns = { "/adminInfo" })
public class AdminInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String USER = "user";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		Admin loginedAdmin = (Admin) MyUtils.getLoginedUser(session);// Admin oturumu açtımı kontrol ediyorum.

		// Eğer admin dışında birisi direk sayfaya erişmek istersede kontrol edip o url
		// yönlenmesini de engelliyorum.
		if (loginedAdmin == null) {
			response.sendRedirect(request.getContextPath() + "/userLogin");
			return;
		}

		request.setAttribute(USER, loginedAdmin);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/userInfoView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
