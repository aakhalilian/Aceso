package com.fnma.aceso.layout.service;

public class WebItem {
	protected Condition condition;
	protected String location;
	protected String key;
	protected int order = 1000;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

}
