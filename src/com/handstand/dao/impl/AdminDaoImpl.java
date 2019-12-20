package com.handstand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.handstand.connection.MySQLConnection;
import com.handstand.dao.AdminDao;
import com.handstand.entity.Admin;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class AdminDaoImpl implements AdminDao {

	//Parametreler kullandığımızdan ötürü sql injectiona kapalı kalmıs olduk.
	private static final String FIND_ADMIN_WITH_USERNAME_AND_PASSWORD = "SELECT admin.ID, admin.ADMIN_USER_NAME, admin.ADMIN_PASSWORD, admin.GENDER FROM admin"
			+ " WHERE admin.ADMIN_USER_NAME =? AND admin.ADMIN_PASSWORD =?";

	private static final String FIND_ADMIN_WITH_USERNAME = "SELECT admin.ID, admin.ADMIN_USER_NAME, admin.ADMIN_PASSWORD, admin.GENDER FROM admin"
			+ " WHERE admin.ADMIN_USER_NAME =?";

	@Override
	public Admin findAdmin(String adminUserName, String adminPassword) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(FIND_ADMIN_WITH_USERNAME_AND_PASSWORD);
		preparedStatement.setString(1, adminUserName);
		preparedStatement.setString(2, adminPassword);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String gender = resultSet.getString("GENDER");
			Admin admin = new Admin(gender, adminUserName, adminPassword);
			return admin;
		}
		return null;
	}

	@Override
	public Admin findAdmin(String adminUserName) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(FIND_ADMIN_WITH_USERNAME);
		preparedStatement.setString(1, adminUserName);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String gender = resultSet.getString("GENDER");
			String adminPassword = resultSet.getString("ADMIN_PASSWORD");
			Admin admin = new Admin(gender, adminUserName, adminPassword);
			return admin;
		}
		return null;
	}
}
