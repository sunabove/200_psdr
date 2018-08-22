package web.model;

import java.sql.Timestamp;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DbFileLogRepository extends PagingAndSortingRepository<DbFileLog, String> {
	
	DbFileLog findByFileLogId(String fileLogId);
	
	DbFileLog findByDownloadFile( DbFile downloadFile ); 
	
}