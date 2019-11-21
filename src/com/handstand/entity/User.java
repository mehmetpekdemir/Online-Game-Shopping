package com.handstand.entity;

import java.io.Serializable;
/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String firstName;
	private String lastName;
	private String emailAddress;
	private String password;

	// Kayıt olurken veya bilgileri güncellerken gerekecek.
	public User(String firstName, String lastName, String emailAddress, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress;
		this.password = password;
	}

	// Giriş yapmak için gerekli.
	public User(String emailAddress, String password) {
		this.emailAddress = emailAddress;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
