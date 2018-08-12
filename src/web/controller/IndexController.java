package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.User;
import web.model.UserRepository;

@RequestMapping("/main")
@Controller
public class IndexController extends ComController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping( value = { "/index.html" , "main.html" } )
	public String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		
		User user = userRepository.findByName("Bob");
		
		if( null == user ) { 
			user = new User("Bob", 38);
	        //save user, verify has ID value after save
	        this.userRepository.save( user );
	        
	        user = userRepository.findByName("Bob");
		}
        
		model.addAttribute( "user", user );
		
		return "110_main.html";
	} 
	
	@RequestMapping("/list.html")
	public String list(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		return "000_index.html";
	} 

}