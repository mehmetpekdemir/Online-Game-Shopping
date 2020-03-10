package com.handstand.entity;

import java.util.ArrayList;
import java.util.Collection;
/**
 * 
 * @author YUSUF YUCEDAG 
 *
 */
public class Customer extends User {

	private static final long serialVersionUID = 1L;

	private int userId;
	private String phoneNumber;
	private Collection<Address> addresses = new ArrayList<Address>();
	private Collection<Order> orders = new ArrayList<Order>();

	public Customer(String phoneNumber, String firstName, String lastName, String emailAddress, String password) {
		super(firstName, lastName, emailAddress, password);
		this.phoneNumber = phoneNumber;
	}

	public int getUserId() {
		return userId;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Collection<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(Collection<Address> addresses) {
		this.addresses = addresses;
	}

	public Collection<Order> getOrders() {
		return orders;
	}

	public void setOrders(Collection<Order> orders) {
		this.orders = orders;
	}

}
