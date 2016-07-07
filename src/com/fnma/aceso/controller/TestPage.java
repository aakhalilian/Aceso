package com.fnma.aceso.controller;

import java.io.File;
import java.io.IOException;

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
import com.fnma.aceso.utilities.LessService;
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
	private XMLService xmlAssist;
	@Autowired
	private LessService lessService;
	@Autowired
	private MongoService mongoService;
	private MongoOperations mongoOperations;

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String welcomePage(Model model) {
		Logger log = logServiece.getLog(propService.get("app.log"), this.getClass());
		log.info("hello");

		User user = new User();
		user.setId(0);
		user.setName("myName");
		model.addAttribute("errors", propService.get("app.log"));
		// try {
		// xmlAssist.toXML(user, "xml/my2.xml");
		// } catch (IOException e) {
		// model.addAttribute("errors", "IOException a");
		// }
		try {
			mongoOperations=mongoService.mongoTemplate();
			Account acc=new Account(5);
			acc.setBalance(10.0);
			mongoOperations.save(acc);
			model.addAttribute("errors", "Done!");
		} catch (Exception e) {
			model.addAttribute("errors", "DBerror");
		}
		
		teller.assignAccount(user, new Account(1));
		teller.assignAccount(user, new Account(3));
		model.addAttribute("accountList", teller.listFor(user));
		return "index";
	}
}