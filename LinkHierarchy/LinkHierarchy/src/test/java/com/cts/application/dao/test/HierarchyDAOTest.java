package com.cts.application.dao.test;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.query.Query;

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

	PageA p1 = new PageA(1, "InvestmentBanking");
	PageB p2 = new PageB(1, "CorporateFinance", p1);
	PageC p3 = new PageC(1, "Industry_Coverage", p2);
	PageD p4 = new PageD(1, "Healthcare", p3);

	@Test
	public void testGetAllPageAContent() {

		LinkHierarchyDAO hierarchyDAO = new LinkHierarchyDAO();
		List<PageA> pageAlist = Arrays.asList(p1);
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
		List<PageB> pageBlist = Arrays.asList(p2);
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
		List<PageC> pageClist = Arrays.asList(p3);
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
		List<PageD> pageDlist = Arrays.asList(p4);
		hierarchyDAO.setSessionFactory(this.sessionFactory);
		Mockito.when(this.sessionFactory.getCurrentSession()).thenReturn(this.session);
		Mockito.when(this.session.createQuery(queryPageB)).thenReturn(this.query);
		Mockito.when(this.query.setParameter(1, trial)).thenReturn(query);
		Mockito.when(this.query.list()).thenReturn(pageDlist);
		assertEquals(pageDlist.toString(), hierarchyDAO.getAllPageDContent(trial).toString());
	}

}
