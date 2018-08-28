package web.model;

public class DbFileLogList extends ArrayList<DbFileLog> {  

	private static final long serialVersionUID = 7987756610062459543L;

	public DbFileLogList() {
	}
	
	public int getDownloadCountSum() {
		int sum = 0 ;
		
		for( DbFileLog item : this ) {
			if( null != item ) { 
				sum += null == item.downloadCount ? 0 : item.downloadCount ; 
			}
		}
		
		return sum;  
	}

}
