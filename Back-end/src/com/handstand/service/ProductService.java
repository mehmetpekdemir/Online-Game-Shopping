package com.handstand.service;

import java.sql.SQLException;

import java.util.List;

import com.handstand.entity.Product;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public interface ProductService {
	public List<Product> listProduct() throws ClassNotFoundException, SQLException;

	public Product findProduct(String code) throws ClassNotFoundException, SQLException;

	public void updateProduct(Product product) throws ClassNotFoundException, SQLException;

	public void addProduct(Product product) throws ClassNotFoundException, SQLException;

	public void deleteProduct(String code) throws ClassNotFoundException, SQLException;

	public List<Product> searchProduct(String productNameSearch) throws ClassNotFoundException, SQLException;
	
	public List<Product> priceHighestProduct() throws ClassNotFoundException, SQLException;
	
	public List<Product> priceLowestProduct() throws ClassNotFoundException, SQLException;
}
