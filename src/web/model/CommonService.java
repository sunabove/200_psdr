package web.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import web.WebObject;

@Log4j

public abstract class CommonService extends WebObject { 
	
	@Autowired protected UserRepository userRepository;
	@Autowired protected CodeRepository codeRepository;
	@Autowired protected PropRepository propRepository;

	public CommonService() {
	}
	
	public Prop getProp( String key , String def ) {
		Prop prop = this.propRepository.findByKey( key );
		
		if( null == prop && key != null ) {
			prop = new Prop();
			prop.key = key;
			prop.value = def ;
			
			prop = propRepository.save( prop );
		}
		
		return prop;
	}
	
	public Code getCode( String codeId , String def , Integer ord ) {
		Code code = codeRepository.findByCodeId( codeId ) ;
		
		if( null == code && codeId.contains( "-" ) ) {
			String grpCodeId =  codeId.substring( 0, codeId.lastIndexOf( "-" ) );
			
			Code grpCode = this.getCode( grpCodeId , grpCodeId , 0 ) ; 
			
			if( null != grpCode ) { 
				code = new Code();
				code.grpCode = grpCode ; 
				code.codeId = codeId ;
				code.textValue = def ; 
				code.ord = ord;
				
				code = codeRepository.save( code );
			}
		} else if( null == code ) {
			code = new Code();
			code.codeId = codeId ;
			code.textValue = def ;
			code.ord = ord;
			
			code = codeRepository.save( code );
		}
		
		return code;
	}  

}