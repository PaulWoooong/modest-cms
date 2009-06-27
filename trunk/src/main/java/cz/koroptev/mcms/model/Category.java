package cz.koroptev.mcms.model;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.jirout.common.SingleIdObject;

/**
 * Each article will belongs to some category.
 * 
 * @author jan
 * 
 */
public class Category implements SingleIdObject {

    @NonVisual
    private Integer id;

    @Validate("required")
    private String name;

    @Inject
    public Category() {

    }

    public Category(final Integer id, final String name) {
	this.id = id;
	this.name = name;
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

}
