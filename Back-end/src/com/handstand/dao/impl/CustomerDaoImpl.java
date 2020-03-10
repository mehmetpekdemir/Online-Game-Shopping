package com.handstand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.handstand.connection.MySQLConnectionUtils;
import com.handstand.dao.CustomerDao;
import com.handstand.dao.impl.CustomerDaoImpl;
import com.handstand.entity.Customer;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public class CustomerDaoImpl implements CustomerDao {
	
	/*
	 * Ihtiyac duyulan sql ifadelerinin tanimlandigi kisim
	 */
	private static final String CUSTOMER_EMAIL_CONTROLLER = "SELECT users.EMAILADDRESS "
			+ "FROM users WHERE EMAILADDRESS =?";
	private static final String INSERT_USER = "INSERT INTO users(FIRSTNAME,LASTNAME,EMAILADDRESS,PASSWORD) "
			+ "VALUES(?,?,?,?)";
	private static final String FIND_CUSTOMER_WITH_EMAILADDRESS_AND_PASSWORD = "SELECT users.FIRSTNAME,users.LASTNAME,customers.PHONENUMBER "
			+ "FROM users INNER JOIN customers ON users.ID = customers.USERID "
			+ "AND users.EMAILADDRESS =? AND users.PASSWORD =?";
	private static final String FIND_CUSTOMER_WITH_EMAILADDRESS = "SELECT users.FIRSTNAME,users.LASTNAME,users.PASSWORD,customers.PHONENUMBER "
			+ "FROM users INNER JOIN customers ON users.ID = customers.USERID AND users.EMAILADDRESS =? ";
	private static final String INSERT_CUSTOMER = "INSERT INTO customers(USERID,PHONENUMBER) "
			+ "SELECT users.ID , ? FROM users WHERE users.EMAILADDRESS =? ";

	private static final String UPDATE_CUSTOMER = "UPDATE customers INNER JOIN users ON "
			+ "customers.USERID=users.ID and users.EMAILADDRESS=? set users.FIRSTNAME =?,"
			+ "users.LASTNAME=?,users.PASSWORD=?,customers.PHONENUMBER=?";

	/*
	 * Unique olan EMAILADDRESS degerinin tekrarini onlemek icin kayýt asamasýnda
	 * girilen email adresi kontrol edilir(duplicate veri girisi onlenir) input :
	 * Anonim kullanicinin girdigi email adresi return : boolean (true or false)
	 */
	@Override
	public boolean emailController(String emailAddress) throws ClassNotFoundException, SQLException {

		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(CUSTOMER_EMAIL_CONTROLLER);
		preparedStatement.setString(1, emailAddress);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			return false;
		}
		return true;
	}

	/*
	 * Customer kayit etmek icin yazilmistir input: Customer nesnesi
	 */
	@Override
	public void insertCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_USER);
		PreparedStatement preparedStatement2 = (PreparedStatement) connection.prepareStatement(INSERT_CUSTOMER);
		preparedStatement.setString(1, customer.getFirstName());
		preparedStatement.setString(2, customer.getLastName());
		preparedStatement.setString(3, customer.getEmailAddress());
		preparedStatement.setString(4, customer.getPassword());
		preparedStatement.executeUpdate();
		preparedStatement2.setString(1, customer.getPhoneNumber());
		preparedStatement2.setString(2, customer.getEmailAddress());
		preparedStatement2.executeUpdate();
		return;
	}

	/*
	 * Kayitli customerin email adresi ve sifresi ile bulunmasi icin yazilmistir
	 * input : email adresi , sifre return : Customer nesnesi
	 */
	@Override
	public Customer findCustomer(String emailAddress, String password) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(FIND_CUSTOMER_WITH_EMAILADDRESS_AND_PASSWORD);
		preparedStatement.setString(1, emailAddress);
		preparedStatement.setString(2, password);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String firstName = resultSet.getString("FIRSTNAME");
			String lastName = resultSet.getString("LASTNAME");
			String phoneNumber = resultSet.getString("PHONENUMBER");
			Customer customer = new Customer(phoneNumber, firstName, lastName, emailAddress, password);
			return customer;
		}
		return null;
	}

	/*
	 * Kayitli customerin email adresi ile bulunmasi icin yazilmistir (Overloaded
	 * function) input : email adresi return : Customer nesnesi
	 */
	@Override
	public Customer findCustomer(String emailAddress) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection
				.prepareStatement(FIND_CUSTOMER_WITH_EMAILADDRESS);
		preparedStatement.setString(1, emailAddress);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			String firstName = resultSet.getString("FIRSTNAME");
			String lastName = resultSet.getString("LASTNAME");
			String password = resultSet.getString("PASSWORD");
			String phoneNumber = resultSet.getString("PHONENUMBER");
			Customer customer = new Customer(phoneNumber, firstName, lastName, emailAddress, password);
			return customer;
		}
		return null;
	}

	/*
	 * Customerin bilgilerinin guncellenmesi icin yazilmistir input : Customer
	 * nesnesi
	 */
	@Override
	public void updateCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(UPDATE_CUSTOMER);
		preparedStatement.setString(1, customer.getEmailAddress());
		preparedStatement.setString(2, customer.getFirstName());
		preparedStatement.setString(3, customer.getLastName());
		preparedStatement.setString(4, customer.getPassword());
		preparedStatement.setString(5, customer.getPhoneNumber());
		preparedStatement.executeUpdate();
	}
}
