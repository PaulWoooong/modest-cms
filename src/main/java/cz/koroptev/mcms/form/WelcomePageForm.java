package cz.koroptev.mcms.form;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.upload.services.UploadedFile;

import cz.koroptev.mcms.entities.Category;
import cz.koroptev.mcms.entities.Image;
import cz.koroptev.mcms.entities.WelcomePage;

/**
 * This object should be edited as welcome page.
 * 
 * @author jan
 * 
 */
public class WelcomePageForm {

    @NonVisual
    private Integer id;

    private String caption;
    private String title;
    private String url;

    private String text2;
    private String text;

    private Category category;

    private Image imageMain;

    @Property
    private UploadedFile mainImage;

    public static WelcomePageForm getInstance(WelcomePage page) {
	WelcomePageForm out = new WelcomePageForm();
	out.setId(page.getId());
	out.setCaption(page.getCaption());
	out.setTitle(page.getTitle());
	out.setUrl(page.getUrl());
	out.setText(page.getText());
	out.setText2(page.getText2());
	out.setCategory(page.getCategory());
	out.setImageMain(page.getImage());
	return out;
    }

    /**
     * Copy all properties into business object. Images are not copyed.
     * 
     * @param page
     * @return
     */
    public WelcomePage copy(WelcomePage page) {
	page.setId(getId());
	page.setCaption(getCaption());
	page.setTitle(getTitle());
	page.setUrl(getUrl());
	page.setText(getText());
	page.setText2(getText2());
	page.setCategory(getCategory());
	return page;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCaption() {
	return caption;
    }

    public void setCaption(String caption) {
	this.caption = caption;
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getText2() {
	return text2;
    }

    public void setText2(String text2) {
	this.text2 = text2;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public Image getImageMain() {
	return imageMain;
    }

    public void setImageMain(Image imageMain) {
	this.imageMain = imageMain;
    }

    public UploadedFile getMainImage() {
	return mainImage;
    }

    public void setMainImage(UploadedFile mainImage) {
	this.mainImage = mainImage;
    }

}
