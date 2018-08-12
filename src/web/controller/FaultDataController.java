package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.User;
import web.model.UserRepository;

@RequestMapping("/data")
@Controller
public class FaultDataController  extends ComController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping( value = { "index.html" , "main.html" , "list.html" } )
	public String dataList(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		model.addAttribute( "name", name );
		
		return "210_data_list.html";
	}

}