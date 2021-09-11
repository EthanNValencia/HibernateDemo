package com.nephew.HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Hello world!
 */
public class AAA_SimpleDBInsertion 
{
	
	/*
	 CREATE USER 'neon-hibernate'@'localhost' IDENTIFIED BY 'pw';
	 GRANT ALL PRIVILEGES ON * . * TO 'neon-hibernate'@'localhost';
	 */
	
    public static void main( String[] args )
    {
        Alien nephew = new Alien();
        
        nephew.setAid(101);
        
        nephew.setColor("Red");
        
        // Configuration is an object. 
        Configuration config = new Configuration().configure().addAnnotatedClass(Alien.class);
        // SessionFactory and Session are interfaces. 
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.save(nephew);
        tx.commit(); // nephew is stored into db
    }
}
