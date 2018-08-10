package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.User;
import web.model.UserRepository;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping( value = { "index.html" , "main.html" , "regi.html" } )
	public String userRegi(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		model.addAttribute( "name", name );
		
		return "311_user_regi.html";
	}
	
	@RequestMapping( value = { "manage_role.html" } )
	public String manageRole(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		model.addAttribute( "name", name );
		
		return "410_manage_user_role.html";
	}

}