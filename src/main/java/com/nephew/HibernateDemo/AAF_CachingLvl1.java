package com.nephew.HibernateDemo;

import java.util.Collection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAF_CachingLvl1 {
	
	public static void main(String[] args) {
		AlienObj ao = null;
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(AlienObj.class).addAnnotatedClass(LaptopObj.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session1 = sf.openSession();
		session1.beginTransaction();
		
		ao = (AlienObj) session1.get(AlienObj.class, 1);
		System.out.println(ao);
		
		ao = (AlienObj) session1.get(AlienObj.class, 1);
		System.out.println(ao);
		// Session is smart enough to know that at 1 has been queried, 
		// so it doesn't need to be queried twice. This is first level caching. 
		
		
		session1.getTransaction().commit();
		session1.close(); 
		
		// Session 2
		Session session2 = sf.openSession();
		session2.getTransaction().begin();
		
		// For Session2 to learn about the same object that was queried in the Session1
		// it must be queried, because second level caching is not taking place. 
		ao = (AlienObj) session2.get(AlienObj.class, 1);
		System.out.println(ao);
		
		session2.getTransaction().commit();
		session2.close();
	}

}
