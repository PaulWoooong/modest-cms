package cz.koroptev.mcms.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.FieldValidator;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PropertyEditContext;

import cz.koroptev.mcms.entities.AbstractPage;
import cz.koroptev.mcms.services.PathService;

public class AbstractPageEditBlock {

    @Inject
    private ComponentResources resources;

    @Inject
    private PathService pathService;

    @Property
    @Environmental
    private PropertyEditContext context;

    @Component(parameters = { "value=context.propertyValue",
	    "label=prop:context.label",
	    "translate=prop:abstractPageTranslator",
	    "encoder=prop:abstractPageEncoder",
	    "validate=prop:abstractPageValidator",
	    "clientId=prop:context.propertyId", "annotationProvider=context",
	    "blankOption=never" })
    private Select abstractPage;

    public SelectModel getPages() {
	List<OptionModel> list = new ArrayList<OptionModel>();
	for (AbstractPage page : pathService.getAll()) {
	    list.add(new OptionModelImpl(page.getTitle(), page));
	}
	return new SelectModelImpl(null, list);
    }

    @SuppressWarnings("unchecked")
    public FieldValidator<AbstractPage> getAbstractPageValidator() {
	return context.getValidator(abstractPage);
    }

    @SuppressWarnings("unchecked")
    public FieldTranslator<AbstractPage> getAbstractPageTranslator() {
	return context.getTranslator(abstractPage);
    }

    /**
     * Define object that allows encode abstract page object into HTML string
     * identifier and back.
     * 
     * @return
     */
    public ValueEncoder<AbstractPage> getAbstractPageEncoder() {
	return new ValueEncoder<AbstractPage>() {

	    public String toClient(AbstractPage page) {
		System.out.println("\n" + page.getId() + "\n");
		if (page == null || page.getId() == null) {
		    System.out.println("\nIt's null\n");
		    return "";
		}
		return page.getId().toString();
	    }

	    public AbstractPage toValue(String abstractPageId) {
		System.out.println("\n" + abstractPageId);
		return pathService.getById(Integer.valueOf(abstractPageId));
	    }

	};
    }
}
