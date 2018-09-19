package com.cts.application.controller.test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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

	PageA p1 = new PageA(1, "InvestmentBanking");
	PageB p2 = new PageB(1, "CorporateFinance", p1);
	PageC p3 = new PageC(1, "Industry_Coverage", p2);
	PageD p4 = new PageD(1, "Healthcare", p3);

	@Test
	public void testGetAllArticles() throws Exception {
		Link link1 = ControllerLinkBuilder.linkTo(LinkHierarchyController.class).slash(p1.getPage_A_Name())
				.withSelfRel();
		p1.add(link1);

		List<PageA> pageAlist = Arrays.asList(p1);

		Mockito.when(hierarchyService.getAllPageAContent()).thenReturn(pageAlist);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/Banking/").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		System.out.println(result.getResponse());
		String expected = "[{\"page_A_Id\": 1,\"page_A_Name\": \"InvestmentBanking\", \"links\": [{ \"rel\": \"self\",\"href\": \"/Banking/InvestmentBanking\"}]}]";
		// [{"page_A_Id":1,"page_A_Name":"InvestmentBanking","links":[{"rel":"self","href":"/InvestmentBanking"}]}]

		JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
	}

	@Test
	public void testGetPageBcontent() throws Exception {

		Link link1 = ControllerLinkBuilder.linkTo(LinkHierarchyController.class).slash(p2.getPageA().getPage_A_Name())
				.slash(p2.getPage_B_Name()).withSelfRel();
		;
		p2.add(link1);
		List<PageB> pageBlist = Arrays.asList(p2);

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
				.slash(p3.getPageB().getPageA().getPage_A_Name()).slash(p3.getPageB().getPage_B_Name())
				.slash(p3.getPage_C_Name()).withSelfRel();
		p3.add(link1);

		List<PageC> pageClist = Arrays.asList(p3);

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
				.slash(p4.getPageC().getPageB().getPageA().getPage_A_Name())
				.slash(p4.getPageC().getPageB().getPage_B_Name()).slash(p4.getPageC().getPage_C_Name())
				.slash(p4.getPage_D_Name()).withSelfRel();
		p3.add(link1);

		List<PageD> pageDlist = Arrays.asList(p4);

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