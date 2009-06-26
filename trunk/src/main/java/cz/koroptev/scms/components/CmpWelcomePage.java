package cz.koroptev.scms.components;

import java.util.List;

import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.jirout.common.Chunk;

import cz.koroptev.scms.base.UserAwareComponent;
import cz.koroptev.scms.model.Article;
import cz.koroptev.scms.model.WelcomePage;
import cz.koroptev.scms.services.ArticleService;

/**
 * Template for welcome page.
 * 
 * @author jan
 * 
 */
public class CmpWelcomePage extends UserAwareComponent {

    @Parameter(required = true, defaultPrefix = "prop")
    private WelcomePage welcomePage;

    @Inject
    private ArticleService articleService;

    private Article article;

    @Property
    private int count;

    /**
     * @return the welcomePage
     */
    public WelcomePage getWelcomePage() {
	return welcomePage;
    }

    /**
     * @param welcomePage
     *            the welcomePage to set
     */
    public void setWelcomePage(WelcomePage welcomePage) {
	this.welcomePage = welcomePage;
    }

    public boolean isCategorySelected() {
	return welcomePage.getCategory() != null;
    }

    public List<Article> getArticles() {
	List<Article> out = null;
	if (welcomePage.getCategory() != null
		&& welcomePage.getCategory().getId() != null) {
	    out = articleService.getArticlesByCategory(welcomePage
		    .getCategory().getId());
	} else {
	    out = articleService.getArticles(Chunk.getChunk(0, 4));
	}
	return out;
    }

    public Article getArticle() {
	return article;
    }

    public void setArticle(Article article) {
	this.article = article;
    }

}
