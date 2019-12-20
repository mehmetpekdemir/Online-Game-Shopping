package com.handstand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.handstand.connection.MySQLConnection;
import com.handstand.dao.CategoryDaoImp;
import com.handstand.entity.Category;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class CategoryDaoImpl implements CategoryDaoImp {

	private static final String LIST_CATEGORY = "SELECT categories.ID,categories.PLATFORMID,"
			+ "categories.CATEGORY_NAME,platforms.PLATFORM_NAME FROM categories "
			+ "INNER JOIN platforms ON categories.PLATFORMID = platforms.ID";

	private static final String LIST_CATEGORY_WITH_PLATFORM_NAME = "SELECT categories.PLATFORMID,"
			+ "platforms.PLATFORM_NAME,categories.CATEGORY_NAME,platforms.ID FROM platforms "
			+ "INNER JOIN categories ON categories.PLATFORMID = platforms.ID";

	private static final String FIND_CATEGORY = "SELECT categories.CATEGORY_NAME FROM categories WHERE categories.ID = ?";

	private static final String FIND_CATEGORY_WITH_CATEGORY_NAME = "SELECT categories.ID FROM categories WHERE categories.CATEGORY_NAME = ?";

	private static final String ADD_CATEGORY = "INSERT INTO categories(PLATFORMID,CATEGORY_NAME) "
			+ "SELECT platforms.ID , ? FROM platforms WHERE platforms.PLATFORM_NAME =? ";

	private static final String UPDATE_CATEGORY = "UPDATE categories INNER JOIN platforms "
			+ "ON categories.PLATFORMID = platforms.ID " + "set categories.CATEGORY_NAME=? WHERE categories.ID =?";

	// Kategorileri listelemek için kullanıyoruz Input : - Output:Kategori listesi
	@Override
	public List<Category> listCategory() throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		List<Category> categories = new ArrayList<Category>();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(LIST_CATEGORY);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			int platformId = resultSet.getInt("PLATFORMID");
			String platformName = resultSet.getString("PLATFORM_NAME");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			Category category = new Category(id, platformId, platformName, categoryName);
			categories.add(category);
		}
		return categories;
	}

	// Kategorileri ürün ismine göre listelemek için kullanıyoruz Input : -
	// Output:Kategori listesi
	@Override
	public List<Category> listCategoryWithPlatformName() throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		List<Category> categories = new ArrayList<Category>();
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(LIST_CATEGORY_WITH_PLATFORM_NAME);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			int platformId = resultSet.getInt("PLATFORMID");
			String platformName = resultSet.getString("PLATFORM_NAME");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			Category category = new Category(id, platformId, platformName, categoryName);
			categories.add(category);
		}
		return categories;
	}

	// Kategorileri id göre aramak için kullanıyoruz.
	// Input : int , String Output : Category nesnesi
	@Override
	public Category findCategory(int id, String platformName) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(FIND_CATEGORY);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String categoryName = resultSet.getString("CATEGORY_NAME");
			Category category = new Category(id, platformName, categoryName);
			return category;
		}
		return null;
	}

	// Kullanıcıdan aldığımız kategori ismini veritabanımızda hangi id'ye karsılık
	// düstügünü aramak icin kullanıyorum.
	// Input :String Output : int
	@Override
	public int findCategoryWithCategoryName(String categoryName) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(FIND_CATEGORY_WITH_CATEGORY_NAME);
		preparedStatement.setString(1, categoryName);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			return id;
		}
		return -1;
	}

	// Kategori eklemek için kullanıyoruz. Input : Kategori nesnesi Output : -
	@Override
	public void addCategory(Category category) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(ADD_CATEGORY);
		preparedStatement.setString(1, category.getCategoryName());
		preparedStatement.setString(2, category.getPlatformName());
		preparedStatement.executeUpdate();
	}

	// Kategori güncellemek için kullanıyoruz. Input : Kategori nesnesi Output : -
	@Override
	public void updateCategory(Category category) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(UPDATE_CATEGORY);
		preparedStatement.setString(1, category.getCategoryName());
		preparedStatement.setInt(2, category.getId());
		preparedStatement.executeUpdate();
	}

}
