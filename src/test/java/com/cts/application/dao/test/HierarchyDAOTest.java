package com.cts.application.dao.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

import com.cts.application.dao.LinkHierarchyDAO;
import com.cts.application.model.PageA;
import com.cts.application.model.PageB;
import com.cts.application.model.PageC;
import com.cts.application.model.PageD;

import junit.framework.TestCase;

@SpringBootTest
@PropertySource("classpath:sql.properties")
@RunWith(MockitoJUnitRunner.class)
public class HierarchyDAOTest extends TestCase {

	@Value("${db.query.PageA}") // To query pageA
	private String queryPageA;

	@Value("${db.query.PageB}") // To query pageB
	private String queryPageB;

	@Value("${db.query.PageC}") // To query pageC
	private String queryPageC;

	@Value("${db.query.PageD}") // To query pageD
	private String queryPageD;

	@Mock
	private SessionFactory sessionFactory;
	@Mock
	private Session session;

	@SuppressWarnings("rawtypes")
	@Mock
	private Query query;

	@SuppressWarnings("rawtypes")
	@Mock
	private Query query2;

	PageA pageA ;
	PageB pageB ;
	PageC pageC ;
	PageD pageD ;
	
	@Before
	public void setup(){
	 pageA = new PageA(1, "InvestmentBanking");
	 pageB = new PageB(1, "CorporateFinance", pageA);
	 pageC = new PageC(1, "Industry_Coverage", pageB);
	 pageD = new PageD(1, "Healthcare", pageC);
	}
	@Test
	public void testGetAllPageAContent() {

		LinkHierarchyDAO hierarchyDAO = new LinkHierarchyDAO();
		List<PageA> pageAlist = Arrays.asList(pageA);
		hierarchyDAO.setSessionFactory(this.sessionFactory);
		Mockito.when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		Mockito.when(this.session.createQuery(queryPageA)).thenReturn(this.query);
		Mockito.when(this.query.list()).thenReturn(pageAlist);
		assertEquals(pageAlist.toString(), hierarchyDAO.getAllPageAContent().toString());
	}

	@Test
	public void testGetAllPageBContent() {
		String trial = "anyString";
		LinkHierarchyDAO hierarchyDAO = new LinkHierarchyDAO();
		List<PageB> pageBlist = Arrays.asList(pageB);
		hierarchyDAO.setSessionFactory(this.sessionFactory);
		Mockito.when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		Mockito.when(this.session.createQuery(queryPageB)).thenReturn(this.query);
		Mockito.when(this.query.setParameter(1, trial)).thenReturn(query);
		Mockito.when(this.query.list()).thenReturn(pageBlist);
		assertEquals(pageBlist.toString(), hierarchyDAO.getAllPageBContent(trial).toString());
	}

	@Test
	public void testGetAllPageCContent() {
		String trial = "anyString";
		LinkHierarchyDAO hierarchyDAO = new LinkHierarchyDAO();
		List<PageC> pageClist = Arrays.asList(pageC);
		hierarchyDAO.setSessionFactory(this.sessionFactory);
		Mockito.when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		Mockito.when(this.session.createQuery(queryPageB)).thenReturn(this.query);
		Mockito.when(this.query.setParameter(1, trial)).thenReturn(query);
		Mockito.when(this.query.list()).thenReturn(pageClist);
		assertEquals(pageClist.toString(), hierarchyDAO.getAllPageCContent(trial).toString());
	}

	@Test
	public void testGetAllPageDContent() {
		String trial = "anyString";
		LinkHierarchyDAO hierarchyDAO = new LinkHierarchyDAO();
		List<PageD> pageDlist = Arrays.asList(pageD);
		hierarchyDAO.setSessionFactory(this.sessionFactory);
		Mockito.when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		Mockito.when(this.session.createQuery(queryPageB)).thenReturn(this.query);
		Mockito.when(this.query.setParameter(1, trial)).thenReturn(query);
		Mockito.when(this.query.list()).thenReturn(pageDlist);
		assertEquals(pageDlist.toString(), hierarchyDAO.getAllPageDContent(trial).toString());
	}

}
