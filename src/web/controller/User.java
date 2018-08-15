package web.controller;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;
import web.JsonObject;

@Entity
@Table(name = "user_tbl")

public class User extends JsonObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 396819332752790606L;
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	@Getter @Setter private String id;
	
	@Getter @Setter private String email;
	
	@Column(name = "passwd")
	@Getter @Setter private String password;
	@Getter @Setter private String name;
	
	@Getter @Setter private String lastLoginDt;
	@Getter @Setter private String lastLogOutDt;
	
	@Getter @Setter private int age;

	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}  
}