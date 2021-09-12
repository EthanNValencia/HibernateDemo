package com.nephew.HibernateDemo;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AA_DefaultMainMethod {
	public static void main(String[] args) {
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(Student_HQL.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session = sf.openSession(); 
		session.beginTransaction();
		
		// Type code in here
		
		session.getTransaction().commit();
		session.close(); 
	}
}
