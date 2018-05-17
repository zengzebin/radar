package com.ssxt.common.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @ClassName: HibernateSupportNick
 * @Description: leave out multithreading. 'session' should be a threadlocal
 *               parameter where program in multithreading.
 * @author nick
 * @date 2016年4月26日 上午8:49:03
 * 
 */
public class HibernateSupportNick {
	@Autowired
	public SessionFactory sessionFactory;
	
	private static final ThreadLocal<Session> session = new ThreadLocal<Session>();

	public Session getSession() {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = currentSession();
		}
		return session;
	}

	private Session currentSession() throws HibernateException {
		Session s = (Session) session.get();
		if (s == null) {
			s = sessionFactory.openSession();
			session.set(s);
		}
		return s;
	}
	
	public Session openSession() {
		return sessionFactory.openSession();
	}
	
	public void closeSession() throws HibernateException {
		Session s = (Session) session.get();
		if (s != null) {
			s.close();
		}
		session.set(null);
	}
	
	public void flush() {
		getSession().flush();
	}

	public void clear() {
		getSession().clear();
	}

}
