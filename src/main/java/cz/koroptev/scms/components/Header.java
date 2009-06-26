package cz.koroptev.scms.components;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BaseURLSource;

import cz.koroptev.scms.pages.Index;
import cz.koroptev.scms.util.UserSession;

public class Header {

    @Inject
    private BaseURLSource baseURLSource;

    @SessionState
    private UserSession userSession;

    private boolean userSessionExists;

    public String getBase() {
	String base = baseURLSource.getBaseURL(false);
	if (base.indexOf("localhost") > 0) {
	    base += ":8080";
	}
	return base;
    }

    public Object onActionFromSignOut() {
	System.out.println("sign out !!!");
	userSession.setUserLoggedIn(false);
	userSession = null;
	return Index.class;
    }

    public boolean getUserSigned() {
	return userSessionExists && userSession.isUserLoggedIn();
    }

    /**
     * @return the userSession
     */
    public UserSession getUserSession() {
	return userSession;
    }

}
