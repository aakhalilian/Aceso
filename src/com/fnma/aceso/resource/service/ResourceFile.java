package com.fnma.aceso.resource.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.fnma.aceso.utilities.LogService;
import com.fnma.aceso.utilities.ServiceAccessor;
import com.github.sommeri.less4j.Less4jException;

public class ResourceFile {
	
	private String type;
	private String location;
	private long lastCheck;
	private static final String RESOURCE_URL="/resources/download/";

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDispatcher() {
		String dispatcher="";
		switch(type){
		case "javascript":
		case "JavaScript":
		case "JS":
		case "js":  
			dispatcher=getJavaScript();
        break;
		case "css":  
		case "style":
			dispatcher=getStyleSheet();
        break;
		case "less":  
			dispatcher=getLess();
        break;
		}
		return dispatcher;
	}
	
	private String getLess() {
		try {
			URI uri = new URI(location);
			if(uri.getScheme()==null)
				return getCompiledLess();
			else return "";		
		} catch (URISyntaxException e) {
			return "";
		}	
	}
	
	private String getCompiledLess() {
		LogService logService=ServiceAccessor.getLogServiece();
		ServletContext servletContext=ServiceAccessor.getServletContext();
		Logger log = logService.getLog();
		File file= new File(servletContext.getRealPath("/WEB-INF/assets/"+location));
		File fakeCss= new File(file.getAbsolutePath()+".css");
		if(file.exists()){
			if(file.lastModified()>lastCheck || !fakeCss.exists() ){
				log.info("find "+file.getAbsolutePath()+" to compile");
				try {
					LessService lessService=new LessService();
					lessService.compile(file);
					lastCheck=file.lastModified();
					WebResourceService resourceService=ServiceAccessor.getResourceService();
					ServiceAccessor.setResourceServiceToFile(resourceService);
					return "<link rel='stylesheet' type='text/css' href='"+generateUrl(location)+".css'>";
				} catch (Less4jException e) {
					log.error("error in compile: "+ e.getMessage());
					return "";
				} catch (IOException e) {
					log.error("error in save: "+ e.getMessage());
					return "";
				}
			}
			return "<link rel='stylesheet' type='text/css' href='"+generateUrl(location)+".css'>";
		}
		return "";
	}

	private String getStyleSheet() {
		try {
			URI uri = new URI(location);
			if(uri.getScheme()==null)
				return "<link rel='stylesheet' type='text/css' href='"+generateUrl(location)+"'>";
			else if(uri.getScheme().equals("http") || uri.getScheme().equals("https") || uri.getScheme().equals("ftp"))
				return "<link rel='stylesheet' type='text/css' href='"+location+"'>";
			else return "";
				
			
		} catch (URISyntaxException e) {
			return "";
		}	
		
	}

	private String getJavaScript() {
		try {
			URI uri = new URI(location);
			if(uri.getScheme()==null)
				return "<script type='text/javascript' src='"+generateUrl(location)+"'></script>";
			else if(uri.getScheme().equals("http") || uri.getScheme().equals("https") || uri.getScheme().equals("ftp"))
				return "<script type='text/javascript' src='"+location+"'></script>";
			else
				return ""; 
		} catch (URISyntaxException e) {
			return "";
		}
		
	}
	
	
	public long getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(long lastCheck) {
		this.lastCheck = lastCheck;
	}
	private String generateUrl(String location){
		String baseURL=ServiceAccessor.getBaseUrl();		
		location=baseURL+RESOURCE_URL+location;
		return location;
	}

}
