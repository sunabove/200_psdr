package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import web.model.*; 

@RequestMapping("/article")
@Controller
public class ArticleController extends ComController {

	private static final long serialVersionUID = -5704084995590809168L;

	@RequestMapping(value = { "index.html", "main.html", "list.html" })
	public String articleList(HttpServletRequest request , @PageableDefault(size = 10) Pageable pageable ) {
		var loginRequire = true;

		String forward = this.processRequest(request, loginRequire);
		
		if( this.isValid( forward ) ) {
			return forward ; 
		}
		
		String article_title_search = request.getParameter( "article_title_search" );
		
		if( null == article_title_search ) {
			article_title_search = "";
		}
		
		ArticleList articleList = this.articleRepository.findAllByTitleContainingAndDeletedOrderByNoticeDescUpDtDesc( article_title_search, false, pageable );
		
		if( null != articleList ) {
			articleList.setRowNumbers(request);
		}
		
		request.setAttribute( "articleList", articleList );
		request.setAttribute( "articles", articleList );

		return "530_article_list.html";
	}

	@RequestMapping(value = { "view.html" })
	public String articleView(HttpServletRequest request, RedirectAttributes ra ) {
		var loginRequire = true;
		String forward = this.processRequest(request, loginRequire);
		
		if( this.isValid( forward ) ) {
			ra.addAttribute( "id", request.getParameter( "id") );
			
			return forward ; 
		}
		
		Article article = null ; 
		
		Long article_id = this.parseLong( request.getParameter( "article_id" ) );
		
		if( isEmpty( article_id ) ) {
			article_id = this.parseLong( request.getParameter( "id" ) );
		}
		
		if( isValid( article_id ) ) {
			article = this.articleRepository.findByArticleId( article_id );
		}
		
		String cmd = request.getParameter( "cmd" );
		
		if( "save".equalsIgnoreCase( cmd ) ) {
			article = this.articleService.saveArticleCreateIfNotExist(article, request);
		} else if( "delete".equalsIgnoreCase( cmd ) ) {
			article = this.articleService.deleteArticle(article, request);
		} else if( null != article ) {
			article.viewCount = null == article.viewCount ? 1 : article.viewCount + 1 ; 
			
			this.articleService.saveArticleOnly(article);
		}
		
		if( null != article && article.deleted ) {
			article = null ; 
		} 
		
		if( null == article ) {
			article = new Article();
			article.updateUpUser( request );
		}
		
		request.setAttribute( "article", article );

		return "540_article_view.html";
	}

}