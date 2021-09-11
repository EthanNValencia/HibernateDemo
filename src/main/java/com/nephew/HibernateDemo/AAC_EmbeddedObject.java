package com.nephew.HibernateDemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAC_EmbeddedObject {
	public static void main(String[] args) {
        Alien nephew = new Alien();
        AlienName alienName = new AlienName();
        alienName.setFirstName("Ethan");
        alienName.setMiddleName("Joseph");
        alienName.setLastName("Nephew");
        nephew.setAid(101);
        nephew.setAname(alienName);
        nephew.setColor("Red");
        
        Configuration config = new Configuration().configure().addAnnotatedClass(Alien.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        
        session.save(nephew);
        tx.commit(); // nephew is stored into db
	}
}
