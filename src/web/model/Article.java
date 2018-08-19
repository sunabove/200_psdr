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
	
	@Column(name = "is_notice")
	@Getter @Setter public Boolean notice ;
	
	@Getter @Setter public String title ;
	
	@Lob
	@Getter @Setter public String content ; 
	
	@Column(name = "content_type")
	@Getter @Setter public String type = "TXT" ;
	
	@Getter @Setter public Integer viewCount = 0 ;
	
	public Article() {
	}
	
}