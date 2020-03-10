package com.handstand.dao;

import java.sql.SQLException;

import com.handstand.entity.OrderItem;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public interface OrderItemDao {
	public void insertOrderItem(String code, String emailAddress, OrderItem orderItem)
			throws ClassNotFoundException, SQLException;

	public void deleteOrderItems(int id, String emailAddress) throws ClassNotFoundException, SQLException;

	public void deleteOrder(String emailAddress) throws ClassNotFoundException, SQLException;
}
