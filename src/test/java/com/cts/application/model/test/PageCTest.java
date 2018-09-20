package com.cts.application.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cts.application.model.PageA;
import com.cts.application.model.PageB;
import com.cts.application.model.PageC;

public class PageCTest {

	PageC pagetest=new PageC();
	
	@Test
	public void testSetPage_C_Id() {
		pagetest.setPage_C_Id(1);
		assertEquals(1,pagetest.getPage_C_Id());
	}

	@Test
	public void testSetPage_C_Name() {
		pagetest.setPage_C_Name("Bank");;
		assertEquals("Bank",pagetest.getPage_C_Name());
	}

	@Test
	public void testSetPageB() {
		PageB pageB=new PageB();
		pagetest.setPageB(pageB);
		assertEquals(pageB,pagetest.getPageB());
	}

}
