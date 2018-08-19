package web.model; 
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter; 

@Entity 
@Table(name = "board_tbl")

public class Board extends CommonEntity { 

	private static final long serialVersionUID = -1390808608446429471L;

	@Id
	@Getter @Setter public String boardId ;  
	
	@Getter @Setter public String name ; 
	
	public Board() {
	}
	
}