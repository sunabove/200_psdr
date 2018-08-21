package web.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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

import lombok.extern.log4j.Log4j;
import web.model.DbFile;
import web.model.DbFileList;

@RequestMapping("/data")
@Controller
@Log4j
public class FaultDataController extends ComController {

	private static final long serialVersionUID = 3131679599458841886L;

	public FaultDataController() {
		this.loginRequire = true;
	}

	@RequestMapping(value = { "index.html", "main.html", "down.html", "list.html" })
	public String dataList(HttpServletRequest request, @PageableDefault(size = 20) Pageable pageable) {

		var loginRequire = true;

		String forward = this.processRequest(request, loginRequire);

		String user_id = request.getParameter("user_id");

		if (isValid(user_id) || this.isValid(forward)) {
			return forward;
		}

		this.dbFileService.checkPsDrFileList(request);

		String gubun_code = request.getParameter("gubun_code");

		if (isEmpty(gubun_code)) {
			gubun_code = "Fault";
		}

		DbFileList dbFileList = this.dbFileRepository.findAllByGubunCodeAndDeletedOrderByUpDtDesc(gubun_code, false,
				pageable);

		if (null != dbFileList) {
			dbFileList.setRowNumbers(request);
		}

		request.setAttribute("gubun_code", gubun_code);
		request.setAttribute("dbFileList", dbFileList);
		request.setAttribute("dbFiles", dbFileList);

		return "210_data_list.html";
	} 

	@GetMapping("/download/{file_no:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String file_no, HttpServletRequest request) throws Exception {
		var debug = true ; 
		
		String filePath = null ; 
		
		DbFile dbFile = null ; 

		if (this.isValid( file_no )) {
			dbFile = this.dbFileRepository.findByFileNo( file_no );
			if (null != dbFile) {
				filePath = dbFile.filePath ; 
			}
		}
		
		File file = new File( filePath ) ; 
		
		Resource resource = new UrlResource( file.toURI() );

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType( resource.getFile().getAbsolutePath() );
		} catch (Exception ex) {
			log.info("Could not determine file type.");
		}

		// Fallback to the default content type if type could not be determined
		if (contentType == null) {
			contentType = "application/octet-stream";
		}
		
		var fileName = resource.getFilename() ; 
		fileName = dbFile.fileName ; 
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		
		if( debug ) { 
			log.info( LINE );
			log.info( "fileName = " + fileName ); 
			log.info( LINE );
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
				.body(resource);
	}

}