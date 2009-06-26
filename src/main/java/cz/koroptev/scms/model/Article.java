package cz.koroptev.scms.model;

import java.util.Date;
import java.util.Set;

/**
 * Represents one article or gallery.
 * 
 * @author jan
 * 
 */
public class Article extends AbstractPage {

    private String perex;

    /**
     * In this category article should belongs.
     */
    private Category category;

    /**
     * Gallery images.
     */
    private Set<Image> images;

    private Date dateCreate;

    public String getPerex() {
	return perex;
    }

    public void setPerex(String perex) {
	this.perex = perex;
    }

    public Category getCategory() {
	return category;
    }

    public void setCategory(Category category) {
	this.category = category;
    }

    public Set<Image> getImages() {
	return images;
    }

    public void setImages(Set<Image> images) {
	this.images = images;
    }

    public Date getDateCreate() {
	return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
	this.dateCreate = dateCreate;
    }

}
