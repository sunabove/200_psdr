package web.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import web.controller.ComController;

@Service
@Transactional
@Log4j

public class DbFileService extends CommonService { 

	private static final long serialVersionUID = -1229350067174850078L;

	public DbFileService() {

	} 
	
	public DbFile getSystemDbFileByFileId( String fileId , ComController controller , HttpServletRequest request ) {
		DbFile dbFile = this.dbFileRepository.findByFileId( fileId ) ; 
		
		User loginUser = controller.getLoginUser(request);
		
		if( null == dbFile ) {
			dbFile = new DbFile() ;
			dbFile.fileId = fileId ; 
		}
		
		if( null != dbFile ) {
			//dbFile.u
		}
		
		return dbFile;
	}
	
	public DbFile saveDbFile( DbFile dbFile ) {
		if( null != dbFile ) {
			dbFile = this.dbFileRepository.save( dbFile );
		}
		
		return dbFile ; 
	}

}