package com.handstand.entity;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class Admin extends User {

	private static final long serialVersionUID = 1L;
	private int userId;

	//Admin giriş yaparken gerekli .
	public Admin(String emailAddress, String password) {
		super(emailAddress, password);
	}

	//Admin eklerken ihtiyacımız olabilir
	public Admin(String firstName, String lastName, String emailAddress, String password) {
		super(firstName, lastName, emailAddress, password);
	}

	// Getter methodum
	public int getUserId() {
		return userId;
	}

}