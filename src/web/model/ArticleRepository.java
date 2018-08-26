package web.model;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepository extends PagingAndSortingRepository<Article, String> {
	
	Article findByArticleId(Long articleId);   
	
	Article findFirstByArticleIdLessThanAndDeletedOrderByArticleIdDesc( Long articleId, Boolean deleted );
	
	Article findFirstByArticleIdGreaterThanAndDeletedOrderByArticleIdAsc( Long articleId, Boolean deleted );
	
	Article findFirstByNoticeAndDeletedOrderByUpDtDesc( Boolean notice , Boolean deleted );
	
	ArticleList findAllByTitleContainingAndDeletedOrderByNoticeDescUpDtDescArticleIdAsc(String title, Boolean deleted, Pageable pageable); 
	
}