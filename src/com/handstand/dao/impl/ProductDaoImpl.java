package com.handstand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.handstand.connection.MySQLConnection;
import com.handstand.dao.ProductDao;
import com.handstand.entity.Product;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */

public class ProductDaoImpl implements ProductDao {

	private static final String LIST_PRODUCT = "SELECT platforms.PLATFORM_NAME,categories.CATEGORY_NAME,products.PRODUCT_NAME,"
			+ "products.CODE,products.PRICE,products.STOCK_AMOUNT,products.IMAGE,products.DESCRIPTION "
			+ "FROM (( platforms " + "INNER JOIN categories ON platforms.ID = categories.PLATFORMID) "
			+ "INNER JOIN products  ON categories.ID = products.CATEGORYID)";

	private static final String FIND_PRODUCT = "SELECT platforms.PLATFORM_NAME,categories.CATEGORY_NAME,products.PRODUCT_NAME,"
			+ "products.CODE,products.PRICE,products.STOCK_AMOUNT,products.IMAGE,products.DESCRIPTION "
			+ "FROM (( platforms " + "INNER JOIN categories ON platforms.ID = categories.PLATFORMID) "
			+ "INNER JOIN products  ON categories.ID = products.CATEGORYID AND products.CODE =?)";

	private static final String UPDATE_PRODUCT = "UPDATE products INNER JOIN categories "
			+ "ON products.CATEGORYID = categories.ID "
			+ "INNER JOIN platforms ON categories.PLATFORMID = platforms.ID "
			+ "SET products.PRODUCT_NAME=?,products.PRICE=?,products.STOCK_AMOUNT=?,products.IMAGE=?,"
			+ "products.DESCRIPTION=? WHERE products.CODE =?";

	private static final String ADD_PRODUCT = "INSERT INTO products(products.CATEGORYID,"
			+ "products.PRODUCT_NAME,products.CODE,products.PRICE,products.STOCK_AMOUNT,"
			+ "products.IMAGE,products.DESCRIPTION) VALUES (?,?,?,?,?,?,?)";

	private static final String DELETE_PRODUCT = "DELETE FROM products WHERE products.CODE =?";

	private static final String SEARCH_PRODUCT = "SELECT platforms.PLATFORM_NAME,categories.CATEGORY_NAME,products.PRODUCT_NAME,"
			+ "products.CODE,products.PRICE,products.STOCK_AMOUNT,products.IMAGE,products.DESCRIPTION "
			+ "FROM (( platforms " + "INNER JOIN categories ON platforms.ID = categories.PLATFORMID) "
			+ "INNER JOIN products  ON categories.ID = products.CATEGORYID "
			+ "AND products.PRODUCT_NAME LIKE CONCAT('%',?,'%') )";

	private static final String PRICE_LOWEST_FIRST = "SELECT platforms.PLATFORM_NAME,categories.CATEGORY_NAME,products.PRODUCT_NAME,"
			+ "products.CODE,products.PRICE,products.STOCK_AMOUNT,products.IMAGE,products.DESCRIPTION "
			+ "FROM (( platforms " + "INNER JOIN categories ON platforms.ID = categories.PLATFORMID) "
			+ "INNER JOIN products  ON categories.ID = products.CATEGORYID) " + "ORDER BY products.PRICE ASC";

	private static final String PRICE_HIGHEST_FIRST = "SELECT platforms.PLATFORM_NAME,categories.CATEGORY_NAME,products.PRODUCT_NAME,"
			+ "products.CODE,products.PRICE,products.STOCK_AMOUNT,products.IMAGE,products.DESCRIPTION "
			+ "FROM (( platforms " + "INNER JOIN categories ON platforms.ID = categories.PLATFORMID) "
			+ "INNER JOIN products  ON categories.ID = products.CATEGORYID ) " + "ORDER BY products.PRICE DESC";

