package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.User;
import web.model.UserRepository;

@Controller
public class GreetingController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/greeting")
	public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		User user = userRepository.findByName("Bob");
		if( null == user ) { 
			user = new User("Bob", 38);
	        //save user, verify has ID value after save
	        this.userRepository.save( user );
	        
	        user = userRepository.findByName("Bob");
		}
        
		model.addAttribute("name", name);
		return "greeting.html";
	}

	@GetMapping("sample/sample_01")
	public String sample_01() {
		return "sample_01.html";
	}

}