package com.fnma.aceso.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fnma.aceso.model.Account;
import com.fnma.aceso.model.Teller;
import com.fnma.aceso.model.User;
import com.fnma.aceso.resource.service.ResourceFile;
import com.fnma.aceso.utilities.LogServiece;
import com.fnma.aceso.utilities.MongoService;
import com.fnma.aceso.utilities.PropService;
import com.fnma.aceso.utilities.XMLService;
import com.github.sommeri.less4j.Less4jException;

@Controller
public class TestPage {

	@Autowired
	private Teller teller;
	@Autowired
	private PropService propService;
	// @Autowired private ServletContext servletContext;
	@Autowired
	private LogServiece logServiece;
	@Autowired
	private XMLService xmlService;
	@Autowired
	private MongoService mongoService;
	private MongoOperations mongoOperations;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		Logger log = logServiece.getLog(propService.get("app.log"), this.getClass());
		log.info("hello");
		
		ResourceFile file=new ResourceFile();
		file.setLocation("some/location/aa");
		file.setType("js");
		try {
			xmlService.toXML(file, propService.get("app.xml"));
		} catch (IOException e) {
			model.addAttribute("errors", "xml error");
		}
		
		
//		try {
//			mongoOperations=mongoService.mongoTemplate();
//			Account acc=new Account(5);
//			acc.setBalance(10.0);
//			mongoOperations.save(acc);
//			model.addAttribute("errors", "Done!");
//		} catch (Exception e) {
//			model.addAttribute("errors", "DBerror");
//		}
		
		return "index";
	}
}