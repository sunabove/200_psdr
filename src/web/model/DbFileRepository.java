package web.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DbFileRepository extends PagingAndSortingRepository<DbFile, String> {
	
	DbFile findByFileId(String fileId);  
	
	DbFile findFirstByGubunCodeOrderByUpDtDesc( String gubunCode );
	
	DbFileList findAllByGubunCodeAndDeletedOrderByUpDtDesc( String gubunCode , boolean deleted, Pageable pageable );
	
}