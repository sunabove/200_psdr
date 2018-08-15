package web;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class WebObject extends JsonObject {
	
	private static final long serialVersionUID = 7893770244926016334L;

	protected static final String enc = java.nio.charset.StandardCharsets.UTF_8.toString() ;
	
	private static final Logger log = LoggerFactory.getLogger( WebObject.class );
	
	public WebObject () {
		
	}
	
	public final static String urlEncode( String text ) { 
		try {
			return URLEncoder.encode(text, enc );
		} catch ( Exception e) {
			e.printStackTrace();
			
			return text;
		}
	}
	
	public final static String urlDecode( String text ) { 
		try {
			return URLDecoder.decode(text, enc );
		} catch ( Exception e) {
			e.printStackTrace();
			
			return text;
		}
	}
	
	protected final String getDateDiffFormat( String dateDiff ) {
		
		String format = "";
		
		if( dateDiff != null ) {
			dateDiff = dateDiff.trim();
			
			if( dateDiff.startsWith( "-" ) ) {
				format += "-" ;
				dateDiff = dateDiff.substring( 1 );
			}
			
			String hms [] = dateDiff.split( ":" );
			int h = 0;
			int m = 0;
			int s = 0;
			
			if( hms != null && hms.length > 0 ) {
				h = WebObject.parseInt( hms[0] , 0 );
			}
			
			if( hms != null && hms.length > 1 ) {
				m = WebObject.parseInt( hms[1], 0 );
			}
			
			if( hms != null && hms.length > 2 ) {
				s = WebObject.parseInt( hms[2], 0 );
			}
			
			int d = h/24;
			h = h%24; 
			
			boolean hZero = h == 0 ;
			boolean mZero = m == 0 ;
			boolean sZero = s == 0 ;
			
			if( d > 364 ) {
				int y = d/365;
				int mon = ( d - y*365 )/30;
				
				format += y + ( y > 1 ? " years" : " year" );
				if( mon > 0 ) {
					format += " " + mon + ( mon > 1 ? "m" : "m" );
				}
			} else if( d > 29 ) {
				boolean showDays = false ; 
				int mon = d/30 ;
				int w = ( d - mon*30 )/7;
				
				format += mon + ( mon > 1 ? " months" : " month" );
				
				if( w > 0 ) {
					format += " " + w + ( w > 1 ? "w" : "w" );
				} else if( showDays ){
					d = d%7 ; 
					if( d > 0 ) {
						format += " " + d + ( d > 1 ? "d" : "d" );
					}
				}
			} else if( d > 6 ) { 
				int w = d/7 ; 
				d = ( d - w*7 );
				format += w + ( w > 1 ? " weeks" : " week" );
				if( d > 0 ) {
					format += " " + d + ( d > 1 ? "d" : "d" );
				}
			} else if( d > 0 ) {
				format += d + ( d > 1 ? " days" : " day" );
				
				if( ! hZero ) {
					format += " " + h + ( h > 1 ? "h" : "h" );
				} 
			} else if( ! hZero ) {
				if( mZero ) {
					format += h + ( h > 1 ? " hours" : " hour" );
				} else {
					format += h + ( h > 1 ? "h " : "h " );
					format += m + ( m > 1 ? "m" : "m" );
				} 
			} else {
				if( mZero ) {
					if( s < 2 ) {
						format += "1 second";
					} else { 
						format += s + " seconds";
					}
				} else {
					if( sZero ) {
						format += m + ( m > 1 ? " minutes" : " minute" ) ;
					} else {
						if( m < 2 ) {
							format += "1 minute";
						} else { 
							format += m + " minutes";
						}
					}
				}
			}
		}
		return format;
	}
	
	// --  getDateDiffFormat
	
	/**
	 * format integer with unit
	 * @param number
	 * @param unit
	 * @param unitDesc
	 * @param minSize
	 * @return
	 */
	public final String formatInteger( Integer number , int unit , String unitDesc , int minSize ) {
		
		if( number == null ) {
			number = 0 ; 
		}
		
		String format = "" ; 
		
		if( number < unit ) {
			format = String.format( "%,d", number ); 
		} else {
			if( number%unit == 0 ) {
				int i = number/unit ; 
				format = String.format( "%,d", i );
			} else {
				double f = ( number + 0.0)/unit ; 
				format = String.format( "%,.1f", f );
			}
			
			format += unitDesc ; 
		}
		
		for( int i = 0 , iLen = minSize - format.length() ; i < iLen ; i ++ ) {
			format = "&nbsp;" + format ; 
		}
		
		return format ; 
	}
	
	// format integer
	
	// debugList
	public boolean debugList(List<?> list) {

		log.info( "" 	);
		log.info( LINE 	);

		log.info( "Debugging list ...." );

		if( null == list ) {
			log.info( "The list is null!" );
		} else {
			log.info( String.format( "The list class is %s." , list.getClass().getName()) );
			log.info( String.format( "The count of selected list is %,d." , list.size()) 	);
			int rowNo = 1;

			for (Object obj : list) {
				if( null == obj ) {
					log.info( String.format( "[ %s ]:[%,4d] = %s", "" , rowNo , "null" ) );
				} else if( null != obj ) { 
					log.info( String.format( "[ %s ]:[%,4d] = %s" , obj.getClass().getSimpleName() , rowNo, JsonObject.toJsonObject( obj )) );
				}
				rowNo++;
			}
		}

		log.info( "End of debugging list ." );
		log.info( "" 	);
		log.info( LINE 	);
		log.info( "" 	);
		
		return true;

	} 
	// -- debugList 
	
	// removeDuplicatedIntegers
	private void removeDuplicatedIntegers( ) {
		List<Integer> primes = new ArrayList<Integer>();
		
		primes.add(2); 
		primes.add(7); 
		primes.add(7);
		primes.add(11);

		Set<Integer> primesWithoutDuplicates = new LinkedHashSet<Integer>(primes);
		
		primes.clear();
		
		primes.addAll(primesWithoutDuplicates); 
	}
	// -- removeDuplicatedIntegers
	
}