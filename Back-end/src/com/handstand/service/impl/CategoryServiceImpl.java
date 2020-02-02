package com.handstand.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.handstand.dao.impl.CategoryDaoImpl;
import com.handstand.entity.Category;
import com.handstand.service.CategoryService;

/**
 * 
 * @author MEHMET PEKDEMIR
 *
 */
public class CategoryServiceImpl implements CategoryService {

	@Override
	public List<Category> listCategory() throws ClassNotFoundException, SQLException {
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		return categoryDao.listCategory();
	}

	@Override
	public List<Category> listCategoryWithPlatformName() throws ClassNotFoundException, SQLException {
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		return categoryDao.listCategoryWithPlatformName();
	}

	@Override
	public Category findCategory(int id, String platformName) throws ClassNotFoundException, SQLException {
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		return categoryDao.findCategory(id, platformName);
	}

	@Override
	public int findCategoryWithCategoryName(String categoryName) throws ClassNotFoundException, SQLException {
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		return categoryDao.findCategoryWithCategoryName(categoryName);
	}

	@Override
	public void addCategory(Category category) throws ClassNotFoundException, SQLException {
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		categoryDao.addCategory(category);
	}

	@Override
	public void updateCategory(Category category) throws ClassNotFoundException, SQLException {
		CategoryDaoImpl categoryDao = new CategoryDaoImpl();
		categoryDao.updateCategory(category);
	}

}
