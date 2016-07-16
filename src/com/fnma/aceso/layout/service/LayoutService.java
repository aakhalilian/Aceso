package com.fnma.aceso.layout.service;

import java.util.ArrayList;

public class LayoutService {
	private ArrayList<String> location = new ArrayList<String>();
	private ArrayList<WebLink> links=new ArrayList<WebLink>();
	private ArrayList<WebPanel> panels=new ArrayList<WebPanel>();

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
