package cz.koroptev.mcms.pages.admin;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import cz.koroptev.mcms.entities.Category;
import cz.koroptev.mcms.services.CategoryService;

/**
 * Page with list of users.
 * 
 * @author jan
 * 
 */
public class Categories {

    @Inject
    private CategoryService categoryService;

    /**
     * Property for iterating.
     */
    private Category category;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    public List<Category> getCategories() {
	return categoryService.getAll();
    }

    public BeanModel<Category> getModel() {
	BeanModel<Category> model = beanModelSource.createDisplayModel(
		Category.class, resources.getMessages());
	model.add("delete", null);
	return model;
    }

    void onActionFromDelete(Integer idUser) {
	categoryService.remove(idUser);
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
