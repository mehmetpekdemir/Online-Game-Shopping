package com.handstand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.handstand.connection.MySQLConnection;
import com.handstand.dao.PlatformDaoImp;
import com.handstand.entity.Platform;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class PlatformDaoImpl implements PlatformDaoImp {

	private static final String LIST_PLATFORM = "SELECT platforms.ID,platforms.PLATFORM_NAME FROM platforms ORDER BY platforms.ID";
	private static final String FIND_PLATFORM = "SELECT platforms.ID,platforms.PLATFORM_NAME FROM platforms WHERE platforms.ID =?";
	private static final String ADD_PLATFORM = "INSERT INTO platforms(platforms.PLATFORM_NAME) VALUES (?)";
	private static final String UPDATE_PLATFORM = "UPDATE platforms SET platforms.PLATFORM_NAME =? WHERE platforms.ID =?";

	// Platformları listelemek için kullanıyoruz Input : - Output: Platform listesi
	@Override
	public List<Platform> listPlatform() throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		List<Platform> platforms = new ArrayList<Platform>();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(LIST_PLATFORM);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			String platformName = resultSet.getString("PLATFORM_NAME");
			Platform platform = new Platform(id, platformName);
			platforms.add(platform);
		}
		return platforms;
	}

	// Platformları id'e göre aramak için kullanıyoruz.Input : int Output : platform
	@Override
	public Platform findPlatform(int id) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(FIND_PLATFORM);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String platformName = resultSet.getString("PLATFORM_NAME");
			Platform platform = new Platform(id, platformName);
			return platform;
		}
		return null;
	}

	// Platform eklemek için kullanıyoruz. Input : Platform nesnesi Output : -
	@Override
	public void addPlatform(Platform platform) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(ADD_PLATFORM);
		preparedStatement.setString(1, platform.getPlatformName());
		preparedStatement.executeUpdate();
	}

	// Platformları güncellemek için kullanıyoruz. Input : Platform nesnesi Output -
	@Override
	public void updatePlatform(Platform platform) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(UPDATE_PLATFORM);
		preparedStatement.setString(1, platform.getPlatformName());
		preparedStatement.setInt(2, platform.getId());
		preparedStatement.executeUpdate();
	}

}
