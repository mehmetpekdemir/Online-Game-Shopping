package com.handstand.service.impl;

import java.sql.SQLException;

import com.handstand.dao.impl.AdminDaoImpl;
import com.handstand.entity.Admin;
import com.handstand.service.AdminService;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */

public class AdminServiceImpl implements AdminService {

	public Admin findAdmin(String adminUserName, String adminPassword) throws ClassNotFoundException, SQLException {
		AdminDaoImpl adminDao = new AdminDaoImpl();
		return adminDao.findAdmin(adminUserName, adminPassword);
	}

	public Admin findAdmin(String adminUserName) throws ClassNotFoundException, SQLException {
		AdminDaoImpl adminDao = new AdminDaoImpl();
		return adminDao.findAdmin(adminUserName);
	}

}
