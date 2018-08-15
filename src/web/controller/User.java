package web.controller;

import java.sql.Timestamp;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import web.JsonObject;

@Entity
@Table(name = "user_tbl")

public class User { 
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private Integer id; 
	
	@Getter @Setter private String passwd;
	
	/*
	@Getter @Setter public String email;
	
	@Getter @Setter public String roleCode;
	
	@Getter @Setter public String name;
	
	@Getter @Setter public Timestamp lastLoginDt;
	@Getter @Setter public Timestamp lastLogOutDt;
	*/ 

	public User() {
	}

	public User(Integer id, String passwd) {
		this.id = id; 
		this.passwd = passwd ;
	}  
}