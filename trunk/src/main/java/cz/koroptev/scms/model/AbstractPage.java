package cz.koroptev.scms.model;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.jirout.common.SingleIdObject;

/**
 * Base class for all objects that will have separate page.
 * 
 * @author jan
 * 
 */
public class AbstractPage implements SingleIdObject {

    public final static int PAGE_TYPE_WELCOME = 1;
    public final static int PAGE_TYPE_ARTICLE = 2;
    public final static int PAGE_TYPE_CATEGORY = 3;

    @NonVisual
    private Integer id;

    @Validate("required")
    private String url;

    @Validate("required")
    private String caption;

    @Validate("required")
    private String title;

    /**
     * type that helps distinguish between each types.
     */
    private Integer pageType;

    private Image image;

    private String text;

    @Inject
    public AbstractPage() {

    }

    public AbstractPage(final Integer id, final String url, final String title,
	    final String caption) {
	this.id = id;
	this.url = url;
	this.title = title;
	this.caption = caption;
    }

    public String getFullUrl() {
	if (pageType != null && pageType == 1) {
	    return url;
	} else {
	    return "cat/" + url;
	}
    }

    public boolean isCategoryPage() {
	return pageType != null && pageType == 3;
    }

    public boolean isImageExists() {
	return image != null;
    }

    /**
     * @return the id
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
	this.id = id;
    }

    /**
     * @return the url
     */
    public String getUrl() {
	if (url == null) {
	    return "index";
	} else {
	    return url;
	}
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
	this.url = url;
    }

    /**
     * @return the caption
     */
    public String getCaption() {
	return caption;
    }

    /**
     * @param caption
     *            the caption to set
     */
    public void setCaption(String caption) {
	this.caption = caption;
    }

    /**
     * @return the title
     */
    public String getTitle() {
	return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
	this.title = title;
    }

    /**
     * @return the pageType
     */
    public Integer getPageType() {
	return pageType;
    }

    /**
     * @param pageType
     *            the pageType to set
     */
    public void setPageType(Integer pageType) {
	this.pageType = pageType;
    }

    /**
     * @return the image
     */
    public Image getImage() {
	return image;
    }

    /**
     * @param image
     *            the image to set
     */
    public void setImage(Image image) {
	this.image = image;
    }

    /**
     * @return the text
     */
    public String getText() {
	return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
	this.text = text;
    }

}
