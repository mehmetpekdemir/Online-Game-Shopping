package com.handstand.servlet;

import java.io.IOException;
import java.util.List;

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
@WebServlet(urlPatterns = { "/listPlatform" })
public class PlatformListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PLATFORMS = "platforms";

	// Platformları listelemek icin yazıyorum
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Platform> platforms = null;
		try {
			PlatformServiceImpl platformService = new PlatformServiceImpl();
			platforms = platformService.listPlatform();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		request.setAttribute(PLATFORMS, platforms);

		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/platformListView.jsp");
		dispatcher.forward(request, response);
	}

	// doGet methoduna yönleniyor.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}
}
