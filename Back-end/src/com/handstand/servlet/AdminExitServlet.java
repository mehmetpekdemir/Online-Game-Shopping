package com.handstand.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handstand.util.MyUtils;
/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@WebServlet(urlPatterns = { "/adminExit" })
public class AdminExitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {	
		HttpSession session = request.getSession();
		session.invalidate();	
		MyUtils.deleteAdminCookie(response);
		response.sendRedirect(request.getContextPath() + "/home");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
