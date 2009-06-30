package cz.koroptev.mcms.components;

import org.apache.tapestry5.annotations.IncludeStylesheet;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.BaseURLSource;

@IncludeStylesheet(value = { "context:css/site.css", "context:css/admin.css",
	"context:css/lightbox.css" })
public class LayoutAdmin {

    @Inject
    private BaseURLSource baseURLSource;

    public String getBase() {
	String base = baseURLSource.getBaseURL(false);
	if (base.indexOf("localhost") > 0) {
	    base += ":8080";
	}
	return base;
    }

}
