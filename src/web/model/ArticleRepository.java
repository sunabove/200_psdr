package web.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
	
	Article findByArticleId(String articleId);  
	
	Article findByNoticeAndDeletedOrderByUpDtDesc( Boolean notice , Boolean deleted );
	
	ArticleList findAllByTitleContainingAndDeletedOrderByNoticeDescUpDtDesc(String title, Boolean deleted, Pageable pageable); 
	
}