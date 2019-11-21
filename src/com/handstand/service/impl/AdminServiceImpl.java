package com.handstand.service.impl;

import java.sql.SQLException;

import com.handstand.entity.Admin;
/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public interface AdminServiceImpl {
	public Admin findAdmin(String emailAddress, String password) throws ClassNotFoundException, SQLException;

	public Admin findAdmin(String emailAddress) throws ClassNotFoundException, SQLException;
	
}
