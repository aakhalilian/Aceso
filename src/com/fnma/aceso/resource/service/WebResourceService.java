package com.fnma.aceso.resource.service;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fnma.aceso.utilities.LogService;
import com.fnma.aceso.utilities.ServiceAccessor;

@Service
public class WebResourceService {
	private LogService logService;
	private ArrayList<WebResource> webResources = new ArrayList<WebResource>();


	public void addWebResource(WebResource webResource) {
		if (!webResources.contains(webResource)) {
			webResources.add(webResource);
		}
	}

	public ArrayList<WebResource> getWebResources() {
		return webResources;
	}

	public void setWebResources(ArrayList<WebResource> webResources) {
		this.webResources = webResources;
	}

	public WebResource getWebResource(String key) {
		for (WebResource webResource : webResources) {
			if (webResource.getKey().equals(key))
				return webResource;
		}
		return null;
	}

	public ArrayList<WebResource> getWebResourceFor(String context) {
		logService=ServiceAccessor.getLogServiece();
		Logger log = logService.getLog();
		ArrayList<WebResource> resourcesForContext = new ArrayList<WebResource>();
		for (WebResource webResource : webResources) {
			if (webResource.getContext().equals(context))
				resourcesForContext.add(webResource);
		}
		log.info("Get resources for "+context+": "+resourcesForContext.size()+" record has been found");
		return resourcesForContext;
	}

	public String getDispatchersFor(String context) {
		String Dispatchers = "";
		ArrayList<WebResource> resourcesForContext = getWebResourceFor(context);
		for (WebResource webResource : resourcesForContext) {
			Dispatchers += webResource.getDispatchers();
		}
		return Dispatchers;
	}

	public String getDispatchers(String key) {
		return getWebResource(key).getDispatchers();
	}

}
