package cz.koroptev.mcms.form;

import java.util.Date;
import java.util.Set;

import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.upload.services.UploadedFile;

import cz.koroptev.mcms.entities.Article;
import cz.koroptev.mcms.entities.Category;
import cz.koroptev.mcms.entities.Image;

/**
 * Class that simplify editing of article in one form. With images.
 * 
 * @author jan
 * 
 */
public class ArticleForm {

    @NonVisual
    private Integer id;
    
    private String caption;
    private String title;
    private String url;

    private String perex;
    private String text;

    private Category category;

    private Date dateCreate;

    private Image imageMain;
    private Set<Image> images;

    @Property
    private UploadedFile mainImage;

    private UploadedFile img0;
    private UploadedFile img1;
    private UploadedFile img2;
    private UploadedFile img3;

    public static ArticleForm getInstance(Article article) {
	ArticleForm out = new ArticleForm();
	out.setId(article.getId());
	out.setCategory(article.getCategory());
	out.setDateCreate(article.getDateCreate());
	out.setPerex(article.getPerex());
	out.setText(article.getText());
	out.setCaption(article.getCaption());
	out.setTitle(article.getTitle());
	out.setUrl(article.getUrl());
	out.setImageMain(article.getImage());
	out.setImages(article.getImages());
	return out;
    }

    public Article copy(Article article) {
	article.setId(getId());
	article.setCategory(getCategory());
	article.setDateCreate(getDateCreate());
	article.setPerex(getPerex());
	article.setText(getText());
	article.setCaption(getCaption());
	article.setTitle(getTitle());
	article.setUrl(getUrl());
	return article;
    }

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

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

    public UploadedFile getMainImage() {
	return mainImage;
    }

    public void setMainImage(UploadedFile mainImage) {
	this.mainImage = mainImage;
    }

    public UploadedFile getImg0() {
	return img0;
    }

    public void setImg0(UploadedFile img0) {
	this.img0 = img0;
    }

    public UploadedFile getImg1() {
	return img1;
    }

    public void setImg1(UploadedFile img1) {
	this.img1 = img1;
    }

    public UploadedFile getImg2() {
	return img2;
    }

    public void setImg2(UploadedFile img2) {
	this.img2 = img2;
    }

    public UploadedFile getImg3() {
	return img3;
    }

    public void setImg3(UploadedFile img3) {
	this.img3 = img3;
    }

    public String getText() {
	return text;
    }

    public void setText(String text) {
	this.text = text;
    }

    public Image getImageMain() {
	return imageMain;
    }

    public void setImageMain(Image imageMain) {
	this.imageMain = imageMain;
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
}
