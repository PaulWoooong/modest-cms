package cz.koroptev.mcms.pages;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.base.UserAwareComponent;
import cz.koroptev.mcms.model.AbstractPage;
import cz.koroptev.mcms.model.Article;
import cz.koroptev.mcms.model.Image;
import cz.koroptev.mcms.services.ArticleService;
import cz.koroptev.mcms.services.PathService;

public class ArticlePage extends UserAwareComponent {

    private Article article;

    @Inject
    private PathService pathService;

    @Inject
    private ArticleService articleService;

    @Property
    private Image image;

    @Property
    private int count;
    
    /**
     * if request comes for some page here will be requested URL.
     */
    private String pageUrl;

    void onActivate(final String pageUrl) {
	this.pageUrl = pageUrl;
	if (pageUrl != null && pageUrl.length() > 0) {
	    AbstractPage page = pathService.getByPath(pageUrl);
	    article = articleService.getById(page.getId());
	}
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

    public Article getArticle() {
	return article;
    }
    
    public Format getDateFormat(){
	return new SimpleDateFormat("yyyy.MM.dd");
    }

}
