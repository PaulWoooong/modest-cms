package cz.koroptev.mcms.pages.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import cz.koroptev.mcms.entities.Article;
import cz.koroptev.mcms.entities.Category;
import cz.koroptev.mcms.pages.Index;
import cz.koroptev.mcms.services.ArticleService;
import cz.koroptev.mcms.services.CategoryService;

/**
 * Page with list of users.
 * 
 * @author jan
 * 
 */
public class AddArticle {

    @Inject
    private ArticleService articleService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    private Article article;

    private Integer idCategory;

    public Object onSuccess() {
	article.setPageType(2);
	article.setCategory(categoryService.getById(idCategory));
	articleService.create(article);
	return Index.class;
    }

    public BeanModel<Article> getModel() {
	BeanModel<Article> model = beanModelSource.createDisplayModel(
		Article.class, resources.getMessages());
	model.exclude("pageType","imageExists");
	return model;
    }
    
    public SelectModel getSelectModel() {
	List<OptionModel> list = new ArrayList<OptionModel>();
	for (Category cat : categoryService.getAll()) {
	    list.add(new OptionModelImpl(cat.getName(), cat.getId()));
	}
	return new SelectModelImpl(null, list);
    }
    
    public Article getArticle() {
	return article;
    }

    public void setArticle(Article article) {
	this.article = article;
    }

    public Integer getIdCategory() {
	return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
	this.idCategory = idCategory;
    }

}
