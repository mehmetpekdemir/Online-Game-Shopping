package com.handstand.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int platformId;
	private String platformName;
	private String categoryName;
	private Collection<Product> products = new ArrayList<Product>();

	// Default Consturcutor
	public Category() {

	}

	// Categoriyi güncellemek için kullanıyorum
	public Category(int id, String platformName, String categoryName) {
		this.id = id;
		this.platformName = platformName;
		this.categoryName = categoryName;
	}

	// Categoriyi bulmak için gerekli (güncelleme islemimin get methodunda
	// kullanıyorum.)
	public Category(int id, String categoryName) {
		this.id = id;
		this.categoryName = categoryName;
	}

	// Kategorileri listelemek için gerekli.
	public Category(int id, int platformId, String platformName, String categoryName) {
		this.id = id;
		this.platformId = platformId;
		this.platformName = platformName;
		this.categoryName = categoryName;
	}

	// Yeni Kategori oluşturmak için gerekli.
	public Category(String platformName, String categoryName) {
		this.platformName = platformName;
		this.categoryName = categoryName;
	}

	//Getter ve Setter metodlarım
	public int getId() {
		return id;
	}

	public int getPlatformId() {
		return platformId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public void setProducts(Collection<Product> products) {
		this.products = products;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

}
