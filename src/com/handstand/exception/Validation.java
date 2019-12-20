package com.handstand.exception;

import javax.servlet.ServletException;

import com.handstand.connection.MySQLConnectionUtils;
import com.mysql.jdbc.Connection;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class Validation {

	public static void validationServletException(Exception exception, Connection connection) throws ServletException {
		exception.printStackTrace();
		MySQLConnectionUtils.rollbackConnection(connection);
		throw new ServletException();
	}
}
