package cz.koroptev.scms.services.hibernate;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.urlrewriter.RewriteRuleApplicability;
import org.apache.tapestry5.urlrewriter.SimpleRequestWrapper;
import org.apache.tapestry5.urlrewriter.URLRewriteContext;

import cz.koroptev.scms.model.AbstractPage;
import cz.koroptev.scms.services.PathService;
import cz.koroptev.scms.services.RewriteRuleInbound;

public class RewriteRuleInboundImpl implements RewriteRuleInbound {

    private final static Logger logger = Logger
	    .getLogger(RewriteRuleInboundImpl.class);

    private static final HashSet<String> reservedRootPaths = new HashSet<String>();

    @Inject
    private PathService pathService;

    public RewriteRuleInboundImpl() {
	System.out.println("bleee 3");
	reservedRootPaths.add("/favicon");
	reservedRootPaths.add("/assets");
	reservedRootPaths.add("/images");
	reservedRootPaths.add("/img");
	reservedRootPaths.add("/js");
	reservedRootPaths.add("/admin");
	reservedRootPaths.add("/article");
	reservedRootPaths.add("/css");
	reservedRootPaths.add("/index");
	reservedRootPaths.add("/cat");

	reservedRootPaths.add("/servicestatus");
	reservedRootPaths.add("/core/");
    }

    static boolean isReservedPath(String path) {
	for (String str : reservedRootPaths) {
	    if (path.startsWith(str)) {
		return true;
	    }
	}
	return false;
    }

    public Request process(Request request, URLRewriteContext context) {
	final String path = request.getPath();
	if ("/".equals(path)) {
	    System.out.println("changing (2) path: " + path);
	    request = new SimpleRequestWrapper(request, "/index/index");
	} else if ("/index".equals(path)) {
	    System.out.println("chnaging (4): " + path);
	    request = new SimpleRequestWrapper(request, "/index/index");
	} else if (!isReservedPath(path)) {
	    AbstractPage abstractPage = pathService.getByPath(path);
	    if (abstractPage == null) {
		logger.info("No such page: " + path
			+ ", triing to handle directly");
	    } else {
		switch (abstractPage.getPageType()) {
		case AbstractPage.PAGE_TYPE_WELCOME:
		    request = new SimpleRequestWrapper(request, "/index" + path);
		    break;
		case AbstractPage.PAGE_TYPE_ARTICLE:
		    request = new SimpleRequestWrapper(request, "/articlePage"
			    + path);
		    break;
		case AbstractPage.PAGE_TYPE_CATEGORY:
		    request = new SimpleRequestWrapper(request, "/cat" + path);
		    break;
		default:
		    logger.error("Page didn't have valid type");
		    break;
		}
	    }
	} else {
	    System.out.println("don't changing path: " + path);
	}
	return request;
    }

    public RewriteRuleApplicability applicability() {
	return RewriteRuleApplicability.INBOUND;
    }

}
