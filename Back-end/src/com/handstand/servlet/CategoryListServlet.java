package com.handstand.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.entity.Platform;
import com.handstand.service.impl.CategoryServiceImpl;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@WebServlet(urlPatterns = { "/listCategory" })
public class CategoryListServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String PlATFORM = "platform";

	// Categorileri listeliyor.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		Platform platform = new Platform();
		try {
			CategoryServiceImpl categoryService = new CategoryServiceImpl();
			platform.setCategories(categoryService.listCategory());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		request.setAttribute(PlATFORM, platform);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/categoryListView.jsp");
		dispatcher.forward(request, response);
	}

	// doGet e yönleniyor.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		doGet(request, response);
	}

}
