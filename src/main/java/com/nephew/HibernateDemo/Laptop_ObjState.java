package com.nephew.HibernateDemo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Laptop_ObjState {
	@Id
	private int lid;
	private String brand;
	private int price;
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Laptop_ObjState [lid=" + lid + ", brand=" + brand + ", price=" + price + "]";
	}
	
}
