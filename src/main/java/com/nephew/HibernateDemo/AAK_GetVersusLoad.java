package com.nephew.HibernateDemo;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAK_GetVersusLoad {
	public static void main(String[] args) {
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(Laptop_ObjState.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session = sf.openSession(); 
		session.beginTransaction();
		
		Laptop_ObjState lap1 = (Laptop_ObjState) session.load(Laptop_ObjState.class, 6); // This does not actually query. 
		System.out.println("They query will be conducted when I actually use need the object.");
		System.out.println(lap1);
		
		
		lap1 = (Laptop_ObjState) session.load(Laptop_ObjState.class, 60000); // This does not exist
		try {
			System.out.println(lap1);
		} catch(ObjectNotFoundException e) {
			System.out.println("The object was not found.");
		}
		
		session.getTransaction().commit();
		session.close(); 
	}
}
