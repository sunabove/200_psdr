package web.controller;

import java.sql.Timestamp;
import java.util.*;

import javax.servlet.http.*; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*; 
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import web.CustomTimestampEditor;
import web.Html;
import web.WebObject;
import web.gson.DateDeserializer;
import web.gson.TimestampDeserializer;
import web.model.User;
import web.model.UserRepository;
import web.model.UserService; 

/**
 * Common controller of user web module
 * @author sunabove
 *
 */

public abstract class ComController extends WebObject {

	private static final long serialVersionUID = -2104510560222014738L;

	private static final Logger log = LoggerFactory.getLogger( ComController.class );
	
	private static final boolean debug = true;

	private static final String LOGIN_USER_ATTR_NAME = "loginUser" ; 
	
	boolean loginRequire = false ;  
	
	@Autowired private HttpServletRequest request ; 
	
	@Autowired public UserService userService ;

	// constructor
	public ComController() {
	}
	// -- constructor
	
	// init binder
	@InitBinder
	protected void initBinder( WebDataBinder binder ) {
		binder.registerCustomEditor( Date.class , 		new CustomTimestampEditor() );
	    binder.registerCustomEditor( Timestamp.class , 	new CustomTimestampEditor() );
	}
	// -- init binder
	
	// getQueryParams
	/**
	 * getQueryParams
	 * @param request
	 * @return
	 */
	public String getQueryParams( HttpServletRequest request ) {
		String params = "";
		
		if ( true ) {
			// Analyze the parameter values
			Map<String, String[]> map = request.getParameterMap();

			if (map != null && map.size() > 0) {
				Set<String> keys = map.keySet();
				Iterator<String> keysIt = keys.iterator();
				String key;
				String[] vals;
				int idx = 0;
				while (keysIt.hasNext()) {
					key = keysIt.next();
					vals = map.get(key);
					if( key == null || key.trim().length() < 1 ) {
						// do nothing
					} else if (vals == null || vals.length < 1) {
						params += (idx > 0 ? "&" : "?") + key + "=";
						idx++;
						// logger.info( "key: " + key + " = " + ";" );
					} else {
						for (int i = 0, iLen = vals.length; i < iLen; i++) {
							params += (idx > 0 ? "&" : "?") + key + "=" + vals[i];
							idx++;
							// logger.info( "key: " + key + " = " + vals[i] +
							// ";" );
						}
					}
				}
			}
		}
		
		return params ; 
	}
	// -- getQueryParams
	
	/** 
	 * 
	 * @return whether if the request is get type.
	 * 
	 */
	public boolean isGetRequest() { 
		return this.getRequest().getMethod().equalsIgnoreCase("GET"); 
	}
	
	/**
    *
    * @return whether if the request is post type.
    */
    public boolean isPostRequest() { 
       return this.getRequest().getMethod().equalsIgnoreCase("POST"); 
    }

	/**
	 * 
	 * @return session associated with current request
	 * 
	 */
	protected HttpSession getSession() { 
		return this.getRequest().getSession(); 
	}
	
	/**
	 * 
	 * @return request associated with current context
	 * 
	 */
	protected HttpServletRequest getRequest() { 
		return this.request ; 
	}

	/**
	 * return the user which has logged in on the current session
	 * 
	 * @param request
	 * @return
	 */
	public User getSessionLoginUser( ) {
		
		HttpSession httpSession = this.getSession( );

		if ( null == httpSession ) {
			// if the session has been expired
			String msg = "HTTP SESSION HAS EXPIRED!";

			log.info(msg);

			return null;
			// -- if the session has been expired
		} else { 
			// if the session is valid
			User loginUser = (User) httpSession.getAttribute( LOGIN_USER_ATTR_NAME ) ;  
			
			return loginUser ;			
			// -- if the session is valid
		}
		
	}
	// getSessionLoginUser
	
	/**
	 * set a user which has been logged in.
	 * @param user
	 */
	public void setSessionLoginUser( User user ) {
		 this.getSession().setAttribute( LOGIN_USER_ATTR_NAME, user );
	}

	/**
	 * returns whether if the current user has logged in correctly.
	 * 
	 * @param request
	 * @return
	 */
	public boolean hasUserLoggedIn( ) {
		return null != this.getSessionLoginUser( );
	}
	
	// showRequestInfo
	public boolean showRequestInfo( HttpServletRequest request ) {
		String contextPath 	= request.getContextPath();
		String currUri 		= request.getRequestURI() ; 
		String currUrl 		= request.getRequestURL().toString();
		String currUrlPath 	= request.getRequestURI().substring(request.getContextPath().length());
		String queryString 	= request.getQueryString() ;
		String referer		= request.getHeader("referer");
		
		referer 			= null == referer 		? "" : referer 		; 
		queryString 		= null == queryString 	? "" : queryString 	; 
		
		log.info( "" );
		log.info( LINE );
		log.info( "" );
		
		log.info( "contextPath  = " + contextPath 	); 
		log.info( "currUri      = " + currUri 		); 
		log.info( "currUrl      = " + currUrl 		); 
		log.info( "currUrlPath  = " + currUrlPath 	); 
		log.info( "referer      = " + referer 		); 
		log.info( "queryString  = " + queryString 	); 
		
		log.info( "" );
		log.info( LINE );
		log.info( "" );	
		
		return true; 
	}
	// -- showRequestInfo
	
