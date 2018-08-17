package web.model; 
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter; 

@Entity 

public class Code extends CommonEntity {  

	private static final long serialVersionUID = -5392925777521538251L;

	@Id
	@Getter @Setter public String code ;  
	
	@Getter @Setter public String textValue ;	
	@Getter @Setter public String numValue ; 
	
	@ManyToOne
    @JoinColumn(name="p_code")
    public Code pCode;
	
	public Code() {
	}
	
}