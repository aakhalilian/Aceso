package com.fnma.aceso.resource.service;

public class ResourceFile {
	private String type;
	private String location;
	private String dispatcher;
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
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	public long getLastCheck() {
		return lastCheck;
	}

	public void setLastCheck(long lastCheck) {
		this.lastCheck = lastCheck;
	}

}
