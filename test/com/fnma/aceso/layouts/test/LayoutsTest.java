package com.fnma.aceso.layouts.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.fnma.aceso.layout.service.LayoutService;

public class LayoutsTest {
	
	LayoutService ls=new LayoutService();
	@Before
	public void setUp() throws Exception {
		ls.setLocationString("root.main.up");
	}

	@Test
	public void test() {
		ls.up();
		ls.in("branch1");
		assertEquals("root.main.branch1",ls.getLocationString());
	}

}
