package cz.koroptev.mcms.util;

import cz.koroptev.mcms.model.User;

/**
 * Object that is stored in session if user is logged into system. This object
 * id stored to user HTTP session.
 * 
 * @author jan
 * 
 */
public class UserSession {

    private User user;

    private boolean userLoggedIn;

    public User getUser() {
	return user;
    }

    public void setUser(User user) {
	this.user = user;
    }

    /**
     * @return the userLoggedIn
     */
    public boolean isUserLoggedIn() {
	if (userLoggedIn) {
	    if (user == null) {
		return false;
	    } else {
		return userLoggedIn;
	    }
	} else {
	    return userLoggedIn;
	}
    }

    /**
     * @param userLoggedIn
     *            the userLoggedIn to set
     */
    public void setUserLoggedIn(boolean userLoggedIn) {
	this.userLoggedIn = userLoggedIn;
    }

}
