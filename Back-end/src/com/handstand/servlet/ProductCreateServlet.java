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
import com.handstand.entity.Product;
import com.handstand.service.impl.CategoryServiceImpl;
import com.handstand.service.impl.PlatformServiceImpl;
import com.handstand.service.impl.ProductServiceImpl;
import com.handstand.util.AuthenticationHelper;
import com.handstand.util.CheckUtils;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
@WebServlet(urlPatterns = { "/createProduct" })
public class ProductCreateServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String CATEGORIES = "categories";
	private static final String PLATFORMS = "platforms";
	private static final String PRODUCT = "product";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String ERROR_MESSAGE_TEXT = "Ürün kodu kullanımda veya hatalı işlem yaptınız.";
	private static String errorMessage = null;

	// Ürünleri ve kategorileri listelemek için kullanıyorum.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		List<Platform> platforms = null;
		List<Category> categories = null;
		try {
			PlatformServiceImpl platformService = new PlatformServiceImpl(); // Platformu listeledim.
			platforms = platformService.listPlatform();
			CategoryServiceImpl categoryService = new CategoryServiceImpl(); // Categoriyi listeledim.
			categories = categoryService.listCategoryWithPlatformName();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		request.setAttribute(PLATFORMS, platforms);
		request.setAttribute(CATEGORIES, categories);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/productCreateView.jsp");
		dispatcher.forward(request, response);
	}

	// Admin ürün ekleniğinde burası çalışır.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String categoryName = request.getParameter("categoryName");
		String code = (String) request.getParameter("code");
		String productName = (String) request.getParameter("productName");
		String priceString = (String) request.getParameter("price");
		String stockAmountString = request.getParameter("stockAmount");
		String image = request.getParameter("image");
		String description = request.getParameter("description");

		double price = CheckUtils.castDouble(priceString);
		int stockAmount = CheckUtils.castInt(stockAmountString);
		int categoryId = AuthenticationHelper.getCategoryId(categoryName);

		Product product = new Product(categoryId, productName, code, price, stockAmount, image, description);

		if (CheckUtils.isRegexControl(code)) {
			try {
				ProductServiceImpl productService = new ProductServiceImpl();
				productService.addProduct(product);
			} catch (Exception exception) {
				exception.printStackTrace();
				errorMessage = ERROR_MESSAGE_TEXT;
			}
		}
		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(PRODUCT, product);

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
