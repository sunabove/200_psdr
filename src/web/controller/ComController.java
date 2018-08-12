package web.controller ;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.log4j.Log4j; 

@Log4j
public abstract class ComController extends ComObject {
	
	public ComController() {
		
		log.debug( this.getClass().getSimpleName() );
		
	}
	
	public final String getUrlHead( HttpServletRequest request ) {
		
		if( request == null ) {
			return "" ;
		} else {
		
			String url = request.getRequestURL().toString();
			String baseUrl = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath() ;
			
			return baseUrl  ;
		} 
	}
	
	public final Timestamp now() {
		return new Timestamp( System.currentTimeMillis() );
	}
	
	public final SimpleDateFormat dateFormat_yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

	private SimpleDateFormat df = this.dateFormat_yyyy_MM_dd;

	public java.sql.Timestamp parseDate(String dtStr) {
		dtStr = dtStr != null ? dtStr.trim() : dtStr;

		if (dtStr == null || dtStr.length() < 1) {
			return null;
		}

		java.util.Date date = null;
		try {
			date = df.parse(dtStr);
		} catch (Exception e) {
			date = null;
		}

		if (date == null) {
			return null;
		} else {
			return new java.sql.Timestamp(date.getTime());
		}
	}
	
	public String convertFromISO_8859_1( String text ) {
		if( text == null )  {
			return null ; 
		}
		try {
			text = new String( text.getBytes("ISO-8859-1") );
		} catch ( Exception e) { 
			e.printStackTrace();
		}
		
		return text;
	}
	
	public final Timestamp getTimestampAfterDays(Date date, Integer days) {
		
		if( date == null ) {
			return null;
		}

		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		days = days == null ? 0 : days;

		c.add(Calendar.DATE, days);

		Timestamp timestamp = new Timestamp( c.getTimeInMillis() );
		
		return timestamp; 
	}
	
}
