package web.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
	User findByUserId(String userId); 
	
	User findByUserIdAndPasswd(String userId, String passwd);

	User findByEmailAndPasswd(String email, String passwd); 
	
	UserList findAllByOrderByUserIdAsc();
	
	UserList findAllByUserIdContainingOrderByUserIdAsc(String userId);
	
}