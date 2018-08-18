package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/main")
@Controller
public class IndexController extends ComController {

	private static final long serialVersionUID = -1215549637589312065L;

	@RequestMapping( value = { "/index.html" , "main.html" } )
	public String index( HttpServletRequest request ) {
		
		var loginRequire = true ;
		
		String forward = this.processRequest( request, loginRequire ) ; 
		
		if( null != forward ) {
			return "110_main.html";
		} else {
			String user_id = request.getParameter( "user_id" );
			
			if( this.isValid( user_id ) ) {
				return "redirect:/data/index.html" ; 
			}
			
			return "forward:/data/index.html" ; 
		}
	} 
	
	@RequestMapping("/list.html")
	public String list( HttpServletRequest request ) { 
		
		var loginRequire = true ;
		
		this.processRequest( request, loginRequire ) ; 
		
		return "000_index.html";
	} 

}