package com.fnma.aceso.utilities;

import java.io.IOException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;

import com.fnma.aceso.resource.service.WebResourceService;

public class ServiceAccessor {
	private static Properties properties;
	private static ServletContext servletContext;
	private static LogService logService;
	private static XMLService xmlService;
	private static MongoService mongoService;
	private static MongoOperations mongoOperations;
	private static WebResourceService resourceService;
	private static final String RESOURCE_XML="/WEB-INF/resources.xml";
	
	public static Properties getProperties() {
		return properties;
	}
	public static void setProperties(Properties properties) {
		ServiceAccessor.properties = properties;
	}
	public static ServletContext getServletContext() {
		return servletContext;
	}
	public static void setServletContext(ServletContext servletContext) {
		ServiceAccessor.servletContext = servletContext;
	}
	public static LogService getLogServiece() {
		return logService;
	}
	public static void setLogServiece(LogService logService) {
		ServiceAccessor.logService = logService;
	}
	public static XMLService getXmlService() {
		return xmlService;
	}
	public static void setXmlService(XMLService xmlService) {
		ServiceAccessor.xmlService = xmlService;
	}
	public static MongoService getMongoService() {
		return mongoService;
	}
	public static void setMongoService(MongoService mongoService) {
		ServiceAccessor.mongoService = mongoService;
	}
	public static WebResourceService getResourceServiceFromFile() {
		WebResourceService newResourceService=new WebResourceService();
		Logger log = logService.getLog("app.log", ServiceAccessor.class);
		log.info("Trying to get resources...");
		try {
			newResourceService=(WebResourceService) xmlService.toObject(servletContext.getRealPath(RESOURCE_XML));
			log.info("Resources loaded. "+ newResourceService.getWebResources().size()+" Web Resources Found");
		} catch (IOException e) {
			e.printStackTrace();
			log.info("Load resources error.");
			log.error(e.getMessage());
		}
		resourceService=newResourceService;
		return newResourceService;
	}
	public static WebResourceService getResourceService() {
		return resourceService;
	}
	public static void setResourceServiceToFile(WebResourceService resourceService) {
		ServiceAccessor.resourceService = resourceService;
	}
	public static void setResourceService(WebResourceService resourceService) {
		ServiceAccessor.resourceService = resourceService;
	}
	public static MongoOperations getMongoOperations() {
		return mongoOperations;
	}
	public static void setMongoOperations(MongoOperations mongoOperations) {
		ServiceAccessor.mongoOperations = mongoOperations;
	}
}
