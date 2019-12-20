package com.handstand.service.impl;

import java.sql.SQLException;

import java.util.List;
import java.util.Locale;

import com.handstand.dao.impl.ProductDaoImpl;
import com.handstand.entity.Product;
import com.handstand.service.ProductService;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class ProductServiceImpl implements ProductService {

	@Override
	public List<Product> listProduct() throws ClassNotFoundException, SQLException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		return productDao.listProduct();
	}

	@Override
	public Product findProduct(String code) throws ClassNotFoundException, SQLException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		return productDao.findProduct(code);
	}

	@Override
	public void updateProduct(Product product) throws ClassNotFoundException, SQLException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.updateProduct(product);
	}

	@Override
	public void addProduct(Product product) throws ClassNotFoundException, SQLException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.addProduct(product);
	}

	@Override
	public void deleteProduct(String code) throws ClassNotFoundException, SQLException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		productDao.deleteProduct(code);
	}

	public List<Product> searchProduct(String productNameSearch) throws ClassNotFoundException, SQLException {
		//Ürünü hem kücük harf yapıpyorum hemde Turkce karakter
		if (productNameSearch != null) {
			String searchLowerCase = productNameSearch.toLowerCase(Locale.forLanguageTag("tr"));
			ProductDaoImpl productDao = new ProductDaoImpl();
			return productDao.searchProduct(searchLowerCase);
		}
		return null;
	}

	@Override
	public List<Product> priceHighestProduct() throws ClassNotFoundException, SQLException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		return productDao.priceHighestProduct();

	}

	@Override
	public List<Product> priceLowestProduct() throws ClassNotFoundException, SQLException {
		ProductDaoImpl productDao = new ProductDaoImpl();
		return productDao.priceLowestProduct();
	}

}
