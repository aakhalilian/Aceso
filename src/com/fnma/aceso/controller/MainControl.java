package com.fnma.aceso.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fnma.aceso.layout.service.LayoutService;
import com.fnma.aceso.resource.service.WebResourceService;
import com.fnma.aceso.utilities.LogService;
import com.fnma.aceso.utilities.MongoService;
import com.fnma.aceso.utilities.Properties;
import com.fnma.aceso.utilities.ServiceAccessor;
import com.fnma.aceso.utilities.XMLService;

public class MainControl {
	
	protected final Properties properties;
	protected final LogService logService;
	protected final XMLService xmlService;
	protected final MongoService mongoService;
	protected WebResourceService resourceService;
	protected MongoOperations mongoOperations;
	protected LayoutService layoutService;
	protected ServletContext servletContext;
	protected HttpServletRequest request;
	protected HttpServletRequest response;

	
	@Autowired
	public MainControl(Properties properties,ServletContext servletContext,LogService logService,
			XMLService xmlService,MongoService mongoService,LayoutService layoutService, 
			HttpServletRequest request ,HttpServletRequest response) {
		this.properties=properties;
		ServiceAccessor.setProperties(properties);

		this.servletContext=servletContext;
		ServiceAccessor.setServletContext(servletContext);
		
		this.request=request;
		ServiceAccessor.setServletRequest(request);
		
		this.response=response;
		ServiceAccessor.setServletResponse(response);

		this.logService=logService;
		logService.makeLog("app.log", this.getClass());
		ServiceAccessor.setLogServiece(logService);

		this.xmlService=xmlService;
		ServiceAccessor.setXmlService(xmlService);

		this.mongoService=mongoService;
		ServiceAccessor.setMongoService(mongoService);

		this.resourceService=ServiceAccessor.getResourceServiceFromFile();
		this.layoutService=layoutService;
		this.layoutService.loadMap();
		
		this.mongoOperations=ServiceAccessor.initMongoOperations();
		
	}
	
}