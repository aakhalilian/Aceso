//package com.fnma.aceso.controller;
//
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.fnma.aceso.resource.service.WebResourceService;
//import com.fnma.aceso.utilities.LogService;
//import com.fnma.aceso.utilities.MongoService;
//import com.fnma.aceso.utilities.Properties;
//import com.fnma.aceso.utilities.ServiceAccessor;
//import com.fnma.aceso.utilities.XMLService;
//
//@Controller
//public class SecondControl extends MainControl {
//	
//	@Autowired
//	public SecondControl(Properties properties, ServletContext servletContext, LogService logService,
//			XMLService xmlService, MongoService mongoService, HttpServletRequest request) {
//		super(properties, servletContext, logService, xmlService, mongoService, request);
//	}
//	
//	@RequestMapping(value = { "/2/" }, method = RequestMethod.GET)
//	public String welcomePage(Model model) {
//		LogService logService=ServiceAccessor.getLogServiece();
//		Logger log = logService.getLog("app.log", this.getClass());
//		log.info("GET request for / is received.");
//		
//		WebResourceService resourceService=ServiceAccessor.getResourceService();
//		model.addAttribute("resourceService", resourceService);
//			
//		model.addAttribute("message", "by All");
//		
//		return "index2";
//	}
//
//}
