package com.handstand.service;

import java.sql.SQLException;

import com.handstand.dao.AdminDao;
import com.handstand.entity.Admin;
import com.handstand.service.impl.AdminServiceImpl;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */

public class AdminService implements AdminServiceImpl {

	public Admin findAdmin(String emailAddress, String password) throws ClassNotFoundException, SQLException {
		AdminDao adminDao = new AdminDao();
		return adminDao.findAdmin(emailAddress, password);
	}

	public Admin findAdmin(String emailAddress) throws ClassNotFoundException, SQLException {
		AdminDao adminDao = new AdminDao();
		return adminDao.findAdmin(emailAddress);
	}

}
