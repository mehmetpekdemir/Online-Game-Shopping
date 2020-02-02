package com.handstand.util;

import java.sql.SQLException;

import com.handstand.entity.Admin;
import com.handstand.service.impl.AdminServiceImpl;
import com.handstand.service.impl.CategoryServiceImpl;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class AuthenticationHelper {

	public static boolean isAdmin(String emailAddress, String password) {
		Admin admin = null;
		try {
			AdminServiceImpl adminService = new AdminServiceImpl();
			admin = adminService.findAdmin(emailAddress, password);
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
			CategoryServiceImpl categoryService = new CategoryServiceImpl();
			categoryId = categoryService.findCategoryWithCategoryName(categoryName);
		} catch (ClassNotFoundException | SQLException exception) {
			exception.printStackTrace();
		}
		return categoryId;
	}

}
