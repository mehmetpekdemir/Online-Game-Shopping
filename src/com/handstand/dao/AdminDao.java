package com.handstand.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.handstand.connection.MySQLConnection;
import com.handstand.dao.impl.AdminDaoImpl;
import com.handstand.entity.Admin;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */

public class AdminDao implements AdminDaoImpl {

	// Parametreler kullandığımızdan ötürü sql injectiona kapalı tutmus olduk.
	private static final String FIND_ADMIN_WITH_USERNAME_AND_PASSWORD = "SELECT users.ID, users.FIRSTNAME,"
			+ " users.LASTNAME, users.EMAILADDRESS, users.PASSWORD FROM users"
			+ " INNER JOIN admins ON users.ID = admins.USERID AND users.EMAILADDRESS =? AND users.PASSWORD =?";

	private static final String FIND_ADMIN_WITH_USERNAME = "SELECT users.ID, users.FIRSTNAME,"
			+ " users.LASTNAME, users.EMAILADDRESS, users.PASSWORD FROM users" + " WHERE users.EMAILADDRESS =?";

	@Override
	public Admin findAdmin(String emailAddress, String password) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(FIND_ADMIN_WITH_USERNAME_AND_PASSWORD);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String firstName = resultSet.getString("FIRSTNAME");
			String lastName = resultSet.getString("LASTNAME");
			Admin admin = new Admin(firstName, lastName, emailAddress, password);
			return admin;
		}
		return null;
	}

	@Override
	public Admin findAdmin(String emailAddress) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(FIND_ADMIN_WITH_USERNAME);
		preparedStatement.setString(1, emailAddress);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String firstName = resultSet.getString("FIRSTNAME");
			String lastName = resultSet.getString("LASTNAME");
			String password = resultSet.getString("PASSWORD");
			Admin admin = new Admin(firstName, lastName, emailAddress, password);
			return admin;
		}
		return null;
	}
}
