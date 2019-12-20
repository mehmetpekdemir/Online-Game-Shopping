package com.handstand.dao;

import java.sql.SQLException;

import com.handstand.entity.Admin;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public interface AdminDao {
	public Admin findAdmin(String adminUserName, String adminPassword) throws ClassNotFoundException, SQLException;

	public Admin findAdmin(String adminUserName) throws ClassNotFoundException, SQLException;

}
