package web.model;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

public class ArrayList<T extends CommonEntity> extends java.util.ArrayList<T>{

	private static final long serialVersionUID = -2280116744144438187L;

	public ArrayList() {
		super();
	}

	public ArrayList(Collection<? extends T> c) {
		super(c);
	}

	public ArrayList(int initialCapacity) {
		super(initialCapacity);
	}
	
	public final Integer getTotalRowCount() {
		return this.size() ;
	}
	
	public final int getSize() {
		return this.size() ; 
	}
	
	public final T get( int index ) {
		
		if( 0 > index ) {
			index = this.size() - index ;
		}
		
		if( 0 > index ) {
			return null ; 
		}
		
		return super.get( index );
	}
	
	public Integer parseInt( String text, Integer def ) {
		try { 
			Double d = Double.parseDouble( text );
			
			return d.intValue();
		} catch ( Exception e ) {
			return def; 
		} 
	}
	
	public void setRowNumbers( HttpServletRequest reqeust ) {
		Integer page = this.parseInt( reqeust.getParameter( "page"), 0 );
		
		int index = 1 ; 
		for( CommonEntity entity : this ) {
			entity.setRowNumer( page*10 + index );
			index ++ ; 
		}
	}
	
	public int [] getPageList( String currPage ) {
		Integer page = this.parseInt(currPage, 0) ; 
		
		page = page < 0 ? 0 : page ;
		
		int from = (page/10);
		int to = from + 9 ; 
		
		int [] pages = new int[ to - from ] ;
		
		for( int i = 0 , iLen = pages.length ; i < iLen ; i ++ ) {
			pages[ i ] = from + i ; 
		}
		
		return pages;
	}
	
	public int [] getPageEmptyRowSequence( ) {
		int size = this.size() ; 
		
		size = 10 - size ; 
		
		size = size < 0 ? 0 : size ; 
		
		int [] seq = new int[ size ] ;
		
		for( int i = 0, iLen = size ; i < iLen ; i ++ ) {
			seq[ i ] = i ;
		}
		
		return seq;
	}
}
