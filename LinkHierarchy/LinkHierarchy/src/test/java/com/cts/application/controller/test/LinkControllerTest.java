package com.cts.application.controller.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.application.controller.LinkController;
import com.cts.application.controller.LinkHierarchyController;
import com.cts.application.model.Link;
import com.cts.application.model.Page;
import com.cts.application.model.PageA;
import com.cts.application.model.PageB;
import com.cts.application.model.PageC;
import com.cts.application.model.PageD;
import com.cts.application.service.LinkHierarchyService;
import com.cts.application.service.LinkService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LinkController.class, secure = false)
public class LinkControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LinkService hierarchyService;
/*
	PageA p1 = new PageA(1, "InvestmentBanking");
	PageB p2 = new PageB(1, "CorporateFinance", p1);
	PageC p3 = new PageC(1, "Industry_Coverage", p2);
	PageD p4 = new PageD(1, "Healthcare", p3);
*/
	@Test
	public void testGetLinks() throws Exception {
	
		List<Link> listOfLinks = new ArrayList<Link>();
		listOfLinks.add(new Link(1,"InvestmentBanking",0));
		listOfLinks.add(new Link(2,"RetailBanking",0));

		Mockito.when(hierarchyService.getLinks(Mockito.anyString())).thenReturn(listOfLinks);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/DynamicLinks/Banking").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"link_Id\":1,\"link_Name\":\"InvestmentBanking\",\"parent_link_Id\":0},{\"link_Id\":2,\"link_Name\":\"RetailBanking\",\"parent_link_Id\":0}]";
	

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetLinkRoot() throws Exception {
	
		List<Page> listOfLinks = new ArrayList<Page>();
		listOfLinks.add(new Page(1,"InvestmentBanking",0));
		listOfLinks.add(new Page(2,"RetailBanking",0));
		
		Mockito.when(hierarchyService.getSubLinks(Mockito.anyString(),Mockito.anyString())).thenReturn(listOfLinks);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/staticlinks").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"page_A_Id\":1,\"page_A_Name\":\"InvestmentBanking\",\"parentPageId\":0},{\"page_A_Id\":2,\"page_A_Name\":\"RetailBanking\",\"parentPageId\":0}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetSubLinkLevel1() throws Exception {
	
		List<Page> listOfLinks = new ArrayList<Page>();
		listOfLinks.add(new Page(1,"CorporateFinance",0));
		listOfLinks.add(new Page(2,"CapitalMarket",0));
		
		Mockito.when(hierarchyService.getSubLinks(Mockito.anyString(),Mockito.anyString())).thenReturn(listOfLinks);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/staticlinks/InvestmentBanking").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"page_A_Id\":1,\"page_A_Name\":\"CorporateFinance\",\"parentPageId\":0},{\"page_A_Id\":2,\"page_A_Name\":\"CapitalMarket\",\"parentPageId\":0}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}
	
	@Test
	public void testGetSubLinkLevel2() throws Exception {
	
		List<Page> listOfLinks = new ArrayList<Page>();
		listOfLinks.add(new Page(1,"Industry_Coverage",0));
		listOfLinks.add(new Page(2,"Merger&Acquisition",0));
		
		Mockito.when(hierarchyService.getSubLinks(Mockito.anyString(),Mockito.anyString())).thenReturn(listOfLinks);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/staticlinks/InvestmentBanking/CorporateFinance").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"page_A_Id\":1,\"page_A_Name\":\"Industry_Coverage\",\"parentPageId\":0},{\"page_A_Id\":2,\"page_A_Name\":\"Merger&Acquisition\",\"parentPageId\":0}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetSubLinkLevel3() throws Exception {
	
		List<Page> listOfLinks = new ArrayList<Page>();
		listOfLinks.add(new Page(1,"Healthcare",0));
		listOfLinks.add(new Page(2,"RealEstate",0));
		
		Mockito.when(hierarchyService.getSubLinks(Mockito.anyString(),Mockito.anyString())).thenReturn(listOfLinks);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/staticlinks/InvestmentBanking/CorporateFinance/Industry_Coverage").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"page_A_Id\":1,\"page_A_Name\":\"Healthcare\",\"parentPageId\":0},{\"page_A_Id\":2,\"page_A_Name\":\"RealEstate\",\"parentPageId\":0}]";
		
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

}
