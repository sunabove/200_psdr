package web;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
public class SysConfig extends JsonObject {

	private static final long serialVersionUID = 34910595661725712L;
	
	@Getter @Setter public String defaultSupserUserId = "procom";
	
	@Getter @Setter public String defaultSupserUserPasswd = "12345678";
	
	@Getter @Setter public Long defaultBoardId = 1L ;
	
	public SysConfig() {
	}

}
