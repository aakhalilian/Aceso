package com.fnma.aceso.resource.service;

import java.util.ArrayList;

public class WebResource {
	private String context;
	private String key;
	private ArrayList<ResourceFile> resources;

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public ArrayList<ResourceFile> getResources() {
		return resources;
	}

	public void setResources(ArrayList<ResourceFile> resources) {
		this.resources = resources;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
