package com.handstand.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import com.handstand.entity.Order;
import com.handstand.entity.OrderItem;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public interface OrderDao {
	public Order findOrder(String emailAddress) throws ClassNotFoundException, SQLException, ParseException;

	public void insertOrder(String emailAddress, Order order) throws ClassNotFoundException, SQLException;

	public void updateOrder(String emailAddress, Date date)
			throws ClassNotFoundException, SQLException, ParseException;

	public Collection<OrderItem> controlStockOrderItems(String emailAddress) throws ClassNotFoundException, SQLException;
}
