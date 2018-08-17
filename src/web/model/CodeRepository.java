package web.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface CodeRepository extends PagingAndSortingRepository<Code, String> {
	
	Code findByCode(String code);  
	
	ArrayList<Code> findByPCode(String pCode);
	
}