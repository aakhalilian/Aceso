package com.fnma.aceso.resource.service;

import java.util.ArrayList;
import org.springframework.stereotype.Service;


@Service
public class WebResourceService {

	private ArrayList<WebResource> webResources=new ArrayList<WebResource>();
	
	public void addWebResource(WebResource webResource){
		if(!webResources.contains(webResource)){
			webResources.add(webResource);
		}
	}
	public ArrayList<WebResource> getWebResources() {
		return webResources;
	}
	public void setWebResources(ArrayList<WebResource> webResources) {
		this.webResources = webResources;
	}
	public WebResource getWebResource(String key){
		for(WebResource webResource : webResources){
			if(webResource.getKey().equals(key))
				return webResource;
		}
		return null;
	}
	public ArrayList<WebResource> getWebResourceFor(String context){
		ArrayList<WebResource> resourcesForContext= new ArrayList<WebResource>();
		for(WebResource webResource : webResources){
			if(webResource.getContext().equals(context))
				resourcesForContext.add(webResource);
		}
		return resourcesForContext;
	}
	public String getDispatchersFor(String context){
		String Dispatchers="";
		ArrayList<WebResource> resourcesForContext=getWebResourceFor(context);
		for(WebResource webResource : resourcesForContext){
			Dispatchers+=webResource.getDispatchers();
		}		
		return Dispatchers;
	}
	public String getDispatchers(String key){
		return getWebResource(key).getDispatchers();
	}
	
}
