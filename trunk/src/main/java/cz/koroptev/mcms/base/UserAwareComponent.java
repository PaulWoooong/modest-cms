package cz.koroptev.mcms.base;

import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BaseURLSource;

import cz.koroptev.mcms.util.UserSession;

@IncludeStylesheet("context:css/site.css")
public class UserAwareComponent {

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
	return userSessionExists && userSession.isUserLoggedIn();
    }

    /**
     * @return the userSession
     */
    public UserSession getUserSession() {
	return userSession;
    }
}
