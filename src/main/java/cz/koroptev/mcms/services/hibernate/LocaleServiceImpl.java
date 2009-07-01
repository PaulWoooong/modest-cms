package cz.koroptev.mcms.services.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.Request;

import cz.koroptev.mcms.services.LocaleService;

public class LocaleServiceImpl implements LocaleService {

    private static final List<String> locales = new ArrayList<String>();

    public LocaleServiceImpl(
	    @Inject @Symbol(value = "tapestry.supported-locales") String localesStr) {
	String[] locs = localesStr.split(",");
	for (int i = 0; i < locs.length; i++) {
	    locales.add(locs[i].toLowerCase());
	}
    }

    public String removeLocale(String url) {
	for (String locale : locales) {
	    int pos = url.indexOf(locale);
	    if (pos == 0) {
		return url.substring(locale.length());
	    } else if (pos == 1) {
		return url.substring(locale.length() + 1);
	    }
	}
	return url;
    }

    public String applyLocale(String url, Request request) {
	String locale = getLocale(request.getPath());
	if (locale == null) {
	    return url;
	} else {
	    if (!url.startsWith("/")) {
		url = "/" + url;
	    }
	    return "/" + locale + url;
	}
    }

    public String getLocale(String url) {
	for (String locale : locales) {
	    int pos = url.indexOf(locale);
	    if (pos >= 0) {
		return locale;
	    }
	}
	return null;
    }

}
