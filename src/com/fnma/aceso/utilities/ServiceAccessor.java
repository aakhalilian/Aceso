package com.fnma.aceso.utilities;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.data.mongodb.core.MongoOperations;

import com.fnma.aceso.resource.service.WebResourceService;

public class ServiceAccessor {
	protected static Properties properties;
	protected static ServletContext servletContext;
	protected static HttpServletRequest servletRequest;
	protected static HttpServletRequest servletResponse;
	protected static LogService logService;
	protected static XMLService xmlService;
	protected static MongoService mongoService;
	protected static MongoOperations mongoOperations;
	protected static WebResourceService resourceService;
	protected static final String RESOURCE_XML = "/WEB-INF/resources.xml";

	public static String getBaseUrl() {
		String baseUrl = null;

		if ((servletRequest.getServerPort() == 80) || (servletRequest.getServerPort() == 443))
			baseUrl = servletRequest.getScheme() + "://" + servletRequest.getServerName()
					+ servletRequest.getContextPath();
		else
			baseUrl = servletRequest.getScheme() + "://" + servletRequest.getServerName() + ":"
					+ servletRequest.getServerPort() + servletRequest.getContextPath();
		return baseUrl;
	}

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
		WebResourceService newResourceService = new WebResourceService();
		Logger log = logService.getLog();
		log.info("Trying to get resources...");
		try {
			newResourceService = (WebResourceService) xmlService.toObject(servletContext.getRealPath(RESOURCE_XML));
			log.info("Resources loaded. " + newResourceService.getWebResources().size() + " Web Resources Found");
		} catch (IOException e) {
			e.printStackTrace();
			log.info("Load resources error.");
			log.error(e.getMessage());
		}
		resourceService = newResourceService;
		return newResourceService;
	}

	public static WebResourceService getResourceService() {
		return resourceService;
	}

	public static void setResourceServiceToFile(WebResourceService resourceService) {
		Logger log = logService.getLog();
		log.info("Saving resources to the file...");
		try {
			xmlService.toXML(resourceService, servletContext.getRealPath(RESOURCE_XML));
			log.info("Resources saved. " + servletContext.getRealPath(RESOURCE_XML));
		} catch (IOException e) {
			e.printStackTrace();
			log.info("Save resources error.");
			log.error(e.getMessage());
		}
		ServiceAccessor.resourceService = resourceService;
	}

	public static void setResourceService(WebResourceService resourceService) {
		ServiceAccessor.resourceService = resourceService;
	}

	public static MongoOperations getMongoOperations() {
		return mongoOperations;
	}
	
	public static MongoOperations initMongoOperations() {
		Logger log = logService.getLog();
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
		ServiceAccessor.mongoOperations = newMongoOperations;
		return newMongoOperations;
	}
	
	

	public static void setMongoOperations(MongoOperations mongoOperations) {
		ServiceAccessor.mongoOperations = mongoOperations;
	}

	public static HttpServletRequest getServletRequest() {
		return servletRequest;
	}

	public static void setServletRequest(HttpServletRequest servletRequest) {
		ServiceAccessor.servletRequest = servletRequest;
	}

	public static HttpServletRequest getServletResponse() {
		return servletResponse;
	}

	public static void setServletResponse(HttpServletRequest servletResponse) {
		ServiceAccessor.servletResponse = servletResponse;
	}
}
