package cz.koroptev.scms.pages.admin;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.scms.form.ArticleForm;
import cz.koroptev.scms.model.Image;
import cz.koroptev.scms.services.ArticleFormService;

/**
 * Page for creating & editing user.
 * 
 * @author jan
 * 
 */
public class EditArticle {

    @Inject
    private ArticleFormService articleService;

    @Property
    private ArticleForm article;

    @Retain
    private Integer idArticle;

    void onActivate(Integer id) {
	this.article = articleService.getById(id);
	this.idArticle = id;
    }

    Integer onPassivate() {
	return idArticle;
    }

    public Object onSuccess() {
	System.out.println("on success:" + idArticle + ", " + article);
	article.setId(idArticle);
	idArticle = 0;
	articleService.saveOrUpdate(article);
	return cz.koroptev.scms.pages.Index.class;
    }

    public Integer getIdArticle() {
	return idArticle;
    }

}
