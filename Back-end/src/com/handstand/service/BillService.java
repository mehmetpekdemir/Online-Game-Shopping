package com.handstand.service;

import java.sql.SQLException;
import com.handstand.entity.Bill;

import java.util.Collection;
import java.util.Date;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public interface BillService {
	public void insertBill(String emailAddress,int addressId,Date date,int paymentOption) throws ClassNotFoundException, SQLException;
	
	public Collection<Bill> listBills(String emailAddress) throws ClassNotFoundException, SQLException;
}
