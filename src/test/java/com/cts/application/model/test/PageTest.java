package com.cts.application.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cts.application.model.Page;

public class PageTest {

	Page page=new Page();
	
	@Test
	public void testGetPage_A_Id() {
		page.setPage_A_Id(1);
		assertEquals("1",page.getPage_A_Id().toString());
		
	}

	@Test
	public void testGetPage_A_Name() {
		page.setPage_A_Name("Banking");
		assertEquals("Banking", page.getPage_A_Name());
	}

	@Test
	public void testGetParentPageId() {
	page.setParentPageId(1);
	assertEquals("1", page.getParentPageId().toString());
	}

}
