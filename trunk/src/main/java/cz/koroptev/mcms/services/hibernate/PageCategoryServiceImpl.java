package cz.koroptev.mcms.services.hibernate;

import cz.koroptev.mcms.entities.PageCategory;
import cz.koroptev.mcms.services.PageCategoryService;

public class PageCategoryServiceImpl extends
	SingleIdObjectHibernateServiceImpl<PageCategory> implements
	PageCategoryService {

    @Override
    public Class<PageCategory> getEntityClass() {
	return PageCategory.class;
    }

}
