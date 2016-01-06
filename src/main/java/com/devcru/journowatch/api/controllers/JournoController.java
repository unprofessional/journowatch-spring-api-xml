package com.devcru.journowatch.api.controllers;

import static org.springframework.ui.freemarker.FreeMarkerTemplateUtils.processTemplateIntoString;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.devcru.journowatch.api.daoimpl.UserDaoImpl;
import com.devcru.journowatch.api.objects.User;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;


@Controller
@RequestMapping(value="/journo/*")
public class JournoController {
	
	@Autowired
    private UserDaoImpl userDao;
	
	@Autowired
	private Configuration freemarkerConfiguration;
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public @ResponseBody
	String getIndexView() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		System.out.println("MainController > test hit! Returning data...");
		
		User user = new User();
		user.setUsername("regular");
		user.setPassword("password");
		
		return processTemplateIntoString(freemarkerConfiguration.getTemplate("sample.ftl"), new Object());
	}

}
