package cz.koroptev.mcms.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.FieldTranslator;
import org.apache.tapestry5.FieldValidator;
import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Environmental;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.PropertyEditContext;

import cz.koroptev.mcms.entities.Category;
import cz.koroptev.mcms.services.CategoryService;

public class CategoryEditBlocks {

    @Inject
    private ComponentResources resources;

    @Inject
    private CategoryService categoryService;

    @Property
    @Environmental
    private PropertyEditContext context;

    @Component(parameters = { "value=context.propertyValue",
	    "label=prop:context.label", "translate=prop:categoryTranslator",
	    "validate=prop:categoryValidator",
	    "clientId=prop:context.propertyId", "annotationProvider=context" })
    private Select category;

    public SelectModel getCategories() {
	List<OptionModel> list = new ArrayList<OptionModel>();
	for (Category cat : categoryService.getAll()) {
	    list.add(new OptionModelImpl(cat.getName(), cat.getId()));
	}
	return new SelectModelImpl(null, list);
    }

    public FieldValidator<Category> getCategoryValidator() {
	return context.getValidator(category);
    }

    public FieldTranslator<Category> getCategoryTranslator() {
	return context.getTranslator(category);
    }
}
