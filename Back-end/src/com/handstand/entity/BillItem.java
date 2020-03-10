package com.handstand.entity;

import java.io.Serializable;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public class BillItem implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int billId;
	private int quantity;
	private double totalPrice;
	private String code;
	private String productName;
	private String categoryName;
	private String platformName;
	
	public BillItem(int quantity, double totalPrice, String code, String productName,
			String categoryName, String platformName) {
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.code = code;
		this.productName = productName;
		this.categoryName = categoryName;
		this.platformName = platformName;
	}

	public int getBillId() {
		return billId;
	}

	public void setBillId(int billId) {
		this.billId = billId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public int getId() {
		return id;
	}
}
