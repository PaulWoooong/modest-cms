package cz.koroptev.scms.services.hibernate;

import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.urlrewriter.RewriteRuleApplicability;
import org.apache.tapestry5.urlrewriter.SimpleRequestWrapper;
import org.apache.tapestry5.urlrewriter.URLRewriteContext;

import cz.koroptev.scms.services.RewriteRuleOutbound;

public class RewriteRuleOutboundImpl implements RewriteRuleOutbound {

    public Request process(Request request, URLRewriteContext context) {
	final String path = request.getPath().toLowerCase();
	if (path.startsWith("/articlepage")) {
	    request = new SimpleRequestWrapper(request,
		    cutFirstPathPart(request.getPath()));
	} else if (path.startsWith("/cat")) {
	    request = new SimpleRequestWrapper(request,
		    cutFirstPathPart(request.getPath()));
	} else if (path.startsWith("/index")) {
	    request = new SimpleRequestWrapper(request,
		    cutFirstPathPart(request.getPath()));
	} else {
	    // path is not changed
	}
	return request;
    }

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
