package com.fnma.aceso.layout.service;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fnma.aceso.utilities.FileService;
import com.fnma.aceso.utilities.ServiceAccessor;

@Service
public class LayoutService {
	private ArrayList<String> location = new ArrayList<String>();
	private HashMap<String,LayoutMap> layouts=new HashMap<String,LayoutMap>();
	private final static String ROOT="/WEB-INF/views/";
	private final static String TARGET="layout.xml";
	
	public void loadMap(){
		Logger log = ServiceAccessor.getLogServiece().getLog();
		log.info("Loading layout files...");
		try {
			ArrayList<String> filesLocation=FileService.LookFor(TARGET,ROOT );
			log.info(filesLocation.size()+" files recognized");
		} catch (URISyntaxException e) {
			log.info("layout files reading error!");
			log.error(e.getMessage());
		}
	}
	public void in(String address) {
		location.add(address);
	}

	public void up() {
		if (location.size() > 1) {
			location.remove(location.size() - 1);
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

}
