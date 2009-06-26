package cz.koroptev.scms.model;

/**
 * Page doesn't contains anything special, just show it's text and image.
 * 
 * @author jan
 * 
 */
public class WelcomePage extends AbstractPage {

    private Category category;

    private String text2;

    /**
     * 
     */
    public WelcomePage() {
	super();
	setPageType(1);
    }

    /**
     * @param id
     * @param url
     * @param title
     * @param caption
     */
    public WelcomePage(Integer id, String url, String title, String caption) {
	super(id, url, title, caption);
	setPageType(1);
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public String getText2() {
	return text2;
    }

    public void setText2(String text2) {
	this.text2 = text2;
    }

}
