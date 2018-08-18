package web.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Service
@Transactional
@Log4j

public class DbFileService extends CommonService { 

	private static final long serialVersionUID = -1229350067174850078L;

	public DbFileService() {

	} 
	
	public DbFile saveDbFile( DbFile dbFile ) {
		return null ; 
	}

}