package com.fnma.aceso.controller;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.fnma.aceso.resource.service.WebResourceService;
import com.fnma.aceso.utilities.LogService;
import com.fnma.aceso.utilities.MongoService;
import com.fnma.aceso.utilities.Properties;
import com.fnma.aceso.utilities.ServiceAccessor;
import com.fnma.aceso.utilities.XMLService;

@Controller
public class MainControl {
	
	private final Properties properties;
	private final ServletContext servletContext;
	private final LogService logService;
	private final XMLService xmlService;
	private final MongoService mongoService;
	private WebResourceService resourceService;
	private MongoOperations mongoOperations;
	
	@Autowired
	public MainControl(Properties properties,ServletContext servletContext,LogService logService,
			XMLService xmlService,MongoService mongoService) {
		this.properties=properties;
		ServiceAccessor.setProperties(properties);

		this.servletContext=servletContext;
		ServiceAccessor.setServletContext(servletContext);

		this.logService=logService;
		ServiceAccessor.setLogServiece(logService);

		this.xmlService=xmlService;
		ServiceAccessor.setXmlService(xmlService);

		this.mongoService=mongoService;
		ServiceAccessor.setMongoService(mongoService);

		this.resourceService=ServiceAccessor.getResourceServiceFromFile();
		
		this.mongoOperations=connectMongo();
		ServiceAccessor.setMongoOperations(mongoOperations);
		
	}
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		Logger log = logService.getLog("app.log", this.getClass());
		log.info("GET request for / is received.");
		
		model.addAttribute("resourceService", resourceService);
			
		model.addAttribute("message", "hi all");
		
		return "index";
	}
	
	private MongoOperations connectMongo(){
		Logger log = logService.getLog("app.log", this.getClass());
		MongoOperations newMongoOperations=null;
		try {
			log.info("Trying to initialize MongoDB connection...");
			newMongoOperations=mongoService.mongoTemplate();
			log.info("MongoDB is connected.");
		} catch (Exception e) {
			e.printStackTrace();
			log.info("MongoDB connection error.");
			log.error(e.getMessage());
		}
		return newMongoOperations;
	}
}