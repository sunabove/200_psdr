package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.User;
import web.model.UserRepository;

@RequestMapping("/sys")
@Controller
public class SysController extends ComController {

	@Autowired
	private UserRepository userRepository;

	@RequestMapping( value = { "/index.html" , "main.html" , "monitor" } )
	public String monitor(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		return "420_sys_monitor.html";
	} 
	
	@RequestMapping("/user_stat.html")
	public String userStat(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		return "430_user_sys_stat.html";
	} 
	
	@RequestMapping("/setting.html")
	public String setting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		return "440_sys_setting.html";
	} 

}