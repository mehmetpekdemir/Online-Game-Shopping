package com.handstand.connection;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class MySQLConnectionUtils {

	// Baglantiyi cagiriyoruz.
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return MySQLConnection.getMySQLConnection();
	}

	// Baglantiyi kapatmak için kullanilir.
	public static void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	// Geri alma transaction icin gerekli 
	public static void rollbackConnection(Connection connection) {
		try {
			connection.rollback();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
