package cz.koroptev.scms.util;

import cz.koroptev.scms.model.User;

/**
 * Object that is stored in session if user is logged into system.
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
