package com.cts.application.controller.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.application.controller.LinkHierarchyController;
import com.cts.application.model.PageA;
import com.cts.application.model.PageB;
import com.cts.application.model.PageC;
import com.cts.application.model.PageD;
import com.cts.application.service.LinkHierarchyService;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@WebMvcTest(value = LinkHierarchyController.class, secure = false)
public class HierarchyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private LinkHierarchyService hierarchyService;
    
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
	public void testGetAllArticles() throws Exception {
		Link link1 = ControllerLinkBuilder.linkTo(LinkHierarchyController.class).slash(pageA.getPage_A_Name())
				.withSelfRel();
		pageA.add(link1);
		List<PageA> pageAlist = Arrays.asList(pageA);
		Mockito.when(hierarchyService.getAllPageAContent()).thenReturn(pageAlist);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Banking/").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "[{\"page_A_Id\": 1,\"page_A_Name\": \"InvestmentBanking\", \"links\": [{ \"rel\": \"self\",\"href\": \"/Banking/InvestmentBanking\"}]}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetPageBcontent() throws Exception {

		Link link1 = ControllerLinkBuilder.linkTo(LinkHierarchyController.class).slash(pageB.getPageA().getPage_A_Name())
				.slash(pageB.getPage_B_Name()).withSelfRel();
		;
		pageB.add(link1);
		List<PageB> pageBlist = Arrays.asList(pageB);
		Mockito.when(hierarchyService.getAllPageBContent(Mockito.anyString())).thenReturn(pageBlist);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Banking/anystring")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "[{\"page_B_Id\":1,\"page_B_Name\":\"CorporateFinance\",\"pageA\":{\"page_A_Id\":1,\"page_A_Name\":\"InvestmentBanking\",\"links\":[]},\"links\":[{\"rel\":\"self\",\"href\":\"/Banking/InvestmentBanking/CorporateFinance\"}]}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetPageCcontent() throws Exception {

		Link link1 = ControllerLinkBuilder.linkTo(LinkHierarchyController.class)
				.slash(pageC.getPageB().getPageA().getPage_A_Name()).slash(pageC.getPageB().getPage_B_Name())
				.slash(pageC.getPage_C_Name()).withSelfRel();
		pageC.add(link1);
		List<PageC> pageClist = Arrays.asList(pageC);
		Mockito.when(hierarchyService.getAllPageCContent(Mockito.anyString())).thenReturn(pageClist);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Banking/InvestmentBanking/CorporateFinance")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "[{\"page_C_Id\":1,\"page_C_Name\":\"Industry_Coverage\",\"pageB\":{\"page_B_Id\":1,\"page_B_Name\":\"CorporateFinance\",\"pageA\":{\"page_A_Id\":1,\"page_A_Name\":\"InvestmentBanking\",\"links\":[]},\"links\":[]},\"links\":[{\"rel\":\"self\",\"href\":\"/Banking/InvestmentBanking/CorporateFinance/Industry_Coverage\"}]}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void testGetPageDcontent() throws Exception {

		Link link1 = ControllerLinkBuilder.linkTo(LinkHierarchyController.class)
				.slash(pageD.getPageC().getPageB().getPageA().getPage_A_Name())
				.slash(pageD.getPageC().getPageB().getPage_B_Name()).slash(pageD.getPageC().getPage_C_Name())
				.slash(pageD.getPage_D_Name()).withSelfRel();
		pageC.add(link1);
		List<PageD> pageDlist = Arrays.asList(pageD);
		Mockito.when(hierarchyService.getAllPageDContent(Mockito.anyString())).thenReturn(pageDlist);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/Banking/InvestmentBanking/CorporateFinance/Industry_Coverage")
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse());
		String expected = "[{\"page_D_Id\":1,\"page_D_Name\":\"Healthcare\",\"pageC\":{\"page_C_Id\":1,\"page_C_Name\":\"Industry_Coverage\",\"pageB\":{\"page_B_Id\":1,\"page_B_Name\":\"CorporateFinance\",\"pageA\":{\"page_A_Id\":1,\"page_A_Name\":\"InvestmentBanking\",\"links\":[]},\"links\":[]},\"links\":[{\"rel\":\"self\",\"href\":\"/Banking/InvestmentBanking/CorporateFinance/Industry_Coverage/Healthcare\"}]},\"links\":[]}]";
		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);

	}

	@Test
	public void testGetNullcontent() throws Exception {

		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/Banking/InvestmentBanking/CorporateFinance/Industry_Coverage/Healthcare")
				.accept(MediaType.ALL_VALUE);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expected = "No More Further Subdivisions !!!";
		System.out.println(result.getResponse() + "\n " + expected);
		assertTrue(expected.equals(result.getResponse().getContentAsString()));

	}

}