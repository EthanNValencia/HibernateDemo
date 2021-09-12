package com.nephew.HibernateDemo;

import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAJ_ObjectStates {
	public static void main(String[] args) {
		
		// insertData();
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(Laptop_ObjState.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session = sf.openSession(); 
		session.beginTransaction();
		
		Laptop_ObjState l = new Laptop_ObjState();
		l.setLid(52);
		l.setBrand("Lenovo");
		l.setPrice(700);
		
		session.save(l); // the object is now in persistent state
		l.setPrice(850); // is it saved? 
		/*
		 * Even though it would seem like it was saved before changing the price to
		 * 650 -- it was in persistent state. So 650 is saved. 
		 */
		
		session.getTransaction().commit();
		
		session.evict(l); // Now it is evicted from the session, but this also only happens after the commit, so it might not be very useful. 
		l.setPrice(725);
		
		session.close(); 
	}
	
	public static void insertData() {
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(Laptop_ObjState.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session1 = sf.openSession(); // Begin session 1
		session1.beginTransaction();
		
		Random r = new Random();
		
		for(int i = 1; i <= 50; i++) {
			Laptop_ObjState s = new Laptop_ObjState();
			s.setLid(i);
			s.setBrand("Brand " + i);
			s.setPrice(r.nextInt(2000));
			session1.save(s);
		}
		
		session1.getTransaction().commit();
		session1.close(); // End session 1
	}
}
