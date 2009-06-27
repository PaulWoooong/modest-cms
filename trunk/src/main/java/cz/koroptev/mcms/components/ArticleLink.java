package cz.koroptev.mcms.components;

import java.text.Format;
import java.text.SimpleDateFormat;

import org.apache.tapestry5.annotations.Parameter;

import cz.koroptev.mcms.base.UserAwareComponent;
import cz.koroptev.mcms.model.Article;

/**
 * Template for welcome page.
 * 
 * @author jan
 * 
 */
public class ArticleLink extends UserAwareComponent {

    @Parameter(required = true, defaultPrefix = "prop")
    private Article article;

    public Article getArticle() {
	return article;
    }

    public void setArticle(Article article) {
	this.article = article;
    }

    public Format getDateFormat(){
	return new SimpleDateFormat("yyyy.MM.dd");
    }
    
}
