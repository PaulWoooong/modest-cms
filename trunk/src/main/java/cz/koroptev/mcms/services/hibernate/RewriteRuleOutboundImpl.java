package cz.koroptev.mcms.services.hibernate;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.urlrewriter.RewriteRuleApplicability;
import org.apache.tapestry5.urlrewriter.SimpleRequestWrapper;
import org.apache.tapestry5.urlrewriter.URLRewriteContext;

import cz.koroptev.mcms.services.LocaleService;
import cz.koroptev.mcms.services.RewriteRuleOutbound;

public class RewriteRuleOutboundImpl implements RewriteRuleOutbound {

    @Inject
    private LocaleService localeService;

    public Request process(Request request, final URLRewriteContext context) {
	final String pathOriginal = request.getPath().toLowerCase();
	final String path = localeService.removeLocale(pathOriginal);

	if (path.startsWith("/articlepage")) {
	    request = new SimpleRequestWrapper(request, localeService
		    .applyLocale(cutFirstPathPart(path), request));
	} else if (path.startsWith("/cat")) {
	    request = new SimpleRequestWrapper(request, localeService
		    .applyLocale(cutFirstPathPart(path), request));
	} else if (path.startsWith("/index")) {
	    request = new SimpleRequestWrapper(request, localeService
		    .applyLocale(cutFirstPathPart(path), request));
	} else {
	    // path is not changed
	}
	return request;
    }

    /**
     * In pages we have to point to some concrete page. For that reason page
     * type should be included.
     * 
     * @param path
     * @return
     */
    private String cutFirstPathPart(String path) {
	int pos = path.substring(1).indexOf("/");
	if (pos > 0) {
	    return path.substring(pos + 1);
	} else {
	    return path;
	}
    }

    public RewriteRuleApplicability applicability() {
	return RewriteRuleApplicability.OUTBOUND;
    }

}
