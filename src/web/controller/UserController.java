package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.User;
import web.model.UserList;

@RequestMapping("/user")
@Controller
public class UserController extends ComController {

	private static final long serialVersionUID = 1096136683760111201L;
	
	boolean debug = true ;
	
	public UserController() {
		this.loginRequire = true ; 
	}
	
	@RequestMapping( value = { "index.html" , "main.html" , "regi.html" } )
	public String userRegi( HttpServletRequest request ) { 
		
		String userId 	= request.getParameter( "user_id" );
		String email 	= request.getParameter( "user_email" );
		String passwd 	= request.getParameter( "user_pass" );
		
		String error = null ; 
		
		if( this.isValid( userId ) && this.isValid( email ) && this.isValid( passwd ) ) { 
			User user = userRepository.findByUserId( userId ) ;
			if( null != user ) {
				error = "이미 존재하는 아이디 입니다.";
			} else { 
				user = userService.createUser( request );
				
				if( null != user ) {
					return "redirect:/main/index.html" ;
				}
			}
		} else {
			error = "잘못된 사용자 정보입니다." ;
		}
		
		request.setAttribute( "login_error_msg", error );
		request.setAttribute( "error_msg", error );
		request.setAttribute( "error", error );
		
		return "311_user_regi.html";
	}
	
	@RequestMapping( value = { "manage_role.html", "manage.html" } )
	public String manage( HttpServletRequest request, Pageable pageable ) { 
		var debug = this.debug;
		
		var loginRequire = true ;
		
		String forward = this.processRequest( request , loginRequire ) ;  
		
		if( this.isValid( forward ) ) {
			return forward ; 
		}
		
		String user_id_search = request.getParameter( "user_id_search" );
		
		UserList users = null;
		
		if( isEmpty( user_id_search ) ) { 
			users = this.userRepository.findAllByOrderByUserIdAsc( pageable ); 
		} else {
			users = this.userRepository.findAllByUserIdContainingOrderByUserIdAsc( user_id_search, pageable );
		}
		
		request.setAttribute( "users", users );
		request.setAttribute( "user_id_search", user_id_search );
		
		return "410_manage_user_role.html";
	}
	
	@RequestMapping( value = { "login.html" } )
	public String userLogin( HttpServletRequest request ) {  
		
		return "312_user_login.html";
	}
	
	@RequestMapping( value = { "find_id.html" } )
	public String findId( HttpServletRequest request) { 
		
		var loginRequire = true ;
		
		String forward = this.processRequest( request , loginRequire ) ; 
		
		return "313_user_find_id.html";
	}
	
	@RequestMapping( value = { "info.html" } )
	public String userInfo( HttpServletRequest request ) {
		
		var loginRequire = this.loginRequire ;
		
		String forward = this.processRequest( request, loginRequire ) ; 
		
		if( null != forward ) {
			return forward ; 
		} else {
			User loginUser = this.getLoginUser( request );
			
			userService.saveUserInfo( request , loginUser );
		}
		
		return "314_user_info.html";
	}
	
	@RequestMapping( value = { "logout.html" } )
	public String logout( HttpServletRequest request ) {		
		String id 		= request.getParameter( "user_id" );
		String passwd 	= request.getParameter( "user_pass" ); 
		
		if( this.isValid( id ) && this.isValid( passwd ) ) {
			var loginRequire = true ; 
			this.processRequest(request, loginRequire );
		} else {
			this.setLoginUser( request, null );
			
			var loginRequire = false ;
			this.processRequest(request, loginRequire );
		}

		User loginUser = this.getLoginUser( request ) ;
		
		return "redirect:/main/main.html";
	}

}