package cz.koroptev.scms.pages;

import java.util.List;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.jirout.common.Chunk;

import cz.koroptev.scms.base.UserAwareComponent;
import cz.koroptev.scms.model.AbstractPage;
import cz.koroptev.scms.model.Article;
import cz.koroptev.scms.model.WelcomePage;
import cz.koroptev.scms.services.ArticleService;
import cz.koroptev.scms.services.PathService;
import cz.koroptev.scms.services.WelcomePageService;

public class Index extends UserAwareComponent {

    @Inject
    private PathService pathService;

    @Inject
    private WelcomePageService welcomePageService;

    @Inject
    private ArticleService articleService;

    private Article article;

    @Property
    private int count;

    private WelcomePage welcomePage;

    private boolean welcomePageExists;

    /**
     * if request comes for some page here will be requested URL.
     */
    private String pageUrl;

    void onActivate(final String pageUrl) {
	this.pageUrl = pageUrl;
	System.out.println("pageUrl: " + pageUrl);
	AbstractPage page = pathService.getByPath(pageUrl);
	welcomePage = welcomePageService.getById(page.getId());
    }

    String onPasivate() {
	return pageUrl;
    }

    /**
     * @return the pageUrl
     */
    public String getPageUrl() {
	return pageUrl;
    }

    /**
     * @param pageUrl
     *            the pageUrl to set
     */
    public void setPageUrl(String pageUrl) {
	this.pageUrl = pageUrl;
    }

    /**
     * @return the welcomePage
     */
    public WelcomePage getWelcomePage() {
	return welcomePage;
    }

    /**
     * @return the welcomePageExists
     */
    public boolean isWelcomePageExists() {
	if (welcomePage == null) {
	    if (pageUrl == null || pageUrl.length() == 0) {
		AbstractPage p = pathService.getByPath("/index");
		welcomePage = welcomePageService.getById(p.getId());
		welcomePageExists = welcomePage != null;
	    }
	}
	return welcomePageExists;
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
