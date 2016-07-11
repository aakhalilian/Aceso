package com.fnma.aceso.resource.service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;

import com.fnma.aceso.utilities.LogService;
import com.fnma.aceso.utilities.ServiceAccessor;
import com.github.sommeri.less4j.Less4jException;

public class ResourceFile {
	
	private String type;
	private String location;
	private long lastCheck;

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
		Logger log = logService.getLog("app.log", this.getClass());
		log.info(servletContext.getRealPath("/WEB-INF/assets/"+location));
		File file= new File(servletContext.getRealPath("/WEB-INF/assets/"+location));
		if(file.exists()){
			log.info("file found");
			try {
				LessService lessService=new LessService();
				lessService.compile(file);
				return "<link rel='stylesheet' type='text/css' href='resources/download/"+location+".css'>";
			} catch (Less4jException e) {
				return "";
			} catch (IOException e) {
				return "";
			}
		}
		return "";
	}

	private String getStyleSheet() {
		try {
			URI uri = new URI(location);
			if(uri.getScheme()==null)
				return "<link rel='stylesheet' type='text/css' href='resources/download/"+location+"'>";
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
				return "<script type='text/javascript' src='resources/download/"+location+"'></script>";
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

}
