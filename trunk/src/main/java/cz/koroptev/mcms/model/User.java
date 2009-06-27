package cz.koroptev.mcms.model;

import org.apache.tapestry5.beaneditor.NonVisual;
import org.apache.tapestry5.beaneditor.Validate;
import org.apache.tapestry5.ioc.annotations.Inject;

import com.jirout.common.SingleIdObject;

/**
 * System user.
 * 
 * @author jan
 * 
 */
public class User implements SingleIdObject {

    @NonVisual
    private Integer id;

    @Validate("required,regexp")
    private String email;

    @Validate("required")
    private String password;

    @Inject
    public User() {

    }

    public User(final Integer idUser, final String email, final String password) {
	this.id = idUser;
	this.email = email;
	this.password = password;
    }

    /**
     * @return the idUser
     */
    public Integer getId() {
	return id;
    }

    /**
     * @param idUser
     *            the idUser to set
     */
    public void setId(Integer idUser) {
	this.id = idUser;
    }

    /**
     * @return the name
     */
    public String getEmail() {
	return email;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setEmail(String name) {
	this.email = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
	return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public void setPassword(String password) {
	this.password = password;
    }

}
