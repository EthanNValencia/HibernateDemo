package com.nephew.HibernateDemo;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAI_HQL_Part2 {
	public static void main(String[] args) {
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(Student_HQL.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session = sf.openSession(); 
		session.beginTransaction();
		
		SQLQuery query = session.createSQLQuery("SELECT * FROM student_hql WHERE marks>60");
		query.addEntity(Student_HQL.class); 
		List<Student_HQL> students = query.list();
		
		for(Student_HQL o : students) {
			System.out.println(o);
		}
		
		
		query = session.createSQLQuery("SELECT name,marks FROM student_hql WHERE marks>60");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP); 
		List students_list = query.list();
		
		for(Object o : students_list) {
			Map m = (Map) o; 
			System.out.println(m.get("name") + " " + m.get("marks"));
		}
		
		
		session.getTransaction().commit();
		session.close(); 
	}
}