	// Ürünleri listelemek için kullanıyoruz Input : - Output:Product listesi
	@Override
	public List<Product> listProduct() throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		List<Product> products = new ArrayList<Product>();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(LIST_PRODUCT);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String platformName = resultSet.getString("PLATFORM_NAME");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			String productName = resultSet.getString("PRODUCT_NAME");
			String code = resultSet.getString("CODE");
			double price = resultSet.getDouble("PRICE");
			int stockAmount = resultSet.getInt("STOCK_AMOUNT");
			String image = resultSet.getString("IMAGE");
			String description = resultSet.getString("DESCRIPTION");
			Product product = new Product(platformName, categoryName, productName, code, price, stockAmount, image,
					description);
			products.add(product);
		}
		return products;
	}

	// Ürünleri code göre aramak için kullanıyoruz.Input : String Output : product
	@Override
	public Product findProduct(String code) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(FIND_PRODUCT);
		preparedStatement.setString(1, code);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String platformName = resultSet.getString("PLATFORM_NAME");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			String productName = resultSet.getString("PRODUCT_NAME");
			double price = resultSet.getDouble("PRICE");
			int stockAmount = resultSet.getInt("STOCK_AMOUNT");
			String image = resultSet.getString("IMAGE");
			String description = resultSet.getString("DESCRIPTION");
			Product product = new Product(platformName, categoryName, productName, code, price, stockAmount, image,
					description);
			return product;
		}
		return null;
	}

	// Ürünleri güncellemek için kullanıyoruz. Input : product nesnesi Output : -
	@Override
	public void updateProduct(Product product) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(UPDATE_PRODUCT);
		preparedStatement.setString(1, product.getProductName());
		preparedStatement.setDouble(2, product.getPrice());
		preparedStatement.setInt(3, product.getStockAmount());
		preparedStatement.setString(4, product.getImage());
		preparedStatement.setString(5, product.getDescription());
		preparedStatement.setString(6, product.getCode());
		preparedStatement.executeUpdate();
	}

	// Ürün eklemek için kullanıyoruz. Input : product nesnesi Output : -
	@Override
	public void addProduct(Product product) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(ADD_PRODUCT);
		preparedStatement.setInt(1, product.getCategoryId());
		preparedStatement.setString(2, product.getProductName());
		preparedStatement.setString(3, product.getCode());
		preparedStatement.setDouble(4, product.getPrice());
		preparedStatement.setInt(5, product.getStockAmount());
		preparedStatement.setString(6, product.getImage());
		preparedStatement.setString(7, product.getDescription());
		preparedStatement.executeUpdate();
	}

	// Ürün güncellemek için kullanıyoruz. Input : String Output : -
	@Override
	public void deleteProduct(String code) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(DELETE_PRODUCT);
		preparedStatement.setString(1, code);
		preparedStatement.executeUpdate();
	}

	// Ürün ismine göre arama yapmak için kullanıyoruz. Input : String nesnesi
	// Output : Product listesi
	public List<Product> searchProduct(String productNameSearch) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		List<Product> products = new ArrayList<Product>();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(SEARCH_PRODUCT);
		preparedStatement.setString(1, productNameSearch);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String platformName = resultSet.getString("PLATFORM_NAME");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			String productName = resultSet.getString("PRODUCT_NAME");
			String code = resultSet.getString("CODE");
			double price = resultSet.getDouble("PRICE");
			int stockAmount = resultSet.getInt("STOCK_AMOUNT");
			String image = resultSet.getString("IMAGE");
			String description = resultSet.getString("DESCRIPTION");
			Product product = new Product(platformName, categoryName, productName, code, price, stockAmount, image,
					description);
			products.add(product);
		}
		return products;
	}

	// Ürünleri en yüksek fiyattan en düşük fiyatta sıralıyoruz. Input : - Output :
	// Product listesi
	@Override
	public List<Product> priceHighestProduct() throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		List<Product> products = new ArrayList<Product>();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(PRICE_HIGHEST_FIRST);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String platformName = resultSet.getString("PLATFORM_NAME");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			String productName = resultSet.getString("PRODUCT_NAME");
			String code = resultSet.getString("CODE");
			double price = resultSet.getDouble("PRICE");
			int stockAmount = resultSet.getInt("STOCK_AMOUNT");
			String image = resultSet.getString("IMAGE");
			String description = resultSet.getString("DESCRIPTION");
			Product product = new Product(platformName, categoryName, productName, code, price, stockAmount, image,
					description);
			products.add(product);
		}
		return products;
	}

	// Ürünleri en düşük fiyattan en yüksek fiyata sıralıyoruz. Input : - Output :
	// Product listesi
	@Override
	public List<Product> priceLowestProduct() throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		List<Product> products = new ArrayList<Product>();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(PRICE_LOWEST_FIRST);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String platformName = resultSet.getString("PLATFORM_NAME");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			String productName = resultSet.getString("PRODUCT_NAME");
			String code = resultSet.getString("CODE");
			double price = resultSet.getDouble("PRICE");
			int stockAmount = resultSet.getInt("STOCK_AMOUNT");
			String image = resultSet.getString("IMAGE");
			String description = resultSet.getString("DESCRIPTION");
			Product product = new Product(platformName, categoryName, productName, code, price, stockAmount, image,
					description);
			products.add(product);
		}
		return products;
	}

}
