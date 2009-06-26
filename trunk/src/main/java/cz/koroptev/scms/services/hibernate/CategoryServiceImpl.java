package cz.koroptev.scms.services.hibernate;

import cz.koroptev.scms.model.Category;
import cz.koroptev.scms.services.CategoryService;

public class CategoryServiceImpl extends
	SingleIdObjectHibernateServiceImpl<Category> implements CategoryService {

    @Override
    public Class<Category> getEntityClass() {
	return Category.class;
    }

}
