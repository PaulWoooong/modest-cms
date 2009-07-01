package cz.koroptev.mcms.services.hibernate;

import java.util.HashSet;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.urlrewriter.RewriteRuleApplicability;
import org.apache.tapestry5.urlrewriter.SimpleRequestWrapper;
import org.apache.tapestry5.urlrewriter.URLRewriteContext;

import cz.koroptev.mcms.entities.AbstractPage;
import cz.koroptev.mcms.services.LocaleService;
import cz.koroptev.mcms.services.PathService;
import cz.koroptev.mcms.services.RewriteRuleInbound;

public class RewriteRuleInboundImpl implements RewriteRuleInbound {

    private final static Logger logger = Logger
	    .getLogger(RewriteRuleInboundImpl.class);

    private static final HashSet<String> reservedRootPaths = new HashSet<String>();

    @Inject
    private PathService pathService;

    @Inject
    private LocaleService localeService;

    public RewriteRuleInboundImpl() {
	logger.debug("bleee 3");
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
	final String originalPath = request.getPath();
	final String path = localeService.removeLocale(originalPath);
	if ("/".equals(path)) {
	    logger.debug("changing (2) path: " + path);
	    request = new SimpleRequestWrapper(request, localeService
		    .applyLocale("/index/index", request));
	} else if ("/index".equals(path)) {
	    logger.debug("chnaging (4): " + path);
	    request = new SimpleRequestWrapper(request, localeService
		    .applyLocale("/index/index", request));
	} else if (!isReservedPath(path)) {
	    AbstractPage abstractPage = pathService.getByPath(path);
	    if (abstractPage == null) {
		logger.info("No such page: " + path
			+ ", triing to handle directly");
	    } else {
		switch (abstractPage.getPageType()) {
		case AbstractPage.PAGE_TYPE_WELCOME:
		    request = new SimpleRequestWrapper(request, localeService
			    .applyLocale("/index" + path, request));
		    break;
		case AbstractPage.PAGE_TYPE_ARTICLE:
		    request = new SimpleRequestWrapper(request, localeService
			    .applyLocale("/articlePage" + path, request));
		    break;
		case AbstractPage.PAGE_TYPE_CATEGORY:
		    request = new SimpleRequestWrapper(request, localeService
			    .applyLocale("/cat" + path, request));
		    break;
		default:
		    logger.error("Page didn't have valid type");
		    break;
		}
	    }
	} else {
	    logger.debug("don't changing path: " + path);
	}
	return request;
    }

    public RewriteRuleApplicability applicability() {
	return RewriteRuleApplicability.INBOUND;
    }

}
