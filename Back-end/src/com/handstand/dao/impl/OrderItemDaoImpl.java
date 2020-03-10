package com.handstand.dao.impl;

import java.sql.SQLException;

import com.handstand.connection.MySQLConnectionUtils;
import com.handstand.dao.OrderItemDao;
import com.handstand.dao.impl.OrderItemDaoImpl;
import com.handstand.entity.OrderItem;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public class OrderItemDaoImpl implements OrderItemDao {

	private static final String INSERT_ORDER_ITEM = "INSERT INTO orderitems (ORDERID,PRODUCTID,QUANTITY,"
			+ "TOTALPRICE) values((select orders.ID from ((users INNER JOIN customers ON "
			+ "users.ID = customers.USERID) INNER JOIN orders ON orders.CUSTOMERID = customers.USERID) "
			+ "where users.EMAILADDRESS=? and orders.COMPLETEFLAG =false),(select products.ID from products where products.CODE =?),?,?)";
	private static final String DELETE_ORDER_ITEMS = "delete from orderitems where PRODUCTID =? and ORDERID = (select orders.ID "
			+ "from ((users INNER JOIN customers ON users.ID = customers.USERID) INNER JOIN orders "
			+ "ON orders.CUSTOMERID = customers.USERID) where users.EMAILADDRESS=? AND orders.COMPLETEFLAG =?)";
	private static final String DELETE_ORDER = "delete from orderitems where orderitems.ORDERID = "
			+ "(select orders.ID from ((users INNER JOIN customers ON users.ID = customers.USERID) "
			+ "INNER JOIN orders ON orders.CUSTOMERID = customers.USERID) where users.EMAILADDRESS=? AND orders.COMPLETEFLAG =?)";
	/*
	 * Customer icin tanimlanan siparisi tamamlanmamis sepete urun eklemek amaciyla
	 * yazildi
	 */
	@Override
	public void insertOrderItem(String code, String emailAddress, OrderItem orderItem)
			throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_ORDER_ITEM);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setString(2, code);
		preparedStatement.setInt(3, orderItem.getQuantity());
		preparedStatement.setDouble(4, orderItem.getTotalPrice());
		preparedStatement.executeUpdate();
	}
	/*
	 * Sepetten her hangi bir urunun silinmesi icin yazildi
	 */
	@Override
	public void deleteOrderItems(int id, String emailAddress) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(DELETE_ORDER_ITEMS);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, emailAddress);
		preparedStatement.setBoolean(3, false);
		preparedStatement.executeUpdate();
	}
	/*
	 * sepeti bosaltmak amaciyla yazildi
	 */
	@Override
	public void deleteOrder(String emailAddress) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(DELETE_ORDER);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setBoolean(2, false);
		preparedStatement.executeUpdate();
	}
}
