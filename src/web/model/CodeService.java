package web.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Service
@Transactional
@Log4j

public class CodeService extends CommonService {  

	private static final long serialVersionUID = -3136955053486112227L;

	public CodeService() {

	} 
	
	public Code saveCode( Code code ) {
		if( null != code ) {
			code = this.codeRepository.save( code );
		}
		
		return code ;
	}

}