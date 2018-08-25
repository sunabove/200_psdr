package web.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
	
	Article findByArticleId(Long articleId);   
	
	Article findFirstByNoticeAndDeletedOrderByUpDtDesc( Boolean notice , Boolean deleted );
	
	ArticleList findAllByTitleContainingAndDeletedOrderByNoticeDescUpDtDesc(String title, Boolean deleted, Pageable pageable); 
	
}