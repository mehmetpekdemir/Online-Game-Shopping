package com.handstand.dao;

import java.sql.SQLException;

import com.handstand.entity.Admin;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public interface AdminDaoImp {
	
	public Admin findAdmin(String emailAddress, String password) throws ClassNotFoundException, SQLException;

	public Admin findAdmin(String emailAddress) throws ClassNotFoundException, SQLException;
	
	public void updateAdmin(Admin admin)throws ClassNotFoundException, SQLException;

}