	// isRequestUrlSameAsReferer
	public boolean isRequestUrlSameAsReferer( HttpServletRequest request ) {
		String currUrl 		= request.getRequestURL().toString();
		String referer		= request.getHeader("referer");
		
		currUrl 			= null == currUrl 		? "" : currUrl.trim() ;
		referer 			= null == referer 		? "" : referer.trim() ; 		;
		
		log.info( "" );
		log.info( LINE );
		log.info( "" );
		
		log.info( "currUrl      = " + currUrl 		); 
		log.info( "referer      = " + referer 		); 
		
		log.info( "" );
		log.info( LINE );
		log.info( "" ); 
		
		if( isEmpty( currUrl) || isEmpty( referer) ) {
			return false ; 
		} else if( currUrl.equalsIgnoreCase( referer ) ) {
			return true ; 
		}
			
		return false ;
	}
	// -- isRequestUrlSameAsReferer
	
	// isFirstRequest
	public boolean isFirstVisit( HttpServletRequest request ) {
		String referer = request.getHeader("referer");
		
		if( isEmpty( referer ) ) {
			return true ; 
		} else if( isRequestUrlSameAsReferer( request ) ) {
			return false ; 
		} else {
			return true ; 
		}
	}
	// -- isFirstRequest
	
	
	public void setCommonAttributes( ) {
		Html html = null ; 
		
		this.setCommonAttributes( html );
	}
	
	// setCommonAttributes
	public void setCommonAttributes( Html html ) {
		
		boolean debug = this.debug && true ;
		
		HttpServletRequest request = this.request ;  
		
		html = null == html ? new Html() : html ; 
		
		// debug context path
		debug = debug && this.showRequestInfo( request ); 
		// -- debug context path 
		
		// user login sid
		
		final User sessionLoginUser = this.getSessionLoginUser() ;
		
		// -- user login sid 
		
		// set current url path

		String currUrlPath = request.getRequestURI().substring(request.getContextPath().length());
		html.setCurrUrlPath( currUrlPath );
		
		// -- set current url path
		
		html.setLoginUser( sessionLoginUser );
		
		// crud and editable
		String crud = html.getCrud() ;
		
		crud = null == crud ? "" : crud.trim() ; 
		
		boolean editable = false ; 
		
		if( "new".equalsIgnoreCase( crud ) || "edit".equalsIgnoreCase( crud ) ) {
			editable = true ; 
		}
		
		html.setEditable( editable );

		// -- crud and editable
		
		request.setAttribute( "editable"	, editable );
		
		request.setAttribute( "readonly" 	, ! editable );
		
		request.setAttribute( "crud" 		, crud );
		
		request.setAttribute( "html"		, html ); 
		
	}
	// setCommonAttributes 
	
	// getObjectFromJsonText
	public <T> T getObjectFromJsonText(  String jsonText, Class<?> klass ) {
		
		String funName = "getParsedObjectFromJsonText()" ; 
		
		log.info( "" );
		log.info( LINE );
		log.info( "" + funName );
		log.info( "" );
		
		T jsonObject = null ; 
		
		if( isValid( jsonText ) ) {
			
			jsonText = jsonText.trim() ; 
			
			if( jsonText.startsWith( "\"") ) {
				jsonText = jsonText.substring( 1 );
			}
			
			if( jsonText.endsWith( "\"") ) {
				jsonText = jsonText.substring( 0, jsonText.length() - 1 ); 
			}
			
			// appText = appText.replace( "\\\"", "\"" );
			
			//jsonText = jsonText.replace( "\"{", "{" );
			//jsonText = jsonText.replace( "}\"", "}" );				
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter( Date.class, new DateDeserializer());
			gsonBuilder.registerTypeAdapter( Timestamp.class, new TimestampDeserializer()); 
			
			Gson gson = gsonBuilder.create() ; 
			
			try {  
				jsonObject = (T) gson.fromJson( jsonText, klass );
			} catch ( Exception e ) {
				e.printStackTrace();
			}
		}
		
		log.info( "" + ( null == jsonObject ? "fail" : "success" ) );
		
		log.info( "" );
		log.info( "// " + funName );
		log.info( LINE );
		log.info( "" );
		
		return jsonObject ; 
	}
	// -- getObjectFromJsonText
	
	// show binding errors
	public void showBindingErrors( BindingResult bindingResult ) {
		if( null != bindingResult && bindingResult.hasErrors() ) {
			log.info( "" );
			log.info( "bindingResult = " + bindingResult.toString() );
			
			List<ObjectError> errors = bindingResult.getAllErrors();
			if( null != errors ) {
				int index = 0 ; 
				for( ObjectError error: errors ) {
					log.info( String.format( "binding error[%03d]: %s" ,index , error.toString() ) );
					index ++ ; 
				}
			}
			
			log.info( "" );
			log.info( "" );
		}
	}
	// -- show binding errors
	
	public String processRequest( ) {
		boolean loginRequire = this.loginRequire ; 
		
		return this.processRequest(loginRequire);
	}
	
	public String processRequest( boolean loginRequire ) {
		
		boolean debug = this.debug && true ;
		
		HttpServletRequest request = this.request ;
		
		String forward = null ;
		
		if( loginRequire ) {
			User loginUser = this.getSessionLoginUser();
			
			if( null == loginUser ) {
				loginUser = userService.getLoginUser( request );
				
				if( null != loginUser ) {
					this.setSessionLoginUser( loginUser );
				}
			}
			
			if( null == loginUser ) { 
				forward = "forward:/user/login.html";
			}
		}
		
		if ( debug ) log.info( this.format( "forward = %s", forward ) );
		
		return forward ; 
		
	}
	
}