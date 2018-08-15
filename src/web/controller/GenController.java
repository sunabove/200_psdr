package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/gen")
@Controller
public class GenController  extends ComController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping( value = { "index.html" , "main.html" , "about.html" } )
	public String aboute(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		return "511_about.html";
	} 
	
	@RequestMapping( value = { "privacy.html" } )
	public String privacy(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		return "512_privacy.html";
	} 
	
	@RequestMapping( "site_map.html" )
	public String siteMap(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		return "520_site_map.html";
	} 

}