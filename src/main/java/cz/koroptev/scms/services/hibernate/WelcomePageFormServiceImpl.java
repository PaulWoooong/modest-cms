package cz.koroptev.scms.services.hibernate;

import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.scms.form.WelcomePageForm;
import cz.koroptev.scms.model.Image;
import cz.koroptev.scms.model.WelcomePage;
import cz.koroptev.scms.services.ImageService;
import cz.koroptev.scms.services.WelcomePageFormService;
import cz.koroptev.scms.services.WelcomePageService;

/**
 * Basic hibernate implementation.
 * 
 * @author jan
 * 
 */
public class WelcomePageFormServiceImpl implements WelcomePageFormService {

    @Inject
    private WelcomePageService welcomePageService;

    @Inject
    private ImageService imageService;

    public void createOrUpdate(WelcomePageForm form) {
	if (form.getId() == null || form.getId() == 0) {
	    create(form);
	} else {
	    update(form);
	}
    }

    private void create(WelcomePageForm form) {
	WelcomePage page = new WelcomePage();
	page = form.copy(page);
	welcomePageService.create(page);
	updateImage(page, form);
    }

    private void update(WelcomePageForm form) {
	WelcomePage page = welcomePageService.getById(form.getId());
	page = form.copy(page);
	welcomePageService.update(page);
	updateImage(page, form);
    }

    private void updateImage(WelcomePage page, WelcomePageForm form) {
	if (form.getMainImage() != null) {
	    Image image = imageService.createImage(form.getMainImage());
	    page.setImage(image);
	    welcomePageService.update(page);
	}
    }

    public WelcomePageForm getById(Integer id) {
	return WelcomePageForm.getInstance(welcomePageService.getById(id));
    }

}
