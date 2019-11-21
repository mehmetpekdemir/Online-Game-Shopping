package com.handstand.dao.impl;

import java.sql.SQLException;

import com.handstand.entity.Admin;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public interface AdminDaoImpl {
	public Admin findAdmin(String emailAddress, String password) throws ClassNotFoundException, SQLException;

	public Admin findAdmin(String emailAddress) throws ClassNotFoundException, SQLException;

}
