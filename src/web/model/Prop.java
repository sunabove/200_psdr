package web.model; 
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter; 

@Entity 

public class Prop extends CommonEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Getter @Setter public String key ;  
	
	@Getter @Setter public String value ;	
	
	public Prop() {
	}
	
}