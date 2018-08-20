package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

}