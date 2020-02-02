package com.handstand.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.entity.Platform;
import com.handstand.service.impl.PlatformServiceImpl;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@WebServlet(urlPatterns = { "/createPlatform" })
public class PlatformCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String ERROR_MESSAGE_TEXT = "Platform ismi kullanımda veya hatalı işlem yaptınız.";
	private static final String PLATFORM = "platform";
	private static String errorMessage = null;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/platformCreateView.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String platformName = request.getParameter("platformName");
		Platform platform = new Platform(platformName);

		try {
			PlatformServiceImpl platformService = new PlatformServiceImpl();
			platformService.addPlatform(platform);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorMessage = ERROR_MESSAGE_TEXT;
		}

		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(PLATFORM, platform);

		if (errorMessage != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/errorView.jsp");
			dispatcher.forward(request, response);
		} else {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/homeView.jsp");
			dispatcher.forward(request, response);
		}
	}

}
