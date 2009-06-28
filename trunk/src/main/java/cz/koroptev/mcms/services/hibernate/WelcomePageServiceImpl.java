package cz.koroptev.mcms.services.hibernate;

import cz.koroptev.mcms.entities.WelcomePage;
import cz.koroptev.mcms.services.WelcomePageService;

public class WelcomePageServiceImpl extends
	SingleIdObjectHibernateServiceImpl<WelcomePage> implements
	WelcomePageService {

    @Override
    public Class<WelcomePage> getEntityClass() {
	return WelcomePage.class;
    }

}
