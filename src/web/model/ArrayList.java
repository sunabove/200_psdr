package web.model;

import java.util.Collection;

public class ArrayList<T> extends java.util.ArrayList<T>{

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
