package web.controller; 

import com.google.gson.Gson;

public abstract class JsonObject extends ComObject {
	
	private static final long serialVersionUID = -4315066588118613674L;

	public JsonObject() {
		
	}
	
	public final String replaceNullAsEmpty( String text ) {
		text = text.replaceAll( "\"null\"", "\"\"" );
		
		text = text.replaceAll( "null", "" );
		
		return text;
	}
	
	@Override
	public String toString() {
		return gson.toJson( this ) ; 
	} 
	
	private static final Gson gson = new Gson();  
	
	public static final String toJsonObject( Object obj ) {
		return gson.toJson( obj );
	}
	
}
