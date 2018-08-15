package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class IndexController extends ComController {

	private static final long serialVersionUID = -1215549637589312065L;

	@RequestMapping( value = { "/index.html" , "main.html" } )
	public String index() {
		
		var loginRequire = true ;
		
		String forward = this.processRequest( loginRequire ) ; 
		
		if( null != forward ) {
			return "110_main.html";
		} else {
			return "forward:/data/index.html" ; 
		}
	} 
	
	@RequestMapping("/list.html")
	public String list() { 
		
		return "000_index.html";
	} 

}