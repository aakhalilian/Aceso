package com.fnma.aceso.layout.service;

import java.util.ArrayList;

public class LayoutMap {
	private ArrayList<WebLink> links = new ArrayList<WebLink>();
	private ArrayList<WebPanel> panels = new ArrayList<WebPanel>();
	private String filePath = "";

	public ArrayList<WebPanel> getPanels() {
		return panels;
	}

	public void setPanels(ArrayList<WebPanel> panels) {
		this.panels = panels;
	}

	public ArrayList<WebLink> getLinks() {
		return links;
	}

	public void setLinks(ArrayList<WebLink> links) {
		this.links = links;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}
