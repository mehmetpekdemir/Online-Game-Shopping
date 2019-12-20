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
import com.handstand.util.CheckUtils;
/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
@WebServlet(urlPatterns = { "/editPlatform" })
public class PlatformEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ERROR_MESSAGE = "errorMessage";
	private static final String ERROR_MESSAGE_TEXT = "Hatalı işlem yaptınız.";
	private static final String PLATFORMS = "platforms";
	private static final String PLATFORM = "platform";
	private static String errorMessage = null;

	// platform id değerine göre arayıp yönlendirme islemlerimi yapıyorum.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = (String) request.getParameter("id");
		int convertId = CheckUtils.castInt(id);
		Platform platform = null;
		try {
			PlatformServiceImpl platformService = new PlatformServiceImpl();
			platform = platformService.findPlatform(convertId);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorMessage = ERROR_MESSAGE_TEXT;
		}

		// eğer bi hata yoksa
		if (errorMessage != null && platform == null) {
			response.sendRedirect(request.getServletPath() + "/listPlatform");
			return;
		}

		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(PLATFORMS, platform);
		RequestDispatcher dispatcher = request.getServletContext()
				.getRequestDispatcher("/WEB-INF/views/platformEditView.jsp");
		dispatcher.forward(request, response);
	}

	// gönderilen ürün id'si ile güncelleme yapıyorum .
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String id = (String) request.getParameter("id");
		String platformName = request.getParameter("platformName");
		int convertId = CheckUtils.castInt(id);
		Platform platform = new Platform(convertId, platformName);
		try {
			PlatformServiceImpl platformService = new PlatformServiceImpl();
			platformService.updatePlatform(platform);
		} catch (Exception exception) {
			exception.printStackTrace();
			errorMessage = "Ürün düzenleme islemi hatalı yapıldı lütfen kontrol ediniz.";
		}

		request.setAttribute(ERROR_MESSAGE, errorMessage);
		request.setAttribute(PLATFORM, platform);
		if (errorMessage != null) {
			RequestDispatcher dispatcher = request.getServletContext()
					.getRequestDispatcher("/WEB-INF/views/platformEditView.jsp");
			dispatcher.forward(request, response);
		} else {
			response.sendRedirect(request.getContextPath() + "/listPlatform");
		}
	}
}
