package web.model;

import java.sql.Timestamp;

import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate; 
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter; 
import web.WebObject; 

@MappedSuperclass
public abstract class CommonEntity extends WebObject {
	
	@OneToOne
	@JoinColumn( name = "UP_USER_ID" )
	@Getter @Setter public User upUser ;
	
	@UpdateTimestamp
	@Getter @Setter public Timestamp upDt ;
	
	@Getter @Setter public Boolean deleted = false ;
	
	@Getter @Setter public transient int rowNumer = 0 ; 
	
	public CommonEntity() {
	}
	
	@PreUpdate
    @PrePersist
    protected void onUpdate() {
		if ( this.upDt == null) { upDt = this.getNow() ; }
	} 
	
	public void updateUpUser( HttpServletRequest request ) { 
		this.upUser = this.getLoginUser(request); 
	}	

}
