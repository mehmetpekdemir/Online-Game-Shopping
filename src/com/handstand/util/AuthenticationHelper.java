package com.handstand.util;

import com.handstand.entity.Admin;
import com.handstand.service.AdminService;
/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class AuthenticationHelper {

	public static boolean isAdmin(String emailAddress, String password) {
		Admin admin = null;
		try {
			AdminService adminService = new AdminService();
			admin = adminService.findAdmin(emailAddress, password);
			if (admin == null) {
				return false;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}

		return true;
	}
	

}
