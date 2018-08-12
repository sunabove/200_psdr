package web.controller;

public abstract class ComObject {

	public static final String LINE = "_________________________________________________________________" ; 
	
	public ComObject () {
		
	}
	
	public final static Double parseDouble( String text ) {
		return parseDouble( text, null );
	}
	
	public final static Double parseDouble( String text, Double def ) {
		try {
			return Double.parseDouble( text.trim() );
		} catch ( Exception e) {
			return def ; 
		}
	}
	
	public final static Integer parseInt( String text, Integer def ) {
		Double d = parseDouble( text );
		if( d != null ) {
			return d.intValue() ; 
		} else {
			return def ; 
		} 
	}
	
}
