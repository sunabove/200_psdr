package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/data")
@Controller
public class FaultDataController extends ComController {

	private static final long serialVersionUID = 3131679599458841886L;

	public FaultDataController() {
		this.loginRequire = true ;
	}

	@RequestMapping( value = { "index.html" , "main.html" , "list.html" } )
	public String dataList(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
			Model model) { 
		
		String forward = this.processRequest() ; 
		
		if( null != forward ) {
			return forward ; 
		}
		
		model.addAttribute( "name", name );
		
		return "210_data_list.html";
	}

}