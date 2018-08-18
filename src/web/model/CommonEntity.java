package web.model;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import web.JsonObject;

@MappedSuperclass
public abstract class CommonEntity extends JsonObject {
	
	@Getter @Setter public String    upUserId ;
	
	@UpdateTimestamp
	@Getter @Setter public Timestamp upDt ;
	
	@Getter @Setter public Boolean deleted = false ;
	
	public CommonEntity() {
	}
	
	@PreUpdate
    @PrePersist
    protected void onUpdate() {
		if ( this.upDt == null) { upDt = this.getNow() ; }
	} 

}
