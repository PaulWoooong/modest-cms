package cz.koroptev.mcms.pages.admin;

import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.model.Category;
import cz.koroptev.mcms.services.CategoryService;

/**
 * Page for creating & editing user.
 * 
 * @author jan
 * 
 */
public class EditCategory {

    @Inject
    private CategoryService categoryService;

    private Category category;

    @Retain
    private Integer idCategory;

    void onActivate(Integer idUser) {
	category = categoryService.getById(idUser);
	this.idCategory = idUser;
    }

    Integer onPassivate() {
	return idCategory;
    }

    public Object onSuccess() {
	System.out.println("on success:" + idCategory + ", " + category);
	category.setId(idCategory);
	idCategory = 0;
	categoryService.createOrUpdate(category);
	return Categories.class;
    }

    /**
     * @return the category
     */
    public Category getCategory() {
	return category;
    }

    /**
     * @param category
     *            the category to set
     */
    public void setCategory(Category category) {
	this.category = category;
    }

}
