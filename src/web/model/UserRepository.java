package web.model;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByName(String name);
}