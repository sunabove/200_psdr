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
	
	public Prop saveSysProp( String propId, String value ) {
		 
		Prop prop = propRepository.findByPropId( propId );
		
		if( null != prop ) {
			prop.value = value ;
			
			prop = propRepository.save( prop );
		}
		
		return prop ; 
	} 
	
	public Prop saveSysProp( Prop prop ) {
		prop = propRepository.save( prop );
		return prop;
	}

}