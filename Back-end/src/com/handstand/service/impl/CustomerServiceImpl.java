package com.handstand.service.impl;

import java.sql.SQLException;

import com.handstand.dao.impl.CustomerDaoImpl;
import com.handstand.entity.Customer;
import com.handstand.service.CustomerService;
import com.handstand.service.impl.CustomerServiceImpl;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */

/*
 * Dao katmanina yonlenme yapilan kisim.
 */
public class CustomerServiceImpl implements CustomerService {

	@Override
	public boolean emailController(String emailAddress) throws ClassNotFoundException, SQLException {
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		return customerDaoImpl.emailController(emailAddress);
	}

	@Override
	public Customer findCustomer(String emailAddress, String password) throws ClassNotFoundException, SQLException {
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		return customerDaoImpl.findCustomer(emailAddress, password);
	}

	@Override
	public Customer findCustomer(String emailAddress) throws ClassNotFoundException, SQLException {
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		return customerDaoImpl.findCustomer(emailAddress);
	}

	@Override
	public void insertCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		customerDaoImpl.insertCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		CustomerDaoImpl customerDaoImpl = new CustomerDaoImpl();
		customerDaoImpl.updateCustomer(customer);
	}
}
