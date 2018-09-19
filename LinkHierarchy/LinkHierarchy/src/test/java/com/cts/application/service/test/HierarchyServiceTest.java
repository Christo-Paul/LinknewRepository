package com.cts.application.service.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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
	
	PageA p1=new PageA(1,"InvestmentBanking");
	PageB p2=new PageB(1,"CorporateFinance",p1);
	PageC p3=new PageC(1,"Industry_Coverage",p2);
	PageD p4=new PageD(1,"Healthcare",p3);
	
	
	@Test
	public void testGetAllPageAContent() {

		List<PageA> pageAlist=Arrays.asList(p1);
		Mockito.when(hierarchyDao.getAllPageAContent()).thenReturn(pageAlist);
		assertEquals(pageAlist.toString(),hierarchyService.getAllPageAContent().toString());
		
		
	}

	@Test
	public void testGetAllPageBContent() {

		List<PageB> pageBlist=Arrays.asList(p2);
		Mockito.when(hierarchyDao.getAllPageBContent(Mockito.anyString())).thenReturn(pageBlist);
		assertEquals(pageBlist.toString(),hierarchyService.getAllPageBContent(Mockito.anyString()).toString());
		
		
	}

	@Test
	public void testGetAllPageCContent() {
List<PageC> pageClist=Arrays.asList(p3);
		Mockito.when(hierarchyDao.getAllPageCContent(Mockito.anyString())).thenReturn(pageClist);
		assertEquals(pageClist.toString(),hierarchyService.getAllPageCContent(Mockito.anyString()).toString());
	}

	@Test
	public void testGetAllPageDContent() {
List<PageD> pageDlist=Arrays.asList(p4);
		Mockito.when(hierarchyDao.getAllPageDContent(Mockito.anyString())).thenReturn(pageDlist);
		assertEquals(pageDlist.toString(),hierarchyService.getAllPageDContent(Mockito.anyString()).toString());
	}
}
