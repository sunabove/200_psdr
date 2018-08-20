package web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.DbFileList;

@RequestMapping("/data")
@Controller
public class FaultDataController extends ComController {

	private static final long serialVersionUID = 3131679599458841886L;

	public FaultDataController() { 
		this.loginRequire = true ;
	}

	@RequestMapping( value = { "index.html" , "main.html" , "down.html", "list.html" } )
	public String dataList( HttpServletRequest request, @PageableDefault(size = 20) Pageable pageable ) { 
		
		var loginRequire = true ;
		
		String forward = this.processRequest( request , loginRequire ) ; 
		
		String user_id = request.getParameter( "user_id" );
		
		if( isValid( user_id ) || this.isValid( forward ) ) {
			return forward ; 
		} 
		
		this.dbFileService.checkPsDrFileList(request);
		
		String gubun_code = request.getParameter( "gubun_code" );
		
		if( isEmpty( gubun_code ) ) {
			gubun_code = "Fault" ;
		}
		
		DbFileList dbFileList = this.dbFileRepository.findAllByGubunCodeAndDeletedOrderByUpDtDesc( gubun_code, false, pageable );
		
		if( null != dbFileList ) {
			dbFileList.setRowNumbers( request );
		}
		
		request.setAttribute( "gubun_code", gubun_code );
		request.setAttribute( "dbFileList", dbFileList );
		request.setAttribute( "dbFiles", dbFileList );
		
		return "210_data_list.html";
	}
	
	@RequestMapping( value = "download.html" , produces = "application/octet-stream" )
	public HttpEntity<byte[]> serverFile( HttpServletRequest request, HttpServletResponse response ) {

		byte[] contents = null ;
		
		if( null == contents ) {
			contents = new byte[ 0 ];
		} 
		
		String fileName = "" ; 
		
		String file_id = request.getParameter( "file_id" );
		
		if( this.isValid( file_id ) ) {
			var dbFile = this.dbFileRepository.findByFileId(file_id);
			if( null != dbFile ) {
				contents = dbFile.content ; 
			}
		}

		HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	    response.setHeader("Content-Disposition", "attachment; filename=" + fileName );

	    var entity = new HttpEntity<byte[]>( contents, headers); 
	    
		return entity ;
	}

}