package com.cts.application.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.application.dao.LinkDao;
import com.cts.application.dao.LinkHierarchyDAO;
import com.cts.application.model.Link;
import com.cts.application.model.Page;
import com.cts.application.service.LinkHierarchyService;
import com.cts.application.service.LinkService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LinkServiceTest {

	@Autowired
	LinkService linkService;

	@MockBean
	private LinkDao linkDao;

	@Test
	public void testGetSubLinks() {
		List<Page> listOfLinks = new ArrayList<Page>();
		listOfLinks.add(new Page(1, "InvestmentBanking", 0));
		listOfLinks.add(new Page(2, "RetailBanking", 0));
		Mockito.when(linkDao.getSubLinks(Mockito.anyString(), Mockito.anyString())).thenReturn(listOfLinks);
		assertEquals(listOfLinks.toString(),
				linkService.getSubLinks(Mockito.anyString(), Mockito.anyString()).toString());
	}

	@Test
	public void testGetLinks() {
		List<Link> listOfLinks = new ArrayList<Link>();
		listOfLinks.add(new Link(1, "InvestmentBanking", 0));
		listOfLinks.add(new Link(2, "RetailBanking", 0));
		Mockito.when(linkDao.getLinks(Mockito.anyString())).thenReturn(listOfLinks);
		assertEquals(listOfLinks.toString(), linkService.getLinks(Mockito.anyString()).toString());
	}

}
