package com.handstand.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
/**
 * 
 * @author YUSUF YUCEDAG
 *
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private Date date;
	private boolean completeFlag;
	private Collection<OrderItem> orderItems = new ArrayList<OrderItem>();
	
	public Order(Date date, boolean completeFlag) {
		this.date = date;
		this.completeFlag = completeFlag;
	}

	public int getId() {
		return id;
	}

	public Collection<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Collection<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean getCompleteFlag() {
		return completeFlag;
	}

	public void setCompleteFlag(boolean completeFlag) {
		this.completeFlag = completeFlag;
	}
}
