package web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/article")
@Controller
public class ArticleController extends ComController {

	private static final long serialVersionUID = -5704084995590809168L;

	@RequestMapping(value = { "index.html", "main.html", "list.html" })
	public String articleList(HttpServletRequest request) {

		var loginRequire = true;

		String forward = this.processRequest(request, loginRequire);

		return "530_article_list.html";
	}

	@RequestMapping(value = { "view.html" })
	public String articleView(HttpServletRequest request) {

		var loginRequire = true;

		String forward = this.processRequest(request, loginRequire);

		return "540_article_view.html";
	}

}