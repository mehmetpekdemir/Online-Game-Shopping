package com.handstand.service.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;

import com.handstand.dao.impl.BillDaoImpl;
import com.handstand.entity.Bill;
import com.handstand.service.BillService;
import com.handstand.service.impl.BillServiceImpl;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */

/*
 * Dao katmanina yonlenme yapilan kisim.
 */
public class BillServiceImpl implements BillService {

	@Override
	public void insertBill(String emailAddress,int addressId,Date date,int paymentOption) throws ClassNotFoundException, SQLException {
		BillDaoImpl billDaoImpl = new BillDaoImpl();
		billDaoImpl.insertBill(emailAddress,addressId,date,paymentOption);
	}

	@Override
	public Collection<Bill> listBills(String emailAddress) throws ClassNotFoundException, SQLException {
		BillDaoImpl billDaoImpl = new BillDaoImpl();
		return billDaoImpl.listBills(emailAddress);
	}

}
