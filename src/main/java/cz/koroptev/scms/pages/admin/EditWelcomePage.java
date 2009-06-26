package cz.koroptev.scms.pages.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.tapestry5.OptionModel;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Retain;
import org.apache.tapestry5.internal.OptionModelImpl;
import org.apache.tapestry5.internal.SelectModelImpl;
import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.scms.form.WelcomePageForm;
import cz.koroptev.scms.model.Category;
import cz.koroptev.scms.services.CategoryService;
import cz.koroptev.scms.services.WelcomePageFormService;

/**
 * At this place can be edited common page properties, like url, title and
 * caption.
 * 
 * @author jan
 * 
 */
public class EditWelcomePage {

    @Inject
    private WelcomePageFormService welcomePageService;

    @Inject
    private CategoryService categoryService;

    private WelcomePageForm welcomePage;

    @Retain
    private Integer idUser;

    @Parameter
    private Integer idCategory;

    void onActivate(Integer idWelcomePage) {
	welcomePage = welcomePageService.getById(idWelcomePage);
	if (welcomePage.getCategory() != null) {
	    idCategory = welcomePage.getCategory().getId();
	}
	this.idUser = idWelcomePage;
    }

    Integer onPassivate() {
	return idUser;
    }

    public Object onSuccess() {
	if (idCategory == null) {
	    welcomePage.setCategory(null);
	} else {
	    welcomePage.setCategory(categoryService.getById(idCategory));
	}
	welcomePageService.createOrUpdate(welcomePage);
	return cz.koroptev.scms.pages.Index.class;
    }

    public WelcomePageForm getWelcomePage() {
	return welcomePage;
    }

    public void setWelcomePage(WelcomePageForm welcomePage) {
	this.welcomePage = welcomePage;
    }

    public SelectModel getSelectModel() {
	List<OptionModel> list = new ArrayList<OptionModel>();
	for (Category cat : categoryService.getAll()) {
	    list.add(new OptionModelImpl(cat.getName(), cat.getId()));
	}
	return new SelectModelImpl(null, list);
    }

    public Integer getIdCategory() {
	return idCategory;
    }

    public void setIdCategory(Integer idCategory) {
	this.idCategory = idCategory;
    }
}
