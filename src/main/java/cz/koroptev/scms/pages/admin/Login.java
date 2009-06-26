package cz.koroptev.scms.pages.admin;

import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.scms.model.User;
import cz.koroptev.scms.services.UserService;
import cz.koroptev.scms.util.UserSession;

public class Login {

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
	    System.out.println("was sucess: " + userSession.getUser());
	}

    }

    Object onSuccess() {
	return cz.koroptev.scms.pages.Index.class;
    }

}
