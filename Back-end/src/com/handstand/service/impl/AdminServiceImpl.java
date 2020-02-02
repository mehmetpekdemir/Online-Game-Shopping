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

	public Admin findAdmin(String emailAddress, String password) throws ClassNotFoundException, SQLException {
		AdminDaoImpl adminDao = new AdminDaoImpl();
		return adminDao.findAdmin(emailAddress, password);
	}

	public Admin findAdmin(String emailAddress) throws ClassNotFoundException, SQLException {
		AdminDaoImpl adminDao = new AdminDaoImpl();
		return adminDao.findAdmin(emailAddress);
	}

	@Override
	public void updateAdmin(Admin admin) throws ClassNotFoundException, SQLException {
		AdminDaoImpl adminDao = new AdminDaoImpl();
		adminDao.updateAdmin(admin);
	}
	

}
