package web.model;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
	
	User findByName(String name); 

	//User findByIdAndPasswd(String id, String passwd);

	//User findByEmailAndPasswd(String email, String passwd);
	
}