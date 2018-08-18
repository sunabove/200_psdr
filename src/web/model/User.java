package web.model;

import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter; 

@Entity
@Table(name = "user_tbl")

public class User extends CommonEntity { 
	
	private static final long serialVersionUID = -6023492649132057963L;

	@Id
	@Getter @Setter public String userId ;  
	
	@Getter @Setter public String passwd;	
	@Getter @Setter public String email; 
	
	@OneToOne
	@JoinColumn(name = "roleCode")
	@Getter @Setter public Code role ;
	
	@Getter @Setter public String name;
	
	@Getter @Setter public Timestamp lastLoginDt ;
	@Getter @Setter public Timestamp lastLogOutDt ;
	
	public User() {
	}

	public User( String userId, String passwd , Code role ) {
		this.userId = userId ; 
		this.passwd = passwd ;
		this.role = role ; 
	}
	
	public boolean isAdmin() {
		return null != role && "USER-ROLE-ADMIN".equalsIgnoreCase( role.codeId );
	}
	
}