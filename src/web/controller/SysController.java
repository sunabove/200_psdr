package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import web.model.Prop;

@RequestMapping("/sys")
@Controller
public class SysController extends ComController {

	private static final long serialVersionUID = 7435308736508795619L;

	public SysController() {
		this.loginRequire = true;
	}

	@RequestMapping(value = { "index.html", "main.html", "monitor.html" })
	public String monitor(HttpServletRequest request) {
		var loginRequire = this.loginRequire;

		String forward = this.processRequest(request, loginRequire);

		if (null != forward) {
			return forward;
		}

		return "420_sys_monitor.html";
	}

	@RequestMapping("user_stat.html")
	public String userStat(HttpServletRequest request) {
		var loginRequire = this.loginRequire;

		String forward = this.processRequest(request, loginRequire);

		if (null != forward) {
			return forward;
		}

		return "430_user_sys_stat.html";
	}

	@RequestMapping("setting.html")
	public String setting(HttpServletRequest request) {
		var loginRequire = this.loginRequire;

		String forward = this.processRequest(request, loginRequire);

		if (null != forward) {
			return forward;
		}

		// system name properties
		Prop sysName_01 = propService.getProp("SYS_NAME_01", "경기 지역 본부");
		Prop sysName_02 = propService.getProp("SYS_NAME_02", "성남 전력 지사");
		Prop sysName_03 = propService.getProp("SYS_NAME_03", "154KV 중원변전소");

		var rowCount = 0  ; 
		
		if (true) {
			var sysName_01_txt = request.getParameter("sysName_01");
			var sysName_02_txt = request.getParameter("sysName_02");
			var sysName_03_txt = request.getParameter("sysName_03");
			
			if( isValid( sysName_01_txt ) ) {
				sysName_01.value = sysName_01_txt ; 
				
				this.propService.saveProp( sysName_01 );
				
				rowCount ++ ;
			}
			
			if( isValid( sysName_02_txt ) ) {
				sysName_02.value = sysName_02_txt ; 
				
				this.propService.saveProp( sysName_02 );
				
				rowCount ++ ;
			}
			
			if( isValid( sysName_03_txt ) ) {
				sysName_03.value = sysName_03_txt ; 
				
				this.propService.saveProp( sysName_03 );
				
				rowCount ++ ;
			}
		}

		if (true) {

			if (this.isEmpty(request.getParameter("sysName_01"))) {
				request.setAttribute("sysName_01_txt", sysName_01.value);
			}

			if (this.isEmpty(request.getParameter("sysName_02"))) {
				request.setAttribute("sysName_02_txt", sysName_02.value);
			}

			if (this.isEmpty(request.getParameter("sysName_03"))) {
				request.setAttribute("sysName_03_txt", sysName_03.value);
			}
		}
		
		if( 0 < rowCount ) {
			return "redirect:/sys/setting.html" ; 
		} else { 
			return "440_sys_setting.html";
		}
	}

}