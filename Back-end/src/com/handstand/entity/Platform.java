package com.handstand.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 
 * @author MEHMET PEKDEMİR
 *
 */
public class Platform implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String platformName;
	private Collection<Category> categories = new ArrayList<Category>();

	public Platform() {

	}

	// Platformu listelemek için kullanıyorum.
	public Platform(int id, String platformName) {
		this.id = id;
		this.platformName = platformName;
	}

	// Platform Eklemek için kullanıyorum.
	public Platform(String platformName) {
		this.platformName = platformName;
	}

	// Getter ve Setter metodlarım
	public int getId() {
		return id;
	}

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public Collection<Category> getCategories() {
		return categories;
	}

	public void setCategories(Collection<Category> categories) {
		this.categories = categories;
	}

}
