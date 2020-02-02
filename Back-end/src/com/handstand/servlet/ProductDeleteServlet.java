package com.handstand.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handstand.service.impl.ProductServiceImpl;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
@WebServlet(urlPatterns = { "/deleteProduct" })
public class ProductDeleteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	// Ürün silmek için kullanıyoruz.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String code = (String) request.getParameter("code");
		try {
			ProductServiceImpl productService = new ProductServiceImpl();
			productService.deleteProduct(code);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/listProduct");
	}

	// Eğer calışırsa doGet e yönleniyor.
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
