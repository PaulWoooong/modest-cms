package cz.koroptev.scms.services.hibernate;

import cz.koroptev.scms.model.WelcomePage;
import cz.koroptev.scms.services.WelcomePageService;

public class WelcomePageServiceImpl extends
	SingleIdObjectHibernateServiceImpl<WelcomePage> implements
	WelcomePageService {

    @Override
    public Class<WelcomePage> getEntityClass() {
	return WelcomePage.class;
    }

}
