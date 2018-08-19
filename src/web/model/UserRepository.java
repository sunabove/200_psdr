package web.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
	User findByUserId(String userId); 
	
	User findByUserIdAndPasswd(String userId, String passwd);

	User findByEmailAndPasswd(String email, String passwd); 
	
	UserList findAllByOrderByUserIdAsc(Pageable pageable);
	
	UserList findAllByUserIdContainingOrderByUserIdAsc(String userId , Pageable pageable);
	
}