package cz.koroptev.mcms.components;

import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BaseURLSource;

import cz.koroptev.mcms.util.UserSession;

public class TopMenu {

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

    public boolean getUserSigned() {
	return userSessionExists;
    }

    /**
     * @return the userSession
     */
    public UserSession getUserSession() {
	return userSession;
    }
}
