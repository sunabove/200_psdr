package web.model;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import web.WebObject;
import web.spring.Application;

//@Service
//@Transactional
@Log4j

public class UserService extends WebObject {

	private static final long serialVersionUID = 2420319221881431764L;
	
	@Autowired public UserRepository userRepository;

	public UserService() {

	}

	public User getLoginUser( HttpServletRequest request ) {
		
		var debug = true ; 
		
		/*
		
		if( true ) { 
			User rootUser = userRepository.findById( "procom" );
			
			if (null == rootUser) {
				User newUser = new User( 1, "12345678" );
				this.userRepository.save( newUser );
	
				rootUser = userRepository.findById( "procom" );
			}
		}
		
		String id 		= request.getParameter( "user_id" );
		String passwd 	= request.getParameter( "user_pass" );
		
		User loginUser = null;
		
		if( this.isEmpty( id ) ) {
			if( debug ) log.info( "loginUse id is empty." );
		} else if( this.isEmpty( passwd ) ) {
			if( debug ) log.info( "loginUse password is empty." );
		} else if( this.isValid( id ) && this.isValid( passwd ) ) { 
			loginUser = userRepository.findByIdAndPasswd(id, passwd) ;
			
			if( debug ) log.info( "loginUser by id and passwd = " + loginUser );
			
			if( null == loginUser ) {
				loginUser = userRepository.findByEmailAndPasswd(id, passwd);
				
				if( debug ) log.info( "loginUser by email and passwd = " + loginUser );
			}
		}
		
		return loginUser ; 
		
		*/
		
		return null;
	}

}