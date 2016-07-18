package com.fnma.aceso.utilities.test;

import static org.junit.Assert.*;

import java.net.URISyntaxException;

import org.junit.Test;

import com.fnma.aceso.utilities.FileService;

public class FileServiceUT {

		
	@Test
	public void test() {
		try {
			int logfiles=FileService.LookFor(".log", "c:/tomcat8/logs").size();
			assertTrue(logfiles>0);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}

}
