package util;

import org.hibernate.cfg.Configuration;

import error.ErrorLog;

import java.io.File;

import org.hibernate.SessionFactory;

public class HibernateUtil {
	private static final SessionFactory m_sessionFactory;
	
	static {
		try {
			m_sessionFactory = new Configuration().configure().buildSessionFactory();
		}
		catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			ErrorLog.log(ex.getMessage());
			throw new ExceptionInInitializerError(ex);
		}
	}
	
	public static SessionFactory getSessionFactory() {
		return m_sessionFactory;
	}
}
