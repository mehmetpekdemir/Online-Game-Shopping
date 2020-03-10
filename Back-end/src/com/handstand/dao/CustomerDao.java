package com.handstand.dao;

import java.sql.SQLException;

import com.handstand.entity.Customer;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public interface CustomerDao {
	public boolean emailController(String emailAddress) throws ClassNotFoundException, SQLException;

	public void insertCustomer(Customer customer) throws ClassNotFoundException, SQLException;

	public Customer findCustomer(String emailAddress, String password) throws ClassNotFoundException, SQLException;

	public Customer findCustomer(String emailAddress) throws ClassNotFoundException, SQLException;

	public void updateCustomer(Customer customer) throws ClassNotFoundException, SQLException;
}
