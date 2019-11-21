package com.handstand.filter;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import com.handstand.connection.MySQLConnectionUtils;
import com.handstand.util.MyUtils;
import com.handstand.validation.Validation;
import com.mysql.jdbc.Connection;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
@WebFilter(filterName = "jdbcFilter", urlPatterns = { "/*" })
public class JDBCFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request2 = (HttpServletRequest) request;
		if (this.needJDBC(request2)) {
			Connection connection = null;
			try {
				connection = MySQLConnectionUtils.getConnection();
				connection.setAutoCommit(false);
				MyUtils.storeConnection(request, connection);
				chain.doFilter(request, response);
				connection.commit();
			} catch (Exception exception) {
				Validation.validationServletException(exception, connection);
			} finally {
				MySQLConnectionUtils.closeConnection(connection);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
	}

	// İsteğimizin hedefini kontrol ediyorum. Util in altına taşınabilir.
	private boolean needJDBC(HttpServletRequest request) {
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String urlPattern = servletPath;

		if (pathInfo != null) {
			urlPattern = servletPath + "/*";
		}

		// Anahtarım = Servletin adı, Valuesi = Servletin kaydı
		Map<String, ? extends ServletRegistration> servletRegistrations = request.getServletContext()
				.getServletRegistrations();

		// WEB-appde tüm sunucu uygulamaların toplanmasını
		Collection<? extends ServletRegistration> values = servletRegistrations.values();
		for (ServletRegistration servletRegistration : values) {
			Collection<String> mappings = servletRegistration.getMappings();
			if (mappings.contains(urlPattern)) {
				return true;
			}
		}
		return false;
	}

}