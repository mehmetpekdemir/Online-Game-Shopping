package com.handstand.entity;
/**
 * 
 * @author YUSUF YUCEDAG 
 *
 */
public class Address {
	private int id;
	private String addressTitle;
	private String addressDetail;
	private String city;
	private String district;
	private String postCode;
	
	public Address(int id,String addressTitle, String addressDetail, String city, String district, String postCode) {
		this.id = id;
		this.addressTitle = addressTitle;
		this.addressDetail = addressDetail;
		this.city = city;
		this.district = district;
		this.postCode = postCode;
	}
	public Address(String addressTitle, String addressDetail, String city, String district, String postCode) {
		this.addressTitle = addressTitle;
		this.addressDetail = addressDetail;
		this.city = city;
		this.district = district;
		this.postCode = postCode;
	}

	public int getId() {
		return id;
	}
	
	public String getAddressTitle() {
		return addressTitle;
	}

	public void setAddressTitle(String addressTitle) {
		this.addressTitle = addressTitle;
	}

	public String getAddressDetail() {
		return addressDetail;
	}

	public void setAddressDetail(String addressDetail) {
		this.addressDetail = addressDetail;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}
