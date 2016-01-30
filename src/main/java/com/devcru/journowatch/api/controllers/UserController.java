package com.devcru.journowatch.api.controllers;

import com.devcru.journowatch.api.constants.Constants;
import com.devcru.journowatch.api.objects.User;
import com.devcru.journowatch.api.services.UserService;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin( // CORS... it's not just a shitty beer
		origins = {
				// use "https://" when appropriate
				"http://" + Constants.LOCALHOST8080,
				"http://" + Constants.OPENSHIFT
		},
		methods = {
				RequestMethod.GET,
				RequestMethod.POST,
				RequestMethod.PUT,
				RequestMethod.DELETE,
				RequestMethod.OPTIONS // for the browser CORS pre-flight
		},
		allowedHeaders = "*" // TODO: Find the minimum-required headers necessary instead of allowing all
)
@RequestMapping(value = "/user/*")
public class UserController {
	
	@Autowired
	private UserService userServ;
	
	/* 
	 * TODO:
	 * We are beginning work on abstracting the REST API out into its own resource layer
	 * The front-end web-client layer is being developed as a separate project
	 * Therefore, we can get rid of these FTL references soon
	 */
	
	@RequestMapping(value="/", method=RequestMethod.POST)
	@ResponseBody
	public String createUser(@RequestBody User user) {
		
		String username = user.getUsername();
		String email = user.getEmail();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		int role = user.getRole();
		String password = user.getPassword();
		
		// TODO: Remember to remove this debug sysout crap when we're done with it
		
		System.out.println("UC > username: " + username);
		System.out.println("UC > email: " + email);
		System.out.println("UC > firstName: " + firstName);
		System.out.println("UC > lastName: " + lastName);
		System.out.println("UC > role: " + role);
		System.out.println("UC > password: " + password);
		
		boolean status = userServ.createUser(user);
		
		System.out.println("UC > status: " + status);
		
		return status + "";
	}
	
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	@ResponseBody
	public User createUser(@PathVariable("username") String username) {
		
		User user = new User();
		user.setUsername(username);
		
		user = userServ.getUser(user);
		
		return user;
	}
	
	@RequestMapping(value = "/{username}", method=RequestMethod.PUT)
	@ResponseBody
	public String updateUser(@PathVariable("username") String username, @RequestBody User user) {
		System.out.println("PUT user/{username} hit!");
		// Should we first associate the username with the JSON struct?
		// i.e. is there a data integrity risk here?  Analyze further...
		//user.setUsername(username);
		boolean status = userServ.updateUser(user);
		return status + "";
		// ???: return JsonResponse;
	}
	
	@RequestMapping(value = "/{username}", method=RequestMethod.DELETE)
	@ResponseBody
	public String deleteUser(@PathVariable("username") String username) {
		User user = new User();
		user.setUsername(username);
		
		boolean status = userServ.deleteUser(user);
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
    	// TODO: boolean status = userServ.logTheFuckIn(user);
    	// XXX: Still not sure if this method will be necessary....
    	// most likely belongs in Service Class if so, anyway
        return null;
    }

}
