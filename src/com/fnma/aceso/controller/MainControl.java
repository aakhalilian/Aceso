package com.fnma.aceso.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fnma.aceso.model.Account;
import com.fnma.aceso.model.Teller;
import com.fnma.aceso.model.User;
import com.fnma.aceso.resource.service.ResourceFile;
import com.fnma.aceso.resource.service.WebResource;
import com.fnma.aceso.resource.service.WebResourceService;
import com.fnma.aceso.utilities.LogService;
import com.fnma.aceso.utilities.MongoService;
import com.fnma.aceso.utilities.Properties;
import com.fnma.aceso.utilities.ServiceAccessor;
import com.fnma.aceso.utilities.XMLService;
import com.github.sommeri.less4j.Less4jException;

@Controller
public class MainControl {

	private ServiceAccessor serviceAccessor;
	@Autowired
	private Properties properties;
	@Autowired
	private ServletContext servletContext;
	@Autowired
	private  LogService logService;
	@Autowired
	private XMLService xmlService;
	@Autowired
	private MongoService mongoService;
	private MongoOperations mongoOperations;
	@Autowired
	private WebResourceService resourceService;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		resourceService=resourceInit();
		buildServiceAccessor();
		Logger log = logService.getLog("app.log", this.getClass());
		log.info("GET request for / is received.");
		
		model.addAttribute("resourceService", resourceService);
			
		model.addAttribute("message", "hi all");
//		try {
//			mongoOperations=mongoService.mongoTemplate();
//			Account acc=new Account(5);
//			acc.setBalance(10.0);
//			mongoOperations.save(acc);
//			model.addAttribute("errors", "Done!");
//		} catch (Exception e) {
//			model.addAttribute("errors", "DBerror");
//		}
		
		return "index";
	}
	
	private void buildServiceAccessor(){
		serviceAccessor=new ServiceAccessor();
		serviceAccessor.setLogServiece(logService);
		serviceAccessor.setProperties(properties);
		serviceAccessor.setResourceService(resourceService);
		serviceAccessor.setServletContext(servletContext);
		serviceAccessor.setXmlService(xmlService);
		serviceAccessor.setMongoService(mongoService);
	}
	
	private WebResourceService resourceInit(){
		WebResourceService newResourceService=new WebResourceService();
//		Logger log = logService.getLog("app.log",this.getClass());

		try {
//			log.info("Start processing web resources...");
			newResourceService=(WebResourceService) xmlService.toObject(servletContext.getRealPath("/WEB-INF/resources.xml"));
//			log.info("Web Resourcess process done.");
		} catch (IOException e) {
//			log.info("Web Resourcess process error!" + e.getMessage());
		}
		return newResourceService;
	}
}