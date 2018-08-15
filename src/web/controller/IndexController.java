package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/main")
@Controller
public class IndexController extends ComController {

	private static final long serialVersionUID = -1215549637589312065L;

	@RequestMapping( value = { "/index.html" , "main.html" } )
	public String index(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) {
		return "110_main.html";
	} 
	
	@RequestMapping("/list.html")
	public String list(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		return "000_index.html";
	} 

}