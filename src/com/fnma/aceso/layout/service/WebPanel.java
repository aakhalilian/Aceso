package com.fnma.aceso.layout.service;

public class WebPanel extends WebItem {
	private String template="";

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}
	
	public String path(){
		return LayoutService.ROOT+template;	
	}
}
