package com.nephew.HibernateDemo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="laptop_table")
@Cacheable(true)
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class LaptopObj {
	
	@Id
	private int lid;
	private String lname;
	private int price;
	@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE) // A couple hours of headache
	@ManyToOne
	private AlienObj alien;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public AlienObj getAlien() {
		return alien;
	}
	public void setAlien(AlienObj alien) {
		this.alien = alien;
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	@Override
	public String toString() {
		return "LaptopObj [lid=" + lid + ", lname=" + lname + ", price=" + price + ", alien=" + alien + "]";
	}
	
	
	
}
