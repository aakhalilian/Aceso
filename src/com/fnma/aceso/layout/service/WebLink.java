package com.fnma.aceso.layout.service;

public class WebLink extends WebItem{
	private String href="";
	private String title="";
	private String label="";
	private boolean blank=false;
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isBlank() {
		return blank;
	}
	public void setBlank(boolean blank) {
		this.blank = blank;
	}
	public String getTarget() {
		if(this.isBlank())
			return "_blank";
		else
			return "_self";
	}
}
