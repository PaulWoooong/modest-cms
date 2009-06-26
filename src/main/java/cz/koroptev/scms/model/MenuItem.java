package cz.koroptev.scms.model;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.jirout.common.SingleIdObject;

/**
 * 
 * @author jan
 * 
 */
public class MenuItem implements SingleIdObject {

    @NonVisual
    private Integer id;

    @Validate("required")
    private String name;

    /**
     * By this number are menu items ordered in menu.
     */
    private Integer priority;

    /**
     * Reference to concrete page type that will be shown when user select this
     * menu item.
     */
    private AbstractPage abstractPage;

    @Inject
    public MenuItem() {

    }

    public MenuItem(final Integer id, final String name, final Integer order,
	    final AbstractPage page) {
	this.id = id;
	this.name = name;
	this.priority = order;
	this.abstractPage = page;
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
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * @return the page
     */
    public AbstractPage getAbstractPage() {
	return abstractPage;
    }

    /**
     * @param page
     *            the page to set
     */
    public void setAbstractPage(AbstractPage page) {
	this.abstractPage = page;
    }

    /**
     * @return the order
     */
    public Integer getPriority() {
	return priority;
    }

    /**
     * @param order
     *            the order to set
     */
    public void setPriority(Integer order) {
	this.priority = order;
    }
}
