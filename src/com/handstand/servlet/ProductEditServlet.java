package com.handstand.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.entity.Product;
import com.handstand.service.impl.ProductServiceImpl;
import com.handstand.util.CheckUtils;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
@WebServlet(urlPatterns = { "/editProduct" })
public class ProductEditServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String PRODUCTS = "products";
	private static final String PRODUCT = "product";
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String ERROR_MESSAGE_TEXT = "Hatalı işlem yaptınız.";
	private static String errorMessage = null;

	//Hangi ürünü güncelleyeceğimizi arıyoruz.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String code = (String) request.getParameter("code");
		Product product = null;
		try {
			ProductServiceImpl productService = new ProductServiceImpl();
			product = productService.findProduct(code);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorMessage = ERROR_MESSAGE_TEXT;
		}
		// eğer bi hata yoksa
		if (errorMessage != null && product == null) {
			response.sendRedirect(request.getServletPath() + "/listProduct");
			return;
		}

		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(PRODUCTS, product);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/productEditView.jsp");
		dispatcher.forward(request, response);
	}

	//Admin ürünü güncellendiğinde burası çalışır
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String platformName = request.getParameter("platformName");
		String categoryName = request.getParameter("categoryName");
		String code = (String) request.getParameter("code");
		String productName = (String) request.getParameter("productName");
		String priceString = (String) request.getParameter("price");
		String stockAmountString = request.getParameter("stockAmount");
		String image = request.getParameter("image");
		String description = request.getParameter("description");

		double price = CheckUtils.castDouble(priceString);
		int stockAmount = CheckUtils.castInt(stockAmountString);

		Product product = new Product(platformName, categoryName, productName, code, price, stockAmount, image,
				description);

		try {
			ProductServiceImpl productService = new ProductServiceImpl();
			productService.updateProduct(product);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorMessage = ERROR_MESSAGE_TEXT;
		}

		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(PRODUCT, product);
		if (errorMessage != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/productEditView.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/listProduct");
		}
	}

}
