package com.fnma.aceso.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
public class SecondControl extends MainControl {
	
	
	

	public SecondControl(Properties properties, ServletContext servletContext, LogService logService,
			XMLService xmlService, MongoService mongoService, HttpServletRequest request, HttpServletRequest response) {
		super(properties, servletContext, logService, xmlService, mongoService, request, response);
	}

	@RequestMapping(value = { "/1" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		Logger log = logService.getLog();
		log.info("GET request for /1 is received.");
		
		String name= request.getParameter("name");
		
		model.addAttribute("resourceService", resourceService);
			
		model.addAttribute("message", "hi "+name);
		
		return "index";
	}
	
	@RequestMapping(value = { "/3" }, method = RequestMethod.GET)
	public String wilcommaPage(Model model) {
		Logger log = logService.getLog();
		log.info("GET request for /3 is received.");
		
		String name= request.getParameter("name");
		model.addAttribute("resourceService", resourceService);
			
		model.addAttribute("message", "holla "+name);
		
		return "index";
	}

}
