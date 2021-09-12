package com.nephew.HibernateDemo;

import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class AAH_HQL_Part1 {
public static void main(String[] args) {
		// insertData();
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(Student_HQL.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session1 = sf.openSession(); // Begin session 1
		session1.beginTransaction();
		
		/*
		 * This is a query, but it is not SQL it is HQL. 
		 */
		Query q = session1.createQuery("from Student_HQL");
		List<Student_HQL> students = q.list(); // This saves the query results to the list.
		
		for(Student_HQL s : students) {
			System.out.println(s);
		}
		
		q = session1.createQuery("from Student_HQL where rollno=7");
		Student_HQL student = (Student_HQL) q.uniqueResult();
		System.out.println(student);
		
		
		q = session1.createQuery("select rollno,name,marks from Student_HQL where rollno=7"); // Specifying what the results should look like. 
		Object[] student_1 = (Object[]) q.uniqueResult(); 
		
		for(Object o : student_1) { // This is the info for ONE entry
			System.out.println(o);
		}
		System.out.println(student_1[0] + " " + student_1[1] + " " + student_1[2]);
		
		
		q = session1.createQuery("select rollno,name,marks from Student_HQL"); // Specifying what the results should look like. 
		List<Object[]> student_list = (List<Object[]>) q.list(); 
		
		for(Object[] student_hql : student_list) {
			System.out.println(student_hql[0] + " : " + student_hql[1] + " : " + student_hql[2]);
		}
		
		q = session1.createQuery("select sum(marks) from Student_HQL"); // Specifying what the results should look like. 
		Long student_marks = (Long) q.uniqueResult(); // sum returns a long.
		System.out.println(student_marks);
		
		int sixty = 60; // Adding the 60 into the HQL query below. 
		q = session1.createQuery("select sum(marks) from Student_HQL s where s.marks > :b"); // Specifying what the results should look like. 
		q.setParameter("b", sixty); // :b is replaced with the value in sixty (60).
		student_marks = (Long) q.uniqueResult(); // We know that sum will only return one result. 
		System.out.println(student_marks);
		
		session1.getTransaction().commit();
		session1.close(); // End session 1
		
	}




	public static void insertData() {
		Configuration config = new Configuration().configure("alienlaptop.cfg.xml").addAnnotatedClass(Student_HQL.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
        SessionFactory sf = config.buildSessionFactory(reg);
		Session session1 = sf.openSession(); // Begin session 1
		session1.beginTransaction();
		
		Random r = new Random();
		
		for(int i = 1; i <= 50; i++) {
			Student_HQL s = new Student_HQL();
			s.setRollno(i);
			s.setName("Name" + i);
			s.setMarks(r.nextInt(100));
			session1.save(s);
		}
		session1.getTransaction().commit();
		session1.close(); // End session 1
	}
}
