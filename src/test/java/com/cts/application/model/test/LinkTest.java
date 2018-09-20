package com.cts.application.model.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.cts.application.model.Link;

public class LinkTest {

	Link link=new Link();
	
	@Test
	public void testGetLink_Id() {
		link.setLink_Id(1);
		assertEquals(1,link.getLink_Id());
	}

	@Test
	public void testGetLink_Name() {
		link.setLink_Name("Bank");
		assertEquals("Bank",link.getLink_Name());
	}

	@Test
	public void testGetParent_link_Id() {
		link.setParent_link_Id(1);
		assertEquals(1,link.getParent_link_Id());
	}

}
