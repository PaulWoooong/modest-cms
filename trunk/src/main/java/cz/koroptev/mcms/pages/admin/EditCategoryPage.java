package cz.koroptev.mcms.pages.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.form.CategoryPageForm;
import cz.koroptev.mcms.model.Category;
import cz.koroptev.mcms.model.Image;
import cz.koroptev.mcms.model.PageCategory;
import cz.koroptev.mcms.services.CategoryService;
import cz.koroptev.mcms.services.ImageService;
import cz.koroptev.mcms.services.PageCategoryService;

/**
 * At this place can be edited common page properties, like url, title and
 * caption.
 * 
 * @author jan
 * 
 */
public class EditCategoryPage {

    @Inject
    private PageCategoryService welcomePageService;

    @Inject
    private CategoryService categoryService;

    @Inject
    private ImageService imageService;

    private CategoryPageForm categoryPage;

    @Retain
    private Integer idCategoryPage;

    @Parameter
    private Integer idCategory;

    void onActivate(Integer idWelcomePage) {
	categoryPage = CategoryPageForm.getInstance(welcomePageService
		.getById(idWelcomePage));
	if (categoryPage.getCategory() != null) {
	    idCategory = categoryPage.getCategory().getId();
	}
	this.idCategoryPage = idWelcomePage;
    }

    Integer onPassivate() {
	return idCategoryPage;
    }

    public Object onSuccess() {
	if (idCategory == null) {
	    categoryPage.setCategory(null);
	} else {
	    categoryPage.setCategory(categoryService.getById(idCategory));
	}
	if (categoryPage.getId() == null || categoryPage.getId() == 0) {
	    create(categoryPage);
	} else {
	    update(categoryPage);
	}
	return cz.koroptev.mcms.pages.Index.class;
    }

    private void create(CategoryPageForm form) {
	PageCategory page = new PageCategory();
	page = form.copy(page);
	welcomePageService.create(page);
	updateImage(page, form);
    }

    private void update(CategoryPageForm form) {
	PageCategory page = welcomePageService.getById(form.getId());
	page = form.copy(page);
	welcomePageService.update(page);
	updateImage(page, form);
    }

    private void updateImage(PageCategory page, CategoryPageForm form) {
	if (form.getMainImage() != null) {
	    Image image = imageService.createImage(form.getMainImage());
	    page.setImage(image);
	    welcomePageService.update(page);
	}
    }

    public SelectModel getSelectModel() {
	List<OptionModel> list = new ArrayList<OptionModel>();
	for (Category cat : categoryService.getAll()) {
	    list.add(new OptionModelImpl(cat.getName(), cat.getId()));
	}
	return new SelectModelImpl(null, list);
    }

    public Integer getIdCategory() {
	return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
	this.idCategory = idCategory;
    }

    public CategoryPageForm getCategoryPage() {
	return categoryPage;
    }

    public void setCategoryPage(CategoryPageForm categoryPage) {
	this.categoryPage = categoryPage;
    }
}
