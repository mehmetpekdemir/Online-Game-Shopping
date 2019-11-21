package com.handstand.entity;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class Admin extends User {

	private static final long serialVersionUID = 1L;
	private int userId;

	public Admin(String emailAddress, String password) {
		super(emailAddress, password);
	}

	public Admin(String firstName, String lastName, String emailAddress, String password) {
		super(firstName, lastName, emailAddress, password);
	}

	public int getUserId() {
		return userId;
	}

}