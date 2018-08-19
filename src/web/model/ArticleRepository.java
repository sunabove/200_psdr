package web.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
	
	Article findByArticleId(String userId);  
	
	ArticleList findAllByTitleOrderByUpDtDesc(String title, Pageable pageable); 
	
}