package web.model;

import java.sql.Timestamp;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;
import web.JsonObject;

@MappedSuperclass
public abstract class CommonEntity extends JsonObject {
	
	@Getter @Setter public String    updUserId ;
	
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Getter @Setter public Timestamp upDt ;
	
	public CommonEntity() {
		
	}
	
	@PrePersist
	protected void onCreate() {
	    if ( this.upDt == null) { upDt = this.getNow() ; }
	}

}
