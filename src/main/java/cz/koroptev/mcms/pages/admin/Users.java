package cz.koroptev.mcms.pages.admin;

import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.beaneditor.BeanModel;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BeanModelSource;

import cz.koroptev.mcms.model.User;
import cz.koroptev.mcms.services.UserService;

/**
 * Page with list of users.
 * 
 * @author jan
 * 
 */
public class Users {

    @Inject
    private UserService userService;

    /**
     * Property for iterating.
     */
    private User user;

    @Inject
    private BeanModelSource beanModelSource;

    @Inject
    private ComponentResources resources;

    public List<User> getUsers() {
	return userService.getAll();
    }

    public BeanModel getModel() {
	BeanModel model = beanModelSource.createDisplayModel(User.class,
		resources.getMessages());
	model.add("delete", null);
	return model;
    }

    void onActionFromDelete(Integer idUser) {
	userService.remove(idUser);
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
