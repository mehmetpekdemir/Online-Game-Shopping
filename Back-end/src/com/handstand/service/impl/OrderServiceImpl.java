package com.handstand.service.impl;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

import com.handstand.dao.OrderDao;
import com.handstand.dao.impl.OrderDaoImpl;
import com.handstand.entity.Order;
import com.handstand.entity.OrderItem;
import com.handstand.service.OrderService;
import com.handstand.service.impl.OrderServiceImpl;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */

/*
 * Dao katmanina yonlenme yapilan kisim.
 */
public class OrderServiceImpl implements OrderService {

	@Override
	public Order findOrder(String emailAddress) throws ClassNotFoundException, SQLException, ParseException {
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		return orderDaoImpl.findOrder(emailAddress);
	}

	@Override
	public void insertOrder(String emailAddress, Order order) throws ClassNotFoundException, SQLException {
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		orderDaoImpl.insertOrder(emailAddress, order);
	}

	@Override
	public Collection<OrderItem> controlStockOrderItems(String emailAddress) throws ClassNotFoundException, SQLException {
		OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
		return orderDaoImpl.controlStockOrderItems(emailAddress);
	}

	@Override
	public void updateOrder(String emailAddress, Date date)
			throws ClassNotFoundException, SQLException, ParseException {
		OrderDao orderDaoImpl = new OrderDaoImpl();
		orderDaoImpl.updateOrder(emailAddress, date);
		
	}
}
