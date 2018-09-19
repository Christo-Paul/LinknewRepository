package com.cts.application.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cts.application.model.PageA;

public class PageATest {

	PageA pagetest=new PageA();

	@Test
	public void testGetPage_A_Id() {
		pagetest.setPage_A_Id(1);
		assertEquals(1,pagetest.getPage_A_Id());
	}


	@Test
	public void testGetPage_A_Name() {
		pagetest.setPage_A_Name("Bank");;
		assertEquals("Bank",pagetest.getPage_A_Name());
	}

}
