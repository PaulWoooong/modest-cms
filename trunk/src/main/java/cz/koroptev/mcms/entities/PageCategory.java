package cz.koroptev.mcms.entities;

/**
 * This presents content of one category.
 * 
 * @author jan
 * 
 */
public class PageCategory extends AbstractPage {

    private Category category;

    public PageCategory() {
	super();
	setPageType(3);
    }

    public PageCategory(Integer id, String url, String title, String caption) {
	super(id, url, title, caption);
	setPageType(3);
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

}
