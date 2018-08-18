package web.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Service
@Transactional
@Log4j

public class PropService extends CommonService { 

	private static final long serialVersionUID = -8919069162905199897L;

	public PropService() {

	} 
	
	public Prop saveSysProp( String key, String value ) {
		 
		Prop prop = propRepository.findByKey( key );
		
		if( null != prop ) {
			prop.value = value ;
			
			prop = propRepository.save( prop );
		}
		
		return prop ; 
	} 

}