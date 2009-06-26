package cz.koroptev.scms.components;

import java.util.Collection;
import java.util.Iterator;

import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.AfterRenderTemplate;
import org.apache.tapestry5.annotations.BeforeRenderTemplate;
import org.apache.tapestry5.annotations.BeginRender;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;

/**
 * Allows to iterate throw rows and columns.
 * 
 * @author jan
 * 
 */
public class table {

    @Parameter(required = true, defaultPrefix = "prop", principal = true, autoconnect = true)
    private Collection<Object> source;

    @Parameter(required = true, defaultPrefix = "literal")
    private int cols;

    /**
     * The current value, set before the component renders its body.
     */
    @Parameter(principal = true)
    private Object value;

    private Iterator<Object> iterator;

    private int counter;

    @SetupRender
    void initializeValue() {
	counter = 0;
	iterator = source.iterator();
    }

    @BeginRender
    boolean beginRender(MarkupWriter markupWriter) {
	markupWriter.writeRaw("<table><tr>");
	return true;
    }

    @AfterRender
    boolean afterRender(MarkupWriter markupWriter) {
	markupWriter.writeRaw("</tr></table>");
	return true;
    }

    @BeforeRenderTemplate
    boolean beforeRenderTemplate(MarkupWriter markupWriter) {
	if (iterator.hasNext()) {
	    value = iterator.next();
	    counter++;
	    return true;
	} else {
	    return false;
	}
    }

    @AfterRenderTemplate
    boolean afterRenderTemplate(MarkupWriter markupWriter) {
	if (iterator.hasNext()) {
	    if (counter % cols == 0) {
		markupWriter.writeRaw("</tr><tr>");
	    }
	    return false;
	} else {
	    return true;
	}
    }

    public Object getValue() {
	return value;
    }

}
