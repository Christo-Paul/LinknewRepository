package com.cts.application.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cts.application.model.PageA;
import com.cts.application.model.PageB;

public class PageBTest {

	PageB pagetest=new PageB();

	@Test
	public void testSetPage_B_Id() {
		pagetest.setPage_B_Id(1);
		assertEquals(1,pagetest.getPage_B_Id());
	}


	@Test
	public void testSetPage_B_Name() {
		pagetest.setPage_B_Name("Bank");;
		assertEquals("Bank",pagetest.getPage_B_Name());
	}
	
	@Test
	public void testSetPageA() {
		PageA pageA=new PageA();
		pagetest.setPageA(pageA);
		assertEquals(pageA,pagetest.getPageA());
		
		
	
	}

}
