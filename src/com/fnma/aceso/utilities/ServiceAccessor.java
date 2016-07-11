package com.fnma.aceso.utilities;

import javax.servlet.ServletContext;


import com.fnma.aceso.resource.service.WebResourceService;

public class ServiceAccessor {
	private static Properties properties;
	private static ServletContext servletContext;
	private static LogService logService;
	private static XMLService xmlService;
	private static MongoService mongoService;
	private static WebResourceService resourceService;
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
	public static WebResourceService getResourceService() {
		return resourceService;
	}
	public static void setResourceService(WebResourceService resourceService) {
		ServiceAccessor.resourceService = resourceService;
	}
}
