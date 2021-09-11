package com.nephew.HibernateDemo;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name="alien_table")
public class Alien { 
	/*
	 * There is table name and entity name, usually the table name
	 * is derived from the entity name if not specified. Above is an 
	 * example of specifying the table name.  
	 *
	 */
	
	/*
	 * @Id is the primary key. 
	 * @Transient means that this will not be stored in a db.
	 * @Column specifies the column name.
	 */
	
	@Id
	private int aid;
	private AlienName aname;
	@Column(name="alien_color") 
	private String color;
	@Transient
	private String origin;
	
	
	public AlienName getAname() {
		return aname;
	}
	public void setAname(AlienName aname) {
		this.aname = aname;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + ", color=" + color + "]";
	}
	
}
