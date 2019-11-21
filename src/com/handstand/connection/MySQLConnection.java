package com.handstand.connection;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class MySQLConnection {

	// Hibernate kullanılırsak config dosyalarına geçecegiz.
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException {
		String hostName = "localhost";
		String dbName = "www.handstand.com";
		String userName = "root";
		String password = "";
		return getMySQLConnection(hostName, dbName, userName, password);

	}

	// dbUrl = "jdbc:mysql://localhost:3306/demo";
	public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password)
			throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.jdbc.Driver");

		String connectionUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName;
		Connection connection = (Connection) DriverManager.getConnection(connectionUrl, userName, password);
		return connection;

	}

}
