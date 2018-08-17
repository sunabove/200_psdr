package web.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import web.WebObject;

@Service
@Transactional
@Log4j

public class UserService extends CommonService {

	private static final long serialVersionUID = 2420319221881431764L; 

	public UserService() {

	}
	
	public User saveUserInfo( HttpServletRequest request , User user ) {
		String email 	= request.getParameter( "user_email" );
		String passwd 	= request.getParameter( "user_pass" );
		
		boolean updated = false ;
		
		if( this.isValid( email ) && ! email.equals( user.email ) ) {
			user.email = email ; 
			
			updated = true ; 
		}
		
		if( this.isValid( passwd ) ) {
			user.passwd = passwd ; 
			
			updated = true ; 
		}
		
		if( updated ) {
			user = this.userRepository.save( user );
		}
		
		return user ;
	}

	public User getLoginUser( HttpServletRequest request ) {
		
		var debug = true ; 
		
		if( true ) { 
			User rootUser = userRepository.findByUserId( "procom" );
			
			if (null == rootUser) {
				Code userRoleAdmin  = this.getCode( "USER-ROLE-ADMIN", "관리자" , 0 );
				Code userRoleNormal = this.getCode( "USER-ROLE-NORMAL", "사용자", 1 );
				
				User newUser = new User( "procom", "12345678" , userRoleAdmin ); 
				newUser.email = "procom@procom.co.kr";
				
				this.userRepository.save( newUser );
	
				rootUser = userRepository.findByUserId( "procom" );
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
			loginUser = userRepository.findByUserIdAndPasswd(id, passwd) ;
			
			if( debug ) log.info( "loginUser by id and passwd = " + loginUser );
			
			if( null == loginUser ) {
				loginUser = userRepository.findByEmailAndPasswd(id, passwd);
				
				if( null == loginUser ) {
					request.setAttribute( "login_error_msg", "사용자 정보가 잘못되었습니다." );
				}
				
				if( debug ) log.info( "loginUser by email and passwd = " + loginUser );
			}
		}
		
		if( null != loginUser ) {
			request.setAttribute( "loginUser", loginUser );
		}
		
		return loginUser ;
	}

}