package web.model;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@Service
@Transactional
@Log4j

public class ArticleService extends CommonService { 

	private static final long serialVersionUID = -8906663530256522388L;

	public ArticleService() {
	} 
	
	public Article saveArticle( Article article, HttpServletRequest request ) {
		String article_id 		= request.getParameter( "article_id" );
		String article_notice 	= request.getParameter( "article_notice" );
		String article_title 	= request.getParameter( "article_title" );
		String article_content 	= request.getParameter( "article_content" );
		String article_type		= request.getParameter( "article_type" ); 
		
		if( null == article && isValid( article_id ) ) {
			article = this.articleRepository.findByArticleId( article_id ); 
		} else if( null == article && isEmpty( article_id ) ) {
			article = new Article();
		}
		
		if( null == article ) {
			article = new Article();
		}
		
		if( null == article.articleId ) {
			article.articleId = this.createUuid();
		}
		
		if( null == article.board ) {
			var board = this.getBoardCreateIfNotExist( "1", request );
			
			article.board = board ;
		}
		
		if( isValid( article_title ) ) {
			article.title = article_title ; 
		}
		
		article.type = isEmpty( article_type ) ? "TXT" : article_type ;
		article.content = article_content ;
		article.notice = "1".equalsIgnoreCase( article_notice );
		
		article.updateUpUser(request);
		
		article = this.articleRepository.save( article );
		
		return article ;
	} 
	
	public Board getBoardCreateIfNotExist( String boardId , HttpServletRequest request ) {
		if( isEmpty( boardId ) ) {
			boardId = "0";
		}
		
		var board = this.boardRepository.findByBoardId( boardId );
		if( null == board ) {
			board = new Board();
			board.boardId = boardId ;
			board.name = "게시판" ; 
			board.updateUpUser( request );
			
			board = this.boardRepository.save( board );
		}
		
		return board; 
	}
	
	public void createTestData( HttpServletRequest request ) {
		log.info( "createTestData" );
		
		var count = this.articleRepository.count();
		
		log.info( "count = " + count );
		
		if( 200 > count ) {
			Board board = this.getBoardCreateIfNotExist( "0" , request );
			
			for( long i = 0 , iLen = 200 - count ; i < iLen ; i ++ ) {
				Article article = new Article();
				
				article.notice = 0 == i ; 
				if( 1 > i ) {
					article.title = "PSDR FDW 홈페이지에 오신 것을 환영합니다." ; 
				} else { 
					article.title = "test_" + ( i < 10 ? "0" : "" ) + i ;
				}
				article.content = "test_" + ( i < 10 ? "0" : "" )  + i + "conent" ; 
				article.board = board ; 
				
				article.updateUpUser(request);
				
				article = this.articleRepository.save( article );
				
				log.info( "save article = " + article );
			}
		}
		
	}

}