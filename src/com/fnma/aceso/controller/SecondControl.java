package com.fnma.aceso.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fnma.aceso.layout.service.LayoutMap;
import com.fnma.aceso.layout.service.LayoutService;
import com.fnma.aceso.layout.service.WebLink;
import com.fnma.aceso.layout.service.WebPanel;
import com.fnma.aceso.resource.service.WebResourceService;
import com.fnma.aceso.utilities.FileService;
import com.fnma.aceso.utilities.LogService;
import com.fnma.aceso.utilities.MongoService;
import com.fnma.aceso.utilities.Properties;
import com.fnma.aceso.utilities.ServiceAccessor;
import com.fnma.aceso.utilities.XMLService;

@Controller
public class SecondControl extends MainControl {
	

	public SecondControl(Properties properties, ServletContext servletContext, LogService logService,
			XMLService xmlService, MongoService mongoService, LayoutService layoutService, HttpServletRequest request,
			HttpServletRequest response) {
		super(properties, servletContext, logService, xmlService, mongoService, layoutService, request, response);
	}

	@RequestMapping(value = { "/1" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		Logger log = logService.getLog();
		log.info("GET request for /1 is received.");
		model.addAttribute("resourceService", resourceService);
		model.addAttribute("layoutService", layoutService);
				
		return "index";
	}
	
	@RequestMapping(value = { "/3" }, method = RequestMethod.GET)
	public String wilcommaPage(Model model) {
		Logger log = logService.getLog();
		log.info("GET request for /3 is received.");
		
		String name= request.getParameter("name");
		model.addAttribute("resourceService", resourceService);
		model.addAttribute("layoutService", layoutService);
		
		model.addAttribute("message", "holla "+name);
		
		return "index";
	}
	
	private void dolayout(){
		Logger log = logService.getLog();
		LayoutMap layoutMap=new LayoutMap();
		
		WebLink link1=new WebLink();
		WebLink link2=new WebLink();
		
		link1.setHref("google.com");
		link1.setLabel("Google");
		link1.setTitle("link to google");
		link1.setLocation("spmeloc");
		link1.setOrder(525);
		
		link2.setHref("gooooooogle.com");
		link2.setLabel("Gonogle");
		link2.setTitle("link to gonnnnogle");
		link2.setLocation("spmennnnloc");
		link2.setOrder(120);
		
		WebPanel panel1=new WebPanel();
		WebPanel panel2=new WebPanel();
		
		panel1.setLocation("someLoc");
		panel2.setLocation("somsseLoc");
		
		panel1.setTemplate("asdasd/asd/a.vm");
		panel2.setTemplate("asdasd/asd/b.vm");
		
		ArrayList<WebLink> links = new ArrayList<WebLink>();
		ArrayList<WebPanel> panels = new ArrayList<WebPanel>();
		
		links.add(link1);
		links.add(link2);
		panels.add(panel1);
		panels.add(panel2);
		
		layoutMap.setLinks(links);
		layoutMap.setPanels(panels);
		
		
		log.info("Saving layout to the file...");
		try {
			xmlService.toXML(layoutMap, servletContext.getRealPath("/WEB-INF/views/master-layout.xml"));
			log.info("layout saved. ");
		} catch (IOException e) {
			e.printStackTrace();
			log.info("layout resources error.");
			log.error(e.getMessage());
		}
	}

}
