package com.nephew.HibernateDemo;

import org.hibernate.CacheMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAG_CachingLvl2 {
	
	public static void main(String[] args) {
		
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(AlienObj.class).addAnnotatedClass(LaptopObj.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session1 = sf.openSession(); // Begin session 1
		session1.beginTransaction();
		AlienObj ao = (AlienObj) session1.get(AlienObj.class, 1);
		System.out.println(ao);
		
		session1.getTransaction().commit();
		session1.close(); // End session 1
		
		
		Session session2 = sf.openSession(); // Begin session 2
		session2.beginTransaction();
		
		// For Session2 to learn about the same object that was queried in the Session1
		// it must be queried, because second level caching is not taking place. 
		ao = (AlienObj) session2.get(AlienObj.class, 1);
		System.out.println(ao);

		session2.getTransaction().commit();
		session2.close(); // End session 2
		
		Session session3 = sf.openSession(); // Begin session 3
		session3.beginTransaction();
		
		Query q1 = session3.createQuery("from AlienObj where aid=1");
		q1.setCacheable(true);
		
		ao = (AlienObj) q1.uniqueResult();
		System.out.println(ao);
		
		session3.getTransaction().commit();
		session3.close(); // End session 3
		
		Session session4 = sf.openSession(); // Begin session 4
		session4.beginTransaction();
		
		Query q2 = session4.createQuery("from AlienObj where aid=1");
		q2.setCacheable(true);
		
		ao = (AlienObj) q2.uniqueResult();
		System.out.println(ao);
		
		session4.getTransaction().commit();
		session4.close(); // End session 4
		
		
	}

}
