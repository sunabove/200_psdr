package web.controller;

import javax.servlet.http.HttpServletRequest;

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

	@RequestMapping( value = { "index.html" , "main.html" , "down.html", "list.html" } )
	public String dataList( HttpServletRequest request) { 
		
		var loginRequire = true ;
		
		String forward = this.processRequest( request , loginRequire ) ; 
		
		String user_id = request.getParameter( "user_id" );
		
		if( isValid( user_id ) || this.isValid( forward ) ) {
			return forward ; 
		} 
		
		return "210_data_list.html";
	}

}