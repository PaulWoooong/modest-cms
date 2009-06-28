package cz.koroptev.mcms.services.hibernate;

import org.apache.tapestry5.ioc.annotations.Inject;

import cz.koroptev.mcms.entities.Image;
import cz.koroptev.mcms.entities.WelcomePage;
import cz.koroptev.mcms.form.WelcomePageForm;
import cz.koroptev.mcms.services.ImageService;
import cz.koroptev.mcms.services.WelcomePageFormService;
import cz.koroptev.mcms.services.WelcomePageService;

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
