package com.handstand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.handstand.connection.MySQLConnectionUtils;
import com.handstand.dao.BillDao;
import com.handstand.dao.impl.BillDaoImpl;
import com.handstand.entity.Bill;
import com.handstand.entity.BillItem;
import com.handstand.util.CheckUtils;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public class BillDaoImpl implements BillDao {

	/*
	 * Ihtiyac duyulan sql ifadelerinin tanimlandigi kisim
	 */
	private static final String CREATE_BILL = "select users.FIRSTNAME,users.LASTNAME,customers.PHONENUMBER,"
			+ "addresses.ADDRESS_TITLE,addresses.ADDRESS_DETAIL,addresses.CITY,addresses.DISTRICT,addresses.POSTCODE from "
			+ "((users INNER JOIN customers ON users.ID = customers.USERID AND users.EMAILADDRESS=?)"
			+ "INNER JOIN addresses ON addresses.CUSTOMERID = customers.USERID 	AND addresses.ID =?)";
	private static final String FIND_BILL_ITEM = "select orderitems.ORDERID,products.CODE,SUM(orderitems.QUANTITY) , SUM(orderitems.TOTALPRICE)"
			+ " , products.PRODUCT_NAME ,categories.CATEGORY_NAME from (((((users INNER JOIN customers ON "
			+ "users.ID = customers.USERID AND users.EMAILADDRESS=?) INNER JOIN orders ON customers.USERID=orders.CUSTOMERID "
			+ "AND orders.COMPLETEFLAG =?) INNER JOIN orderitems ON orderitems.ORDERID = orders.ID) "
			+ "INNER JOIN products ON products.ID = orderitems.PRODUCTID) INNER JOIN categories ON "
			+ "categories.ID = products.CATEGORYID) group by PRODUCTID";
	private static final String INSERT_BILL = "INSERT INTO bills (ORDERID,PAYMENTOPTION,FIRSTNAME,LASTNAME,EMAILADDRESS,PHONENUMBER,ADDRESS_TITLE,"
			+ "ADDRESS_DETAIL,CITY,DISTRICT,POSTCODE,DATE) values ( ?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String INSERT_BILL_ITEM = "INSERT INTO billitems (BILLID,QUANTITY,TOTALPRICE,PRODUCTNAME,CATEGORYNAME,"
			+ "PLATFORMNAME,CODE) values ( ?,?,?,?,?,?,?)";
	private static final String LIST_BILL_ITEM = "select bills.ORDERID,bills.DATE,bills.FIRSTNAME,bills.LASTNAME,bills.EMAILADDRESS,"
			+ "bills.PHONENUMBER,ADDRESS_TITLE,ADDRESS_DETAIL,CITY,DISTRICT,POSTCODE,CODE,QUANTITY,TOTALPRICE,PLATFORMNAME,"
			+ "CATEGORYNAME,PRODUCTNAME from ((((users INNER JOIN customers ON customers.USERID = users.ID AND users.EMAILADDRESS =?) "
			+ "INNER JOIN orders ON customers.USERID = orders.CUSTOMERID AND orders.COMPLETEFLAG=?) INNER JOIN bills ON "
			+ "bills.ORDERID = orders.ID) INNER JOIN billitems ON bills.ORDERID = billitems.BILLID ) order by bills.ORDERID desc";

	@Override
	public void insertBill(String emailAddress, int addressId, Date date, int paymentOption)
			throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(CREATE_BILL);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setInt(2, addressId);
		ResultSet resultSet = preparedStatement.executeQuery();
		Bill bill = null;
		while (resultSet.next()) {
			String firstName = resultSet.getString("FIRSTNAME");
			String lastName = resultSet.getString("LASTNAME");
			String phoneNumber = resultSet.getString("PHONENUMBER");
			String addressTitle = resultSet.getString("ADDRESS_TITLE");
			String addressDetail = resultSet.getString("ADDRESS_DETAIL");
			String city = resultSet.getString("CITY");
			String district = resultSet.getString("DISTRICT");
			String postCode = resultSet.getString("POSTCODE");
			bill = new Bill(firstName, lastName, emailAddress, phoneNumber, addressTitle, addressDetail, city, district,
					postCode, date);
		}
		PreparedStatement preparedStatement2 = (PreparedStatement) connection.prepareStatement(FIND_BILL_ITEM);
		preparedStatement2.setString(1, emailAddress);
		preparedStatement2.setBoolean(2, false);
		ResultSet resultSet2 = preparedStatement2.executeQuery();
		Collection<BillItem> billItems = new ArrayList<BillItem>();
		int orderId = 0;
		while (resultSet2.next()) {
			orderId = resultSet2.getInt("ORDERID");
			String code = resultSet2.getString("CODE");
			int quantity = resultSet2.getInt("SUM(orderitems.QUANTITY)");
			double totalPrice = resultSet2.getDouble("SUM(orderitems.TOTALPRICE)");
			String productName = resultSet2.getString("PRODUCT_NAME");
			String categoryName = resultSet2.getString("CATEGORY_NAME");
			BillItem billItem = new BillItem(quantity, totalPrice, code, productName, categoryName, "þuanlýk NULL");
			billItems.add(billItem);
		}

		PreparedStatement preparedStatement3 = (PreparedStatement) connection.prepareStatement(INSERT_BILL);
		preparedStatement3.setInt(1, orderId);
		preparedStatement3.setInt(2, paymentOption);
		preparedStatement3.setString(3, bill.getFirstName());
		preparedStatement3.setString(4, bill.getLastName());
		preparedStatement3.setString(5, emailAddress);
		preparedStatement3.setString(6, bill.getPhoneNumber());
		preparedStatement3.setString(7, bill.getAddressTitle());
		preparedStatement3.setString(8, bill.getAddressDetail());
		preparedStatement3.setString(9, bill.getCity());
		preparedStatement3.setString(10, bill.getDistrict());
		preparedStatement3.setString(11, bill.getPostCode());
		preparedStatement3.setString(12, CheckUtils.dateToString(date));
		preparedStatement3.executeUpdate();

		for (BillItem billItem : billItems) {
			PreparedStatement preparedStatement4 = (PreparedStatement) connection.prepareStatement(INSERT_BILL_ITEM);
			preparedStatement4.setInt(1, orderId);
			preparedStatement4.setInt(2, billItem.getQuantity());
			preparedStatement4.setDouble(3, billItem.getTotalPrice());
			preparedStatement4.setString(4, billItem.getProductName());
			preparedStatement4.setString(5, billItem.getCategoryName());
			preparedStatement4.setString(6, billItem.getPlatformName());
			preparedStatement4.setString(7, billItem.getCode());
			preparedStatement4.executeUpdate();
		}
	}

	@Override
	public Collection<Bill> listBills(String emailAddress) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(LIST_BILL_ITEM);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setBoolean(2, true);
		ResultSet resultSet = preparedStatement.executeQuery();
		Collection<Bill> bills = new ArrayList<Bill>();
		Collection<BillItem> billItems = new ArrayList<BillItem>();
		int orderIdForBill = 0;
		while (resultSet.next()) {
			int orderId = resultSet.getInt("ORDERID");
			String firstName = resultSet.getString("FIRSTNAME");
			String lastName = resultSet.getString("LASTNAME");
			String phoneNumber = resultSet.getString("PHONENUMBER");
			String addressTitle = resultSet.getString("ADDRESS_TITLE");
			String addressDetail = resultSet.getString("ADDRESS_DETAIL");
			String city = resultSet.getString("CITY");
			String district = resultSet.getString("DISTRICT");
			String postCode = resultSet.getString("POSTCODE");
			Date date = resultSet.getDate("DATE");
			Bill bill = new Bill(firstName, lastName, emailAddress, phoneNumber, addressTitle, addressDetail, city,
					district, postCode, date);
			String platformName = resultSet.getString("PLATFORM_NAME");
			String categoryName = resultSet.getString("CATEGORY_NAME");
			String productName = resultSet.getString("PRODUCT_NAME");
			String code = resultSet.getString("CODE");
			double totalPrice = resultSet.getDouble("TOTALPRICE");
			int quantity = resultSet.getInt("QUANTITY");
			BillItem billItem = new BillItem(quantity, totalPrice, code, productName, categoryName, platformName);

			billItems.add(billItem);
			if (orderIdForBill != orderId) {
				bills.add(bill);
				billItems.clear();
				orderIdForBill = orderId;
			}
		}
		if(bills.isEmpty()) return null;
		return bills;
	}
}
