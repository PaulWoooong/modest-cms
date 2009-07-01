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

import cz.koroptev.mcms.entities.Category;
import cz.koroptev.mcms.services.CategoryService;

public class CategoryEditBlock {

    @Inject
    private ComponentResources resources;

    @Inject
    private CategoryService categoryService;

    @Property
    @Environmental
    private PropertyEditContext context;

    @Component(parameters = { "value=context.propertyValue",
	    "label=prop:context.label", "translate=prop:categoryTranslator",
	    "validate=prop:categoryValidator", "encoder=prop:categoryEncoder",
	    "clientId=prop:context.propertyId", "annotationProvider=context",
	    "blankOption=always", "blankLabel=message:noCategory-label" })
    private Select category;

    public SelectModel getCategories() {
	List<OptionModel> list = new ArrayList<OptionModel>();
	for (Category cat : categoryService.getAll()) {
	    list.add(new OptionModelImpl(cat.getName(), cat));
	}
	return new SelectModelImpl(null, list);
    }

    @SuppressWarnings("unchecked")
    public FieldValidator<Category> getCategoryValidator() {
	return context.getValidator(category);
    }

    @SuppressWarnings("unchecked")
    public FieldTranslator<Category> getCategoryTranslator() {
	return context.getTranslator(category);
    }

    /**
     * Define object that allows encode abstract page object into HTML string
     * identifier and back.
     * 
     * @return
     */
    public ValueEncoder<Category> getCategoryEncoder() {
	return new ValueEncoder<Category>() {

	    public String toClient(Category cat) {
		if (cat == null || cat.getId() == null) {
		    return "";
		}
		return cat.getId().toString();
	    }

	    public Category toValue(String abstractPageId) {
		return categoryService.getById(Integer.valueOf(abstractPageId));
	    }

	};
    }
}
