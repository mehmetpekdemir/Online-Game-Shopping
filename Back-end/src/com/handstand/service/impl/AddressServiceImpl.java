package com.handstand.service.impl;

import java.sql.SQLException;
import java.util.Collection;

import com.handstand.dao.impl.AddressDaoImpl;
import com.handstand.entity.Address;
import com.handstand.service.AddressService;
import com.handstand.service.impl.AddressServiceImpl;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */

/*
 * Dao katmanina yonlenme yapilan kisim.
 */
public class AddressServiceImpl implements AddressService {
	@Override
	public void insertAddress(Address address, String emailAddress) throws ClassNotFoundException, SQLException {
		AddressDaoImpl addressDaoImpl = new AddressDaoImpl();
		addressDaoImpl.insertAddress(address, emailAddress);
	}

	@Override
	public Collection<Address> listAddress(String emailAddress) throws ClassNotFoundException, SQLException {
		AddressDaoImpl addressDaoImpl = new AddressDaoImpl();
		return addressDaoImpl.listAddress(emailAddress);
	}

	@Override
	public void deleteAddress(int id) throws ClassNotFoundException, SQLException {
		AddressDaoImpl addressDaoImpl = new AddressDaoImpl();
		addressDaoImpl.deleteAddress(id);
	}

	@Override
	public Address findAddress(int id) throws ClassNotFoundException, SQLException {
		AddressDaoImpl addressDaoImpl = new AddressDaoImpl();
		return addressDaoImpl.findAddress(id);
	}

	@Override
	public void updateAddress(Address address) throws ClassNotFoundException, SQLException {
		AddressDaoImpl addressDaoImpl = new AddressDaoImpl();
		addressDaoImpl.updateAddress(address);
	}

}
