package com.handstand.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author YUSUF YÜCEDAÐ
 *
 */

//Fatura bilgisi için gerekli

public class OrderDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id; // primary key
	private String orderDetail; // ürün detaylarý
	private Date prompt; // teslim tarihi
	private int amount; // adet
	private double totalPrice; // toplam ücret
	private int orderId; // sipariþ Id oneTomany

	public OrderDetails() {

	}

	public OrderDetails(String orderDetail, Date prompt, int amount, double totalPrice) {
		this.orderDetail = orderDetail;
		this.prompt = prompt;
		this.amount = amount;
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public String getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(String orderDetail) {
		this.orderDetail = orderDetail;
	}

	public Date getPrompt() {
		return prompt;
	}

	public void setPrompt(Date prompt) {
		this.prompt = prompt;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderId() {
		return orderId;
	}

}
