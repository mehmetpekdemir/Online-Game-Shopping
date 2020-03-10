package com.handstand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.handstand.connection.MySQLConnectionUtils;
import com.handstand.dao.OrderDao;
import com.handstand.dao.impl.OrderDaoImpl;
import com.handstand.entity.Order;
import com.handstand.entity.OrderItem;
import com.handstand.entity.Product;
import com.handstand.util.CheckUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public class OrderDaoImpl implements OrderDao {

	/*
	 * Ihtiyac duyulan sql ifadelerinin tanimlandigi kisim
	 */
	private static final String FIND_ORDER = "SELECT orders.DATE ,orders.COMPLETEFLAG FROM ((users INNER JOIN customers ON users.ID = customers.USERID)"
			+ " INNER JOIN orders ON orders.CUSTOMERID = customers.USERID) where users.EMAILADDRESS=? AND orders.COMPLETEFLAG =?";
	private static final String INSERT_ORDER = "INSERT INTO orders (CUSTOMERID ,DATE, COMPLETEFLAG)  values((select customers.USERID "
			+ "from customers INNER JOIN users ON customers.USERID = users.ID where users.EMAILADDRESS =?),? , ?)";
	private static final String LIST_ORDER = "select orderitems.ID ,orderitems.PRODUCTID , SUM(orderitems.QUANTITY) , "
			+ "SUM(orderitems.TOTALPRICE) , products.PRODUCT_NAME , products.IMAGE ,categories.CATEGORY_NAME from ((("
			+ "((users INNER JOIN customers ON users.ID = customers.USERID AND users.EMAILADDRESS=?)"
			+ " INNER JOIN orders ON customers.USERID=orders.CUSTOMERID AND orders.COMPLETEFLAG =?) INNER JOIN "
			+ "orderitems ON orderitems.ORDERID = orders.ID) INNER JOIN products ON products.ID = orderitems.PRODUCTID) "
			+ "INNER JOIN categories ON categories.ID = products.CATEGORYID) group by PRODUCTID";
	private static final String CONTROL_STOCK = "select orderitems.ID ,orderitems.PRODUCTID , SUM(orderitems.QUANTITY) , "
			+ "SUM(orderitems.TOTALPRICE) , products.PRODUCT_NAME , products.IMAGE ,categories.CATEGORY_NAME from ((("
			+ "((users INNER JOIN customers ON users.ID = customers.USERID AND users.EMAILADDRESS=?)"
			+ " INNER JOIN orders ON customers.USERID=orders.CUSTOMERID AND orders.COMPLETEFLAG =?) INNER JOIN "
			+ "orderitems ON orderitems.ORDERID = orders.ID) INNER JOIN products ON products.ID = orderitems.PRODUCTID) "
			+ "INNER JOIN categories ON categories.ID = products.CATEGORYID) group by PRODUCTID ,STOCK_AMOUNT HAVING "
			+ "products.STOCK_AMOUNT < SUM(orderitems.QUANTITY)";
	private static final String UPDATE_ORDER = "update orders set DATE =? , COMPLETEFLAG=? where CUSTOMERID = "
			+ "(select customers.USERID from users INNER JOIN customers ON users.ID=customers.USERID AND users.EMAILADDRESS = ?) AND COMPLETEFLAG = false";
	private static final String FIND_STOCK_AMOUNTS_FOR_SUBTRACTION = "select products.ID,products.STOCK_AMOUNT,orderitems.QUANTITY from "
			+ "((((users INNER JOIN customers ON customers.USERID = users.ID AND users.EMAILADDRESS =?) INNER JOIN"
			+ " orders ON orders.CUSTOMERID = customers.USERID AND orders.COMPLETEFLAG = ?) INNER JOIN orderitems "
			+ "ON orderitems.ORDERID = orders.ID) INNER JOIN products ON products.ID = orderitems.PRODUCTID)";
	private static final String UPDATE_STOCK = "update products set products.STOCK_AMOUNT =? where products.ID=?;";

	/*
	 * Customerin completeFlag degeri 0 olan yani tamamlanmayan sepetini icerdigi
	 * urunlerle birlikte bulmak ve return etmek icin yazildi
	 */
	@Override
	public Order findOrder(String emailAddress) throws ClassNotFoundException, SQLException, ParseException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(FIND_ORDER);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setBoolean(2, false);
		Order order = null;
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String dateString = resultSet.getString("DATE");
			Date date = CheckUtils.stringToDate(dateString);
			boolean completeFlag = resultSet.getBoolean("COMPLETEFLAG");
			order = new Order(date, completeFlag);
		}

		PreparedStatement preparedStatement2 = (PreparedStatement) connection.prepareStatement(LIST_ORDER);
		preparedStatement2.setString(1, emailAddress);
		preparedStatement2.setBoolean(2, false);
		ResultSet resultSet2 = preparedStatement2.executeQuery();
		Collection<OrderItem> orderItems = new ArrayList<OrderItem>();
		while (resultSet2.next()) {
			int id = resultSet2.getInt("ID");
			int productId = resultSet2.getInt("PRODUCTID");
			int quantity = resultSet2.getInt("SUM(orderitems.QUANTITY)");
			double totalPrice = resultSet2.getDouble("SUM(orderitems.TOTALPRICE)");
			String productName = resultSet2.getString("PRODUCT_NAME");
			String image = resultSet2.getString("IMAGE");
			String categoryName = resultSet2.getString("CATEGORY_NAME");
			Product product = new Product(productId, productName, image, categoryName, "sonradan eklenecek NULL");
			OrderItem orderItem = new OrderItem(id, quantity, totalPrice, product);
			orderItems.add(orderItem);
		}

		if (order != null) {
			order.setOrderItems(orderItems);
			return order;
		}
		return null;
	}

	/*
	 * Customer adina urun eklemek icin bir sepet olusturmak amaciyla yazildi
	 */
	@Override
	public void insertOrder(String emailAddress, Order order) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_ORDER);
		String date = CheckUtils.dateToString(order.getDate());
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setString(2, date);
		preparedStatement.setBoolean(3, order.getCompleteFlag());
		preparedStatement.executeUpdate();
	}

	/*
	 * sepette ekli olan urunlerin stokta var olup olmama durumlarini kontrol etmek
	 * amaciyla yazildi
	 */
	@Override
	public Collection<OrderItem> controlStockOrderItems(String emailAddress)
			throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		Collection<OrderItem> orderItems = new ArrayList<OrderItem>();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(CONTROL_STOCK);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setBoolean(2, false);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			int productId = resultSet.getInt("PRODUCTID");
			int quantity = resultSet.getInt("SUM(orderitems.QUANTITY)");
			double totalPrice = resultSet.getDouble("SUM(orderitems.TOTALPRICE)");
			String productName = resultSet.getString("PRODUCT_NAME");
			String image = resultSet.getString("IMAGE");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			Product product = new Product(productId, productName, image, categoryName, "sonradan eklenecek NULL");
			OrderItem orderItem = new OrderItem(id, quantity, totalPrice, product);
			orderItems.add(orderItem);
		}
		return orderItems;
	}

	/*
	 * Siparisi tamamlanan sepet iceriginin fatura tablosuna kayıt edilmesinin
	 * ardindan tarihini ve completeFlag degerini update etmek amaciyla yazildi
	 */
	@Override
	public void updateOrder(String emailAddress, Date date)
			throws ClassNotFoundException, SQLException, ParseException {
		Connection connection = MySQLConnectionUtils.getConnection();

		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(FIND_STOCK_AMOUNTS_FOR_SUBTRACTION);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setBoolean(2, false);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int productId = resultSet.getInt("ID");
			double stockAmount = resultSet.getDouble("STOCK_AMOUNT");
			double quantity = resultSet.getDouble("QUANTITY");
			double resultStockAmount = stockAmount - quantity;
			PreparedStatement preparedStatement2 = (PreparedStatement) connection.prepareStatement(UPDATE_STOCK);
			preparedStatement2.setDouble(1, resultStockAmount);
			preparedStatement2.setInt(2, productId);
			preparedStatement2.executeUpdate();
		}
		PreparedStatement preparedStatement3 = (PreparedStatement) connection.prepareStatement(UPDATE_ORDER);
		String dateString = CheckUtils.dateToString(date);
		preparedStatement3.setString(1, dateString);
		preparedStatement3.setBoolean(2, true);
		preparedStatement3.setString(3, emailAddress);
		preparedStatement3.executeUpdate();
	}
}
