package cz.koroptev.mcms.pages.admin;

import org.apache.log4j.Logger;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.entities.User;
import cz.koroptev.mcms.services.UserService;
import cz.koroptev.mcms.util.UserSession;

public class Login {

    private final static Logger logger = Logger.getLogger(Login.class);

    @Property
    private User login;

    @SessionState
    private UserSession userSession;

    @Inject
    private UserService userService;

    @Component
    private BeanEditForm form;

    public void onValidateForm() {
	User user = userService.signIn(login.getEmail(), login.getPassword());
	if (user == null) {
	    form.recordError("Invalid email/password combination!");
	} else {
	    if (userSession == null) {
		userSession = new UserSession();
	    }
	    userSession.setUser(user);
	    userSession.setUserLoggedIn(true);
	    logger.debug("was sucess: " + userSession.getUser());
	}

    }

    Object onSuccess() {
	return cz.koroptev.mcms.pages.Index.class;
    }

}
