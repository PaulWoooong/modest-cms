package cz.koroptev.scms.pages.admin;

import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.scms.model.User;
import cz.koroptev.scms.services.UserService;

/**
 * Page for creating & editing user.
 * 
 * @author jan
 * 
 */
public class EditUser {

    @Inject
    private UserService userService;

    private User user;

    @Retain
    private Integer idUser;

    void onActivate(Integer idUser) {
	user = userService.getById(idUser);
	this.idUser = idUser;
    }

    Integer onPassivate() {
	return idUser;
    }

    public Object onSuccess() {
	System.out.println("on success:" + idUser + ", " + user);
	user.setId(idUser);
	userService.createOrUpdate(user);
	idUser = null;
	return Users.class;
    }

    /**
     * @return the user
     */
    public User getUser() {
	return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(User user) {
	this.user = user;
    }

}
