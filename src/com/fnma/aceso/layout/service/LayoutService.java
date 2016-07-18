package com.fnma.aceso.layout.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fnma.aceso.resource.service.WebResourceService;
import com.fnma.aceso.utilities.FileService;
import com.fnma.aceso.utilities.ServiceAccessor;
import com.fnma.aceso.utilities.XMLService;

@Service
public class LayoutService {
	private ArrayList<String> location = new ArrayList<String>();
	private ArrayList<LayoutMap> layouts=new ArrayList<LayoutMap>();
	public final static String ROOT="/WEB-INF/views/";
	public final static String TARGET="layout.xml";
	
	public ArrayList<WebLink> getLinks(){
		ArrayList<WebLink> webLinks=new ArrayList<WebLink>();
		String currentLocation=this.getLocationString();
		for(LayoutMap layout : layouts){
			for(WebLink link : layout.getLinks()){
				if(link.getLocation().equals(currentLocation))
					webLinks.add(link);					
			}
		}
		return webLinks;
	}
	
	public ArrayList<WebPanel> getPanels(){
		ArrayList<WebPanel> webPanels=new ArrayList<WebPanel>();
		String currentLocation=this.getLocationString();
		for(LayoutMap layout : layouts){
			for(WebPanel panel : layout.getPanels()){
				if(panel.getLocation().equals(currentLocation))
					webPanels.add(panel);					
			}
		}
		return webPanels;
	}
	
	public void loadMap(){
		Logger log = ServiceAccessor.getLogServiece().getLog();
		log.info("Loading layout files...");
		try {
			ArrayList<String> filePathes=FileService.LookFor(TARGET,ROOT );
			log.info(filePathes.size()+" files recognized");
			for(String filePath : filePathes){
				layouts.add(getMapFromFile(filePath));
			}
			
		} catch (URISyntaxException e) {
			log.info("layout files reading error!");
			log.error(e.getMessage());
		}
	}
	
	private LayoutMap getMapFromFile(String path){
		LayoutMap newLayoutMap=new LayoutMap();
		XMLService xmlService=ServiceAccessor.getXmlService();
		Logger log = ServiceAccessor.getLogServiece().getLog();
		log.info("Parse layout file:"+path);
		try {
			newLayoutMap = (LayoutMap) xmlService.toObject(path);
			log.info("Done parsing layout file.");
			newLayoutMap.setFilePath(path);
		} catch (IOException e) {
			e.printStackTrace();
			log.info("parse layout file error.");
			log.error(e.getMessage());
		}	
		return newLayoutMap;
	}
	public void in(String address) {
		location.add(address);
	}

	public void up() {
		if (location.size() > 0) {
			location.remove(location.size() - 1);
		}
	}
	
	public void up(int cutNo) {
		for(int i=0; i<cutNo; i++){
			this.up();
		}
	}

	public String getLocationString() {
		String result = "";
		for (int i = 0; i < location.size(); i++) {
			result += location.get(i);
			if(i+1<location.size())
				result +=".";
		}
		return result;
	}
	
	public void setLocationString(String address) {
		String[] addresses=address.split("[.]");
		ArrayList<String> location= new ArrayList<String>();
		for(int i=0; i< addresses.length; i++){
			location.add(addresses[i]);
		}
		this.location = location;
	}
	
	public ArrayList<String> getLocation() {
		return location;
	}

	public void setLocation(ArrayList<String> location) {
		this.location = location;
	}
	
	public ArrayList<LayoutMap> getLayouts() {
		return layouts;
	}

	public void setLayouts(ArrayList<LayoutMap> layouts) {
		this.layouts = layouts;
	}


}
