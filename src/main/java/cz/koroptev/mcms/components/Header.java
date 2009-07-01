package cz.koroptev.mcms.components;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.SessionState;

import cz.koroptev.mcms.pages.Index;
import cz.koroptev.mcms.util.UserSession;

public class Header {

    private final static Logger logger = Logger.getLogger(Header.class);

    @SessionState
    private UserSession userSession;

    private boolean userSessionExists;

    public Object onActionFromSignOut() {
	logger.debug("sign out !!!");
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
