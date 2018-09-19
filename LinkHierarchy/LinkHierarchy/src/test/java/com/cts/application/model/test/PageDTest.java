package com.cts.application.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cts.application.model.PageB;
import com.cts.application.model.PageC;
import com.cts.application.model.PageD;

public class PageDTest {

	PageD pagetest=new PageD();
	
	@Test
	public void testSetPage_D_Id() {
		pagetest.setPage_D_Id(1);
		assertEquals(1,pagetest.getPage_D_Id());
	}

	@Test
	public void testSetPage_D_Name() {
		pagetest.setPage_D_Name("Bank");;
		assertEquals("Bank",pagetest.getPage_D_Name());
	}

	@Test
	public void testSetPageC() {
		PageC pageC=new PageC();
		pagetest.setPageC(pageC);
		assertEquals(pageC,pagetest.getPageC());
	}

}
