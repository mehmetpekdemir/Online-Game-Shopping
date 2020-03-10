package com.handstand.entity;

import java.io.Serializable;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public class OrderItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private int quantity;
	private double totalPrice;
	private Product product;

	public OrderItem(int id, int quantity, double totalPrice, Product product) {
		this.product = product;
		this.id = id;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public OrderItem(int quantity, double totalPrice) {
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
