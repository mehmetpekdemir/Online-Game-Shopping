package com.handstand.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.entity.Category;
import com.handstand.service.impl.CategoryServiceImpl;
import com.handstand.util.CheckUtils;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@WebServlet(urlPatterns = { "/editCategory" })
public class CategoryEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String CATEGORIES = "categories";
	private static final String ERROR_MESSAGE_TEXT = "Hatalı işlem yaptınız.";
	private static String errorMessage = null;

	// Categoriyi id degerine göre arıyor.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = (String) request.getParameter("id");
		String platformName = request.getParameter("platformName");
		int convertId = CheckUtils.castInt(id);
		Category category = null;
		try {
			CategoryServiceImpl categoryService = new CategoryServiceImpl();
			category = categoryService.findCategory(convertId, platformName);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorMessage = ERROR_MESSAGE_TEXT;
		}

		// eğer bi hata yoksa
		if (errorMessage != null && category == null) {
			response.sendRedirect(request.getServletPath() + "/listCategory");
			return;
		}

		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(CATEGORIES, category);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/categoryEditView.jsp");
		dispatcher.forward(request, response);

	}

	// Güncelleme yapıyorum.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String id = (String) request.getParameter("id");
		String categoryName = request.getParameter("categoryName");
		int convertId = CheckUtils.castInt(id);
		Category category = new Category(convertId, categoryName);
		try {
			CategoryServiceImpl categoryService = new CategoryServiceImpl();
			categoryService.updateCategory(category);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorMessage = "Düzenleme islemi hatalı yapıldı lütfen kontrol ediniz.";
		}

		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(CATEGORIES, category);
		if (errorMessage != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/categoryEditView.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/listCategory");
		}
	}

}
