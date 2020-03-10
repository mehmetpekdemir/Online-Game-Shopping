package com.handstand.util;

import java.sql.SQLException;

import com.handstand.entity.Admin;
import com.handstand.service.AdminServiceImpl;
import com.handstand.service.CategoryServiceImpl;
import com.handstand.service.impl.CustomerServiceImpl;

/**
 * 
 * @author MEHMET PEKDEMIR , YUSUF YUCEDAG
 *
 */
public class AuthenticationHelper {

	public static boolean isAdmin(String emailAddress, String password) {
		Admin admin = null;
		try {
			AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
			admin = adminServiceImpl.findAdmin(emailAddress, password);
			if (admin == null) {
				return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return true;
	}

	public static int getCategoryId(String categoryName) {
		int categoryId = 0;
		try {
			CategoryServiceImpl categoryServiceImpl = new CategoryServiceImpl();
			categoryId = categoryServiceImpl.findCategoryWithCategoryName(categoryName);
		} catch (ClassNotFoundException | SQLException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		return categoryId;
	}

	/*
	 * kullanici kayit olurken email adresinin tekrarini onlemek icin yazildi input:
	 * Anonim kullanicinin girdigi email adresi return : boolean
	 */
	public static boolean emailController(String emailAddress) throws ClassNotFoundException, SQLException {

		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		return customerServiceImpl.emailController(emailAddress);
	}

}
