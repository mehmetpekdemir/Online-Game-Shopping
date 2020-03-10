package com.handstand.service;

import java.sql.SQLException;
import java.util.Collection;

import com.handstand.entity.Address;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public interface AddressService {
	public void insertAddress(Address address, String emailAddress) throws ClassNotFoundException, SQLException;

	public void deleteAddress(int id) throws ClassNotFoundException, SQLException;

	public Collection<Address> listAddress(String emailAddress) throws ClassNotFoundException, SQLException;

	public Address findAddress(int id) throws ClassNotFoundException, SQLException;
	
	public void updateAddress(Address address) throws ClassNotFoundException, SQLException;
}
