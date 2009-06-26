package cz.koroptev.scms.services.hibernate;

import cz.koroptev.scms.model.PageCategory;
import cz.koroptev.scms.services.PageCategoryService;

public class PageCategoryServiceImpl extends
	SingleIdObjectHibernateServiceImpl<PageCategory> implements
	PageCategoryService {

    @Override
    public Class<PageCategory> getEntityClass() {
	return PageCategory.class;
    }

}
