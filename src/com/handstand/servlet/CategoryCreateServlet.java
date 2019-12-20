package com.handstand.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.entity.Category;
import com.handstand.entity.Platform;
import com.handstand.service.impl.CategoryServiceImpl;
import com.handstand.service.impl.PlatformServiceImpl;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@WebServlet(urlPatterns = { "/createCategory" })
public class CategoryCreateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String CATEGORY = "category";
	private static final String PLATFORMS = "platforms";
	private static String errorMessage = null;

	//Platformları listeliyor
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
				.getRequestDispatcher("/WEB-INF/views/categoryCreateView.jsp");
		dispatcher.forward(request, response);
	}

	//Yeni Categori yaratıyor
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String platformName = request.getParameter("platformName");
		String categoryName = request.getParameter("categoryName");
		Category category = new Category(platformName, categoryName);

		try {
			CategoryServiceImpl categoryService = new CategoryServiceImpl();
			categoryService.addCategory(category);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorMessage = "Category ismi kullanımda veya hatalı işlem yaptınız.";
		}

		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(CATEGORY, category);

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
