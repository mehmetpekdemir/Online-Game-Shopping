package com.handstand.service;

import java.sql.SQLException;

import com.handstand.entity.Admin;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public interface AdminService {
	public Admin findAdmin(String emailAddress, String password) throws ClassNotFoundException, SQLException;

	public Admin findAdmin(String emailAddress) throws ClassNotFoundException, SQLException;

	public void updateAdmin(Admin admin) throws ClassNotFoundException, SQLException;

}
