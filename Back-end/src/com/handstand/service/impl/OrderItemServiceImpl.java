package com.handstand.service.impl;

import java.sql.SQLException;

import com.handstand.dao.impl.OrderItemDaoImpl;
import com.handstand.entity.OrderItem;
import com.handstand.service.OrderItemService;
import com.handstand.service.impl.OrderItemServiceImpl;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */

/*
 * Dao katmanina yonlenme yapilan kisim.
 */
public class OrderItemServiceImpl implements OrderItemService {

	@Override
	public void insertOrderItem(String code, String emailAddress, OrderItem orderItem)
			throws ClassNotFoundException, SQLException {
		OrderItemDaoImpl orderItemDaoImpl = new OrderItemDaoImpl();
		orderItemDaoImpl.insertOrderItem(code, emailAddress, orderItem);
	}

	@Override
	public void deleteOrderItems(int id, String emailAddress) throws ClassNotFoundException, SQLException {
		OrderItemDaoImpl orderItemDaoImpl = new OrderItemDaoImpl();
		orderItemDaoImpl.deleteOrderItems(id, emailAddress);
	}

	@Override
	public void deleteOrder(String emailAddress) throws ClassNotFoundException, SQLException {
		OrderItemDaoImpl orderItemDaoImpl = new OrderItemDaoImpl();
		orderItemDaoImpl.deleteOrder(emailAddress);
	}
}
