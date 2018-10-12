package web.model;

import java.sql.Timestamp;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DbFileRepository extends PagingAndSortingRepository<DbFile, String> {
	
	DbFile findByFileId(String fileId);
	
	DbFile findByFileNo(String fileNo);
	
	DbFile findFirstByGubunCodeOrderByUpDtDesc( String gubunCode );
	
	Page<DbFile> findAllByGubunCodeAndDeletedOrderByFileModDtDescFileName( String gubunCode , boolean deleted, Pageable pageable );
	
	Page<DbFile> findAllByGubunCodeAndFileModDtLessThanEqualAndDeletedOrderByFileModDtDescFileName( String gubunCode , Timestamp upDt, boolean deleted, Pageable pageable );
	
	//findByEndLessThanEqual 
	
}