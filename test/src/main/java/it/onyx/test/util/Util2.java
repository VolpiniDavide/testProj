package it.onyx.test.util;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.onyx.test.dao.UserDao;

public class Util2 {
	
	
	public static UserDao login ( String email , String password) {
		
		//---------------configuro sessione e passo il file xml di config hibernate------//
		
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		Session session = cfg.buildSessionFactory().getCurrentSession();
		
		session.beginTransaction();
		
		//---------------configuro il criteria builder --------------------//
		
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<UserDao> cr = cb.createQuery(UserDao.class);
		
		//-----------------creo un arraylist di userdao ? ? non so, da vedere questo oggetto root ---------------/
		
		Root<UserDao> root = cr.from(UserDao.class);
		
		//--------------------------- configuro la query ----------------------------------//
		
		cr.select(root).where(cb.equal(root.get("email"), email));
		
		//-----------------------------eseguo la query------------------------------------//
		
		Query<UserDao> query = session.createQuery(cr);
		
		//------------------------------- metto il risultato dentro un arraylist -------------//
		List<UserDao> results = query.getResultList();
		
	
		UserDao ud = results.get(0);
		return ud;

}

}