package com.cts.application.dao;

import java.util.List;

import org.apache.log4j.Logger;

//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.cts.application.model.PageA;
import com.cts.application.model.PageB;
import com.cts.application.model.PageC;
import com.cts.application.model.PageD;

@PropertySource("classpath:sql.properties")
@Repository
@Transactional
public class LinkHierarchyDAO {


	private static Logger log=Logger.getLogger(LinkHierarchyDAO.class);
			
	@Value("${db.query.PageA}") // To query pageA
	private String queryPageA;

	@Value("${db.query.PageB}") // To query pageB
	private String queryPageB;

	@Value("${db.query.PageC}") // To query pageC
	private String queryPageC;

	@Value("${db.query.PageD}") // To query pageD
	private String queryPageD;

	@Autowired
	SessionFactory sessionFactory;

	private Session session;
	@SuppressWarnings("rawtypes")
	private Query query;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<PageA> getAllPageAContent() {
		session = sessionFactory.getCurrentSession();  //Gets the session from sessionfactory
		query = session.createQuery(queryPageA);       //Creates the query using session object
		List<PageA> pageAList = query.list();          //Retrieves the list from database
	
        if(pageAList.size()==0)
        {
        log.info("Link not available in table A");             //Logs into log file if link not found is returned
        }
		return pageAList;
	}

	public List<PageB> getAllPageBContent(String linkname) {
		session = sessionFactory.getCurrentSession(); //Gets the session from sessionfactory
		query = session.createQuery(queryPageB).setParameter(1, linkname); //Creates the query using session object
		@SuppressWarnings("unchecked")
		List<PageB> pageBList = query.list();
		if(pageBList.size()==0)
        {
        log.info("Link not available in table B");            //Logs into log file if link not found is returned
        }
		return pageBList;

	}

	public List<PageC> getAllPageCContent(String linkname) {
		session = sessionFactory.getCurrentSession();  //Gets the session from sessionfactory
		query = session.createQuery(queryPageC).setParameter(1, linkname); //Retrieves the list from database
		@SuppressWarnings("unchecked")
		List<PageC> pageCList = query.list();          //Retrieves the list from database
		if(pageCList.size()==0)
        {
        log.info("Link not available in table C");              //Logs into log file if link not found is returned
        }
		return pageCList;

	}

	public List<PageD> getAllPageDContent(String linkname) {
		session= sessionFactory.getCurrentSession(); //Gets the session from sessionfactory
		query = session.createQuery(queryPageD).setParameter(1, linkname); //Retrieves the list from database
		@SuppressWarnings("unchecked")
		List<PageD> pageDList = query.list();          //Retrieves the list from database
		if(pageDList.size()==0)
        {
        log.info("Link not available in table D");              //Logs into log file if link not found is returned
        }
		
		return pageDList;
	}

}
