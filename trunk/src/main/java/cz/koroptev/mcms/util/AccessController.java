package cz.koroptev.mcms.util;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.tapestry5.runtime.Component;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.ComponentClassResolver;
import org.apache.tapestry5.services.ComponentSource;
import org.apache.tapestry5.services.Dispatcher;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.Response;

public class AccessController implements Dispatcher {

    private final static Logger logger = Logger
	    .getLogger(AccessController.class);

    private final static String LOGIN_PAGE = "/login";

    private ApplicationStateManager asm;
    private ComponentClassResolver resolver;
    private ComponentSource componentSource;

    /**
     * Receive all the services needed as constructor arguments. When we bind
     * this service, T5 IoC will provide all the services !
     */
    public AccessController(ApplicationStateManager asm,
	    ComponentClassResolver resolver, ComponentSource componentSource) {
	this.asm = asm;
	this.resolver = resolver;
	this.componentSource = componentSource;
    }

    public boolean dispatch(Request request, Response response)
	    throws IOException {
	/*
	 * We need to get the Tapestry page requested by the user. So we parse
	 * the path extracted from the request
	 */
	String path = request.getPath();
	if (path.equals(""))
	    return false;

	int nextslashx = path.length();
	String pageName;

	while (true) {
	    pageName = path.substring(1, nextslashx);
	    if (!pageName.endsWith("/") && resolver.isPageName(pageName))
		break;
	    nextslashx = path.lastIndexOf('/', nextslashx - 1);
	    if (nextslashx <= 1)
		return false;
	}
	return checkAccess(pageName, request, response);
    }

    /**
     * Check the rights of the user for the page requested
     */
    public boolean checkAccess(String pageName, Request request,
	    Response response) {

	boolean canAccess = true;

	/* Is the requested page private ? */
	Component page = componentSource.getPage(pageName);
	boolean privatePage = page.getClass().getAnnotation(Private.class) != null;

	if (privatePage) {
	    canAccess = false;
	    logger.debug("checking: " + page.getClass());
	    /* Is the user already authentified ? */
	    if (asm.exists(UserSession.class)) {
		UserSession userSession = asm.get(UserSession.class);
		logger.debug("we have user session: "
			+ userSession.isUserLoggedIn());
		canAccess = userSession.isUserLoggedIn();
	    }
	}

	/*
	 * This page can't be requested by a non authentified user => we
	 * redirect him on the signon page
	 */
	if (!canAccess) {
	    try {
		response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    return true; // Make sure to leave the chain
	}

	return false;
    }
}