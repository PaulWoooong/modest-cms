package cz.koroptev.scms.components;

import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BaseURLSource;

@IncludeStylesheet(value = { "context:css/site.css", "context:css/lightbox.css" })
public class Layout {

    @Parameter(required = true, defaultPrefix = "prop")
    private String title;

    @Inject
    private BaseURLSource baseURLSource;

    public String getBase() {
	String base = baseURLSource.getBaseURL(false);
	if (base.indexOf("localhost") > 0) {
	    base += ":8080";
	}
	return base;
    }

    public String getTitle() {
        return title;
    }

}
