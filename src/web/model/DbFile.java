package web.model;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table( name = "db_file_tbl"
 , indexes = { @Index(name = "file_no_idx", columnList = "file_no") }
)

public class DbFile extends CommonEntity { 
	private static final long serialVersionUID = -8745797345335307150L;

	@Id
	@Column(length=1000)
	@Getter @Setter public String fileId ;

	@Getter @Setter public String gubunCode ;
	
	@Column( name="file_no" )
	@Getter @Setter public String fileNo ;

	@Column(length=255)
	@Getter @Setter public String fileName ;
	
	@Column(length=1000)
	@Getter @Setter public String filePath ;

	@Lob
	//@Column( length = 1_000_000_000 )
	@Getter
	@Setter
	public byte [] content;

	public DbFile() {
	} 

}