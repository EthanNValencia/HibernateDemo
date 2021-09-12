package com.nephew.HibernateDemo;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAE_EagerAndLazyFetch {
	
	public static void main(String[] args) {
		// CreateTables();
		
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(LaptopObj.class).addAnnotatedClass(AlienObj.class);
        // ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        // SessionFactory sf = config.buildSessionFactory(reg);
		SessionFactory sf = config.buildSessionFactory();
		Session session = sf.openSession();
        
		session.beginTransaction();
		AlienObj a1 = (AlienObj) session.get(AlienObj.class, 1);
		if(a1 == null) {
        System.out.println("It is null.");
        } else {
        	System.out.println(a1.getAname());
        	Collection<LaptopObj> laps= a1.getLaps();
        	for(LaptopObj l : laps) {
        		System.out.println(l);
        	}
        }
		session.getTransaction().commit();
	}
	
	public static void CreateTables() {
    	AlienObj navin = new AlienObj();
    	AlienObj rahul = new AlienObj();
    	AlienObj mayank = new AlienObj();
    	navin.setAname("Navin");
    	navin.setAid(1);
    	rahul.setAname("Rahul");
    	rahul.setAid(2);
    	mayank.setAname("Mayank");
    	mayank.setAid(3);
    	

    	LaptopObj dell = new LaptopObj();
    	LaptopObj apple = new LaptopObj();
    	LaptopObj asus = new LaptopObj();
    	LaptopObj acer = new LaptopObj();
    	LaptopObj samsung = new LaptopObj();
    	dell.setLname("Dell");
    	dell.setLid(101);
    	dell.setPrice(1000);
    	dell.setAlien(navin);
    	apple.setLname("Apple");
    	apple.setLid(102);
    	apple.setPrice(2000);
    	apple.setAlien(mayank);
    	asus.setLname("Asus");
    	asus.setLid(103);
    	asus.setPrice(800);
    	asus.setAlien(navin);
    	acer.setLname("Acer");
    	acer.setLid(104);
    	acer.setPrice(1500);
    	acer.setAlien(mayank);
    	samsung.setLname("Samsung");
    	samsung.setLid(105);
    	samsung.setPrice(1400);
    	samsung.setAlien(navin);
    	navin.getLaps().add(dell);
    	mayank.getLaps().add(apple);
    	navin.getLaps().add(asus);
        mayank.getLaps().add(acer);
    	navin.getLaps().add(samsung);
    	
        Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(LaptopObj.class).addAnnotatedClass(AlienObj.class); 
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
        Session session = sf.openSession();
        session.beginTransaction();
        // saving laptops
        session.save(navin);
        session.save(rahul);
        session.save(mayank);
        session.getTransaction().commit();
        session.beginTransaction();
        session.save(dell);
        session.save(apple);
        session.save(asus);
        session.save(acer);
        session.save(samsung);
        session.getTransaction().commit();
        
        
        // session.save();
        // tx.commit();
    }
}
