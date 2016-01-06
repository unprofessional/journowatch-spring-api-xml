package com.devcru.journowatch.api.controllers;

import com.devcru.journowatch.api.daoimpl.UserDaoImpl;
import com.devcru.journowatch.api.objects.User;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.ui.freemarker.FreeMarkerTemplateUtils.processTemplateIntoString;

import java.io.IOException;

/**
 * Created by Monitored on 12/25/2015.
 * User Controller Class
 */

@Controller
@RequestMapping(value = "/*")
public class UserController {

	@Autowired
    private UserDaoImpl userDao;
	
	@Autowired
	private Configuration freemarkerConfiguration;
	
	/* 
	 * Sample landing page thing
	 * Note that this is not REST by any means
	 * This is a full-fledged MVC application
	 */
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public @ResponseBody
	String getIndexView() throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		System.out.println("MainController > test hit! Returning data...");
		
		User user = new User();
		user.setUsername("regular");
		user.setPassword("password");
		
		return processTemplateIntoString(freemarkerConfiguration.getTemplate("sample.ftl"), new Object());
	}
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestBody User user) {
		
		boolean status = userDao.addUser(user);
		
		return status + "";
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	@ResponseBody
	public User createUser(@PathVariable("username") String username) {
		
		User user = new User();
		user.setUsername(username);
		
		user = userDao.getUserByUsername(user);
		
		//user.setUuid(userDao.getUuid(username));
		
		return user;
	}
	
	@RequestMapping(value = "/{username}", method=RequestMethod.PUT)
	@ResponseBody
	public String updateUser(@PathVariable("username") String username, @RequestBody User user) {
		
		// Should we first associate the username with the JSON struct?
		// i.e. is there a data integrity risk here?  Analyze further...
		//user.setUsername(username);
		
		boolean status = userDao.updateUser(user);
		
		return status + "";
		// ???: return JsonResponse;
	}
	
	@RequestMapping(value = "/{username}", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable("username") String username) {
		User user = new User();
		user.setUsername(username);
		
		boolean status = userDao.deleteUser(user);
		
		return status + "";
		// ??? return JsonResponse;
	}
    
    @ResponseStatus(value=HttpStatus.OK)
    // TODO: Test this to see if this gets sent along with a successful 200 OK
    public String okay() {
    	System.out.println(">>>>>>>>>>>>>>>: OKAY!");
    	return "HPOASGBJHSJHDFGGJHSD";
    }
    
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    public String notFound() {
    	System.out.println(">>>>>>>>>>>>>>>: NOT FOUND!");
    	return "Yotsuba!!";    	
    }
    
    @RequestMapping(value="login", method=RequestMethod.POST)
    public static Object logTheFuckIn(User user) {

        // This is where we would call in a UserDAOImpl and execute the login method
        return null;
    }

}
