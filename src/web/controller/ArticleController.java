package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/article")
@Controller
public class ArticleController extends ComController {

	private static final long serialVersionUID = -5704084995590809168L;

	@RequestMapping( value = { "index.html" , "main.html" , "list.html" } )
	public String articleList(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		model.addAttribute( "name", name );
		
		return "530_article_list.html";
	}
	
	@RequestMapping( value = { "view.html" } )
	public String articleView(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		model.addAttribute( "name", name );
		
		return "540_article_view.html";
	}

} 