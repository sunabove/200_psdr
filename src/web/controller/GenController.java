package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.UserRepository;

@RequestMapping("/gen")
@Controller
public class GenController  extends ComController {

	private static final long serialVersionUID = -5761040664318060481L;

	public GenController() {
		this.loginRequire = false ; 
	}

	@RequestMapping( value = { "index.html" , "main.html" , "about.html" } )
	public String about( HttpServletRequest request ) { 
		var loginRequire = false ; 
		this.processRequest(request, loginRequire);
		
		return "511_about.html";
	} 
	
	@RequestMapping( value = { "privacy.html" } )
	public String privacy( HttpServletRequest request ) {
		var loginRequire = false ; 
		this.processRequest(request, loginRequire);
		
		return "512_privacy.html";
	} 
	
	@RequestMapping( "site_map.html" )
	public String siteMap( HttpServletRequest request ) { 
		var loginRequire = false ; 
		this.processRequest(request, loginRequire);
		
		return "520_site_map.html";
	} 

}