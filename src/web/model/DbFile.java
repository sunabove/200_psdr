package web.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "db_file_tbl")

public class DbFile extends CommonEntity { 
	private static final long serialVersionUID = -8745797345335307150L;

	@Id
	@Getter @Setter public String fileId ;

	@Getter @Setter public String gubunCode ;

	@Getter @Setter public String fileName;

	@Lob
	//@Column( length = 1_000_000_000 )
	@Getter
	@Setter
	public byte [] content;

	public DbFile() {
	} 

}