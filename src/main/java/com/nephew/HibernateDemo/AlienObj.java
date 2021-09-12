package com.nephew.HibernateDemo;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="alien_table")
@Cacheable
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE)
public class AlienObj {
	
	@Id
	private int aid;
	private String aname;
	// The default fetch type is lazy. 
	// Lazy fetching results in multiple queries being performed on the
	// server when the query is relational. 
	// @OneToMany(mappedBy="alien") // This is what is what is used in Lazy fetching.
	@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE) // A couple hours of headache
	@OneToMany(mappedBy="alien", fetch=FetchType.EAGER) // Changed fetch to EAGER
	private Collection<LaptopObj> laps = new ArrayList<LaptopObj>();

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public Collection<LaptopObj> getLaps() {
		return laps;
	}

	public void setLaps(Collection<LaptopObj> laps) {
		this.laps = laps;
	}

	@Override
	public String toString() {
		return "AlienObj [aid=" + aid + ", aname=" + aname + "]";
	}
	
	
}
