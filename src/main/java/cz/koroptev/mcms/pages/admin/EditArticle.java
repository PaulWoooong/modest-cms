package cz.koroptev.mcms.pages.admin;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.form.ArticleForm;
import cz.koroptev.mcms.services.ArticleFormService;

/**
 * Page for creating & editing user.
 * 
 * @author jan
 * 
 */
public class EditArticle {

    private final static Logger logger = Logger.getLogger(EditArticle.class);

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
	logger.debug("on success:" + idArticle + ", " + article);
	article.setId(idArticle);
	idArticle = 0;
	articleService.saveOrUpdate(article);
	return cz.koroptev.mcms.pages.Index.class;
    }

    public Integer getIdArticle() {
	return idArticle;
    }

}
