package com.nephew.HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAD_MappingRelations {
	
	public static void main(String[] args) {
		
		Laptop laptop = new Laptop();
		laptop.setLid(101);
		laptop.setLname("Dell");
		
		Student s = new Student();
		s.setName("Navin");
		s.setRollno(1);
		s.setMarks(50);
		s.getLaptop().add(laptop);
		// s.setLaptop(laptop); // One to one example.

		laptop.getStudent().add(s);
		
		
		Configuration config = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
        Session session = sf.openSession();
        session.beginTransaction();
        
        session.save(laptop);
        session.save(s);
        session.getTransaction().commit();
	}
}
