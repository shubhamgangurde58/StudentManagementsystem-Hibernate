package com.shubham.StudentManagementSystem;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConn {
	
	public static SessionFactory sessionFactory;
	
	public static SessionFactory getSessionFactoryInstance() {
		
		if(sessionFactory == null) {
			
			Configuration configuration = new Configuration();
			configuration.configure();
			
			sessionFactory = configuration.buildSessionFactory();		
		}
		return sessionFactory;	
	}
}
