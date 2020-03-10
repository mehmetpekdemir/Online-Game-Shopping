package com.handstand.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import com.handstand.entity.Bill;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public interface BillDao {
	public void insertBill(String emailAddress, int addressId, Date date, int paymentOption) throws ClassNotFoundException, SQLException;
	
	public Collection<Bill> listBills(String emailAddress) throws ClassNotFoundException, SQLException;
}
