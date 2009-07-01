package cz.koroptev.mcms.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.ValueEncoder;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.corelib.components.Select;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.services.PersistentLocale;

import cz.koroptev.mcms.pages.admin.Index;

public class LocaleSelector {

    @Inject
    @Symbol(value = "tapestry.supported-locales")
    private String locales;

    @Inject
    private PersistentLocale persistentLocale;

    @Component(parameters = { "encoder=prop:localeEncoder",
	    "blankOption=never", "value=currentLocale" })
    private Select locale;

    public SelectModel getLocales() {
	List<OptionModel> list = new ArrayList<OptionModel>();
	String[] locs = locales.split(",");
	for (int i = 0; i < locs.length; i++) {
	    list.add(new OptionModelImpl(locs[i], new Locale(locs[i])));
	}
	return new SelectModelImpl(null, list);
    }

//    public Object onSubmitFromSwitcher() {
//	System.out.println("\n\nJsem tu\n\n");
//	return Index.class;
//    }

    public Locale getCurrentLocale() {
	return persistentLocale.get();
    }

    public void setCurrentLocale(Locale locale) {
	persistentLocale.set(locale);
    }

    // @SuppressWarnings("unchecked")
    // public FieldValidator<Locale> getCategoryValidator() {
    // return context.getValidator(locale);
    // }
    //
    // @SuppressWarnings("unchecked")
    // public FieldTranslator<Locale> getCategoryTranslator() {
    // return context.getTranslator(locale);
    // }

    /**
     * Define object that allows encode abstract page object into HTML string
     * identifier and back.
     * 
     * @return
     */
    public ValueEncoder<Locale> getLocaleEncoder() {
	return new ValueEncoder<Locale>() {

	    public String toClient(Locale locale) {
		if (locale == null) {
		    return "";
		}
		return locale.toString();
	    }

	    public Locale toValue(String loc) {
		return new Locale(loc);
	    }

	};
    }
}
