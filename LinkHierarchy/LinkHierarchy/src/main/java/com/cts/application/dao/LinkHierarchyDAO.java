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

	private Session s;
	@SuppressWarnings("rawtypes")
	private Query query;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<PageA> getAllPageAContent() {
		s = sessionFactory.getCurrentSession();
		query = s.createQuery(queryPageA);
		@SuppressWarnings("unchecked")
		List<PageA> page_A_List = query.list();
        if(page_A_List.size()==0)
        {
        log.info("Empty list in table A");
        }
		return page_A_List;
	}

	public List<PageB> getAllPageBContent(String link_name) {
		s = sessionFactory.getCurrentSession();

		query = s.createQuery(queryPageB).setParameter(1, link_name);
		@SuppressWarnings("unchecked")
		List<PageB> page_B_List = query.list();
		if(page_B_List.size()==0)
        {
        log.info("Empty list in table B");
        }
		return page_B_List;

	}

	public List<PageC> getAllPageCContent(String link_name) {
		s = sessionFactory.getCurrentSession();
		query = s.createQuery(queryPageC).setParameter(1, link_name);
		@SuppressWarnings("unchecked")
		List<PageC> page_C_List = query.list();
		if(page_C_List.size()==0)
        {
        log.info("Empty list in table C");
        }
		return page_C_List;

	}

	public List<PageD> getAllPageDContent(String link_name) {
		s = sessionFactory.getCurrentSession();
		query = s.createQuery(queryPageD).setParameter(1, link_name);
		@SuppressWarnings("unchecked")
		List<PageD> page_D_List = query.list();
		if(page_D_List.size()==0)
        {
        log.info("Empty list in table D");
        }
		return page_D_List;
	}

}
