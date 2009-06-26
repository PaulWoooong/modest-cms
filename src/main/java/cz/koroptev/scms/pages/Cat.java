package cz.koroptev.scms.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.jirout.common.Chunk;

import cz.koroptev.scms.base.UserAwareComponent;
import cz.koroptev.scms.model.AbstractPage;
import cz.koroptev.scms.model.Article;
import cz.koroptev.scms.model.PageCategory;
import cz.koroptev.scms.services.ArticleService;
import cz.koroptev.scms.services.PageCategoryService;
import cz.koroptev.scms.services.PathService;

/**
 * This type of page represents preview of articles from one category.
 * 
 * @author jan
 * 
 */
public class Cat extends UserAwareComponent {

    private String pageUrl;

    private PageCategory page;

    @Inject
    private PathService pathService;

    @Inject
    private PageCategoryService pageCategoryService;

    @Inject
    private ArticleService articleService;

    @Property
    private Article article;

    @Property
    private int count;

    void onActivate(final String pageUrl) {
	this.pageUrl = pageUrl;
	if (pageUrl != null && pageUrl.length() > 0) {
	    AbstractPage abstractPage = (PageCategory) pathService
		    .getByPath(pageUrl);
	    if (abstractPage != null) {
		page = pageCategoryService.getById(abstractPage.getId());
	    }
	}
    }

    public List<Article> getArticles() {
	List<Article> articles = null;
	if (page.getCategory() != null && page.getCategory().getId() != null) {
	    articles = articleService.getArticlesByCategory(page.getCategory()
		    .getId());
	} else {
	    articles = articleService.getArticles(Chunk.getChunk(0, 4));
	}
	return articles;
    }

    String onPasivate() {
	return pageUrl;
    }

    public boolean isEqualCount() {
	return count % 2 == 0;
    }

    public PageCategory getPage() {
	return page;
    }

    public void setPage(PageCategory page) {
	this.page = page;
    }

}
