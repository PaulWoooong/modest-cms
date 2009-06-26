package cz.koroptev.scms.form;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.upload.services.UploadedFile;

import cz.koroptev.scms.model.Category;
import cz.koroptev.scms.model.Image;
import cz.koroptev.scms.model.PageCategory;
import cz.koroptev.scms.model.WelcomePage;

public class CategoryPageForm {

    @NonVisual
    private Integer id;

    private String caption;
    private String title;
    private String url;

    private String text;

    private Category category;

    private Image image;

    @Property
    private UploadedFile mainImage;

    public static CategoryPageForm getInstance(PageCategory page) {
	CategoryPageForm out = new CategoryPageForm();
	out.setId(page.getId());
	out.setCaption(page.getCaption());
	out.setTitle(page.getTitle());
	out.setUrl(page.getUrl());
	out.setText(page.getText());
	out.setCategory(page.getCategory());
	out.setImage(page.getImage());
	return out;
    }

    /**
     * Copy all properties into business object. Images are not copyed.
     * 
     * @param page
     * @return
     */
    public PageCategory copy(PageCategory page) {
	page.setId(getId());
	page.setCaption(getCaption());
	page.setTitle(getTitle());
	page.setUrl(getUrl());
	page.setText(getText());
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

    public Image getImage() {
	return image;
    }

    public void setImage(Image image) {
	this.image = image;
    }

    public UploadedFile getMainImage() {
	return mainImage;
    }

    public void setMainImage(UploadedFile mainImage) {
	this.mainImage = mainImage;
    }

}
