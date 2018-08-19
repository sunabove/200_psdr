package web.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.log4j.Log4j;
import web.WebObject;

@Log4j

public abstract class CommonService extends WebObject { 
	
	@Autowired public UserRepository userRepository;
	@Autowired public CodeRepository codeRepository;
	@Autowired public PropRepository propRepository;
	@Autowired public DbFileRepository dbFileRepository;
	
	@Autowired public BoardRepository   boardRepository;
	@Autowired public ArticleRepository articleRepository;

	public CommonService() {
	}
	
	public Prop getProp( String propId , String def ) {
		Prop prop = this.propRepository.findByPropId( propId );
		
		if( null == prop && propId != null ) {
			prop = new Prop();
			prop.propId = propId;
			prop.value = def ;
			
			propRepository.save( prop );
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