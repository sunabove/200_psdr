package web.model; 
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter; 

@Entity 
@Table(name = "article_tbl")

public class Article extends CommonEntity { 

	private static final long serialVersionUID = 7669363100960406954L;

	@Id
	@Getter @Setter public String articleId ;  
	
	@ManyToOne
    @JoinColumn( name="board_id" ) 
	@Getter @Setter public Board board ;
	
	@Getter @Setter public Boolean isNotice ;
	
	@Getter @Setter public String title ;
	
	@Lob
	@Getter @Setter public String content ; 
	
	@Getter @Setter public String contentType = "TXT" ;  
	
	public Article() {
	}
	
}