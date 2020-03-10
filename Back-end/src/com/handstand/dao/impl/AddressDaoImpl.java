package com.handstand.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import com.handstand.connection.MySQLConnection;
import com.handstand.connection.MySQLConnectionUtils;
import com.handstand.dao.AddressDao;
import com.handstand.dao.impl.AddressDaoImpl;
import com.handstand.entity.Address;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public class AddressDaoImpl implements AddressDao {
	/*
	 * Ihtiyac duyulan sql ifadelerinin tanimlandigi kisim
	 */

	private static final String LIST_ADDRESS = "select addresses.ID,addresses.ADDRESS_TITLE,addresses.ADDRESS_DETAIL,"
			+ "addresses.CITY,addresses.DISTRICT,addresses.POSTCODE from "
			+ "((users INNER JOIN customers ON users.ID = customers.USERID) INNER JOIN "
			+ "addresses ON addresses.CUSTOMERID = customers.USERID) where users.EMAILADDRESS=?";
	private static final String DELETE_ADDRESS = "DELETE FROM addresses WHERE ID=?;";
	private static final String INSERT_ADDRESS = "INSERT INTO addresses(CUSTOMERID,ADDRESS_TITLE,ADDRESS_DETAIL,CITY,DISTRICT,"
			+ "POSTCODE) SELECT users.ID , ? , ? , ? , ? , ?  FROM users WHERE users.EMAILADDRESS =? ";
	private static final String FIND_ADDRESS = "SELECT a.ADDRESS_TITLE,a.ADDRESS_DETAIL,a.CITY,"
			+ "a.DISTRICT,a.POSTCODE FROM addresses a where a.ID=?";
	private static final String UPDATE_ADDRESS = "Update addresses set addresses.ADDRESS_TITLE=?,"
			+ "addresses.ADDRESS_DETAIL=?,addresses.CITY=?,addresses.DISTRICT=?,"
			+ "addresses.POSTCODE=? Where addresses.ID =?  ";

	/*
	 * Customera ait adresleri veri tabanindan çekmek için yazilmiþtir. input :
	 * Customer is email adresi return : Address classi listesi (Kullanicinin
	 * adresleri)
	 */
	@Override
	public Collection<Address> listAddress(String emailAddress) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		Collection<Address> addresses = new ArrayList<Address>();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(LIST_ADDRESS);
		preparedStatement.setString(1, emailAddress);
		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			int id = resultSet.getInt("ID");
			String addressTitle = resultSet.getString("ADDRESS_TITLE");
			String addressDetail = resultSet.getString("ADDRESS_DETAIL");
			String city = resultSet.getString("CITY");
			String district = resultSet.getString("DISTRICT");
			String postCode = resultSet.getString("POSTCODE");
			Address address = new Address(id, addressTitle, addressDetail, city, district, postCode);
			addresses.add(address);
		}
		return addresses;
	}

	/*
	 * Customerin kayitli adresini silmek için yazilmistir input : Adresin id degeri
	 */
	@Override
	public void deleteAddress(int id) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnection.getMySQLConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(DELETE_ADDRESS);
		preparedStatement.setInt(1, id);
		preparedStatement.executeUpdate();
	}

	/*
	 * Customerin adresini eklemek için yazýlmýþtýr input : Address classi nesnesi,
	 * Customerin email adresi
	 */
	@Override
	public void insertAddress(Address address, String emailAddress) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(INSERT_ADDRESS);
		preparedStatement.setString(1, address.getAddressTitle());
		preparedStatement.setString(2, address.getAddressDetail());
		preparedStatement.setString(3, address.getCity());
		preparedStatement.setString(4, address.getDistrict());
		preparedStatement.setString(5, address.getPostCode());
		preparedStatement.setString(6, emailAddress);
		preparedStatement.executeUpdate();
	}

	/*
	 * Kayitli olan adresin bulunmasi icin yazilmistir input: Adresin id degeri
	 * return : Address nesnesi
	 */
	@Override
	public Address findAddress(int id) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(FIND_ADDRESS);
		preparedStatement.setInt(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		Address address = null;
		while (resultSet.next()) {
			String addressTitle = resultSet.getString("ADDRESS_TITLE");
			String addressDetail = resultSet.getString("ADDRESS_DETAIL");
			String city = resultSet.getString("CITY");
			String district = resultSet.getString("DISTRICT");
			String postCode = resultSet.getString("POSTCODE");
			address = new Address(id, addressTitle, addressDetail, city, district, postCode);
		}
		return address;
	}

	/*
	 * Kayitli adres bilgisini guncellemek icin yazilmistir input : Address nesnesi
	 */
	@Override
	public void updateAddress(Address address) throws ClassNotFoundException, SQLException {
		Connection connection = MySQLConnectionUtils.getConnection();
		PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(UPDATE_ADDRESS);
		preparedStatement.setString(1, address.getAddressTitle());
		preparedStatement.setString(2, address.getAddressDetail());
		preparedStatement.setString(3, address.getCity());
		preparedStatement.setString(4, address.getDistrict());
		preparedStatement.setString(5, address.getPostCode());
		preparedStatement.setInt(6, address.getId());
		preparedStatement.executeUpdate();
	}
}
