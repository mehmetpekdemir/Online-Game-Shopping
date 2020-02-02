package com.handstand.entity;

import java.io.Serializable;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class Product implements Serializable {

	// open closed prensibi kullanıldı üzerine eklemeler yapılabilir.
	private static final long serialVersionUID = 1L;

	private int id;
	private int categoryId;
	private String productName;
	private String code;
	private double price;
	private int stockAmount;
	private String image;
	private String description;
	private String categoryName;
	private String platformName;

	// Sepet Listelemek icin kullanicaz
	public Product(int id, String productName, String image, String categoryName, String platformName) {
		this.id = id;
		this.productName = productName;
		this.image = image;
		this.setCategoryName(categoryName);
		this.setPlatformName(platformName);
	}

	// Ürünleri Listelemek ve güncellemek için kullanıyorum
	public Product(String platformName, String categoryName, String productName, String code, double price,
			int stockAmount, String image, String description) {
		this.platformName = platformName;
		this.categoryName = categoryName;
		this.productName = productName;
		this.code = code;
		this.price = price;
		this.stockAmount = stockAmount;
		this.image = image;
		this.description = description;
	}

	// Ürün ismine göre islemi için gerekli constructorum.
	public Product(String categoryName, String productName, double price, int stockAmount, String image,
			String description) {
		this.productName = productName;
		this.price = price;
		this.stockAmount = stockAmount;
		this.image = image;
		this.description = description;
	}

	// Ürün eklemek için kullanıtorum.
	public Product(int categoryId, String productName, String code, double price, int stockAmount, String image,
			String description) {
		this.categoryId = categoryId;
		this.productName = productName;
		this.code = code;
		this.price = price;
		this.stockAmount = stockAmount;
		this.image = image;
		this.description = description;
	}

	// Getter ve Setter Metodlarım
	public int getId() {
		return id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockAmount() {
		return stockAmount;
	}

	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

}
