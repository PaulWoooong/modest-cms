package cz.koroptev.mcms.services.hibernate;

import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.entities.Article;
import cz.koroptev.mcms.entities.Image;
import cz.koroptev.mcms.form.ArticleForm;
import cz.koroptev.mcms.services.ArticleFormService;
import cz.koroptev.mcms.services.ArticleService;
import cz.koroptev.mcms.services.ImageService;

public class ArticleFormServiceImpl implements ArticleFormService {

    @Inject
    private ArticleService articleService;

    @Inject
    private ImageService imageService;

    public ArticleForm getById(Integer id) {
	return ArticleForm.getInstance(articleService.getById(id));
    }

    public void saveOrUpdate(ArticleForm form) {
	if (form.getId() == null || form.getId() == 0) {
	    create(form);
	} else {
	    save(form);
	}
    }

    private void create(ArticleForm form) {
	Article article = new Article();
	article = form.copy(article);
	articleService.create(article);
	syncImages(article, form);
    }

    private void save(ArticleForm form) {
	Article article = articleService.getById(form.getId());
	article = form.copy(article);
	articleService.update(article);
	syncImages(article, form);
    }

    private void syncImages(Article article, ArticleForm form) {
	if (form.getMainImage() != null) {
	    Image image = imageService.createImage(form.getMainImage());
	    article.setImage(image);
	}
	if (form.getImg0() != null) {
	    Image image = imageService.createImage(form.getImg0());
	    article.getImages().add(image);
	}
	if (form.getImg1() != null) {
	    Image image = imageService.createImage(form.getImg1());
	    article.getImages().add(image);
	}
	if (form.getImg2() != null) {
	    Image image = imageService.createImage(form.getImg2());
	    article.getImages().add(image);
	}
	if (form.getImg3() != null) {
	    Image image = imageService.createImage(form.getImg3());
	    article.getImages().add(image);
	}
	articleService.update(article);
    }

}
