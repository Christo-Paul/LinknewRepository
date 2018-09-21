package com.cts.application.service.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.application.dao.LinkHierarchyDAO;
import com.cts.application.model.PageA;
import com.cts.application.model.PageB;
import com.cts.application.model.PageC;
import com.cts.application.model.PageD;
import com.cts.application.service.LinkHierarchyService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HierarchyServiceTest {

	@Autowired
	LinkHierarchyService hierarchyService;

	@MockBean
	private LinkHierarchyDAO hierarchyDao;
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

		List<PageA> pageAlist = Arrays.asList(pageA);
		Mockito.when(hierarchyDao.getAllPageAContent()).thenReturn(pageAlist);
		assertEquals(pageAlist.toString(), hierarchyService.getAllPageAContent().toString());

	}

	@Test
	public void testGetAllPageBContent() {

		List<PageB> pageBlist = Arrays.asList(pageB);
		Mockito.when(hierarchyDao.getAllPageBContent(Mockito.anyString())).thenReturn(pageBlist);
		assertEquals(pageBlist.toString(), hierarchyService.getAllPageBContent(Mockito.anyString()).toString());

	}

	@Test
	public void testGetAllPageCContent() {
		List<PageC> pageClist = Arrays.asList(pageC);
		Mockito.when(hierarchyDao.getAllPageCContent(Mockito.anyString())).thenReturn(pageClist);
		assertEquals(pageClist.toString(), hierarchyService.getAllPageCContent(Mockito.anyString()).toString());
	}

	@Test
	public void testGetAllPageDContent() {
		List<PageD> pageDlist = Arrays.asList(pageD);
		Mockito.when(hierarchyDao.getAllPageDContent(Mockito.anyString())).thenReturn(pageDlist);
		assertEquals(pageDlist.toString(), hierarchyService.getAllPageDContent(Mockito.anyString()).toString());
	}
}
