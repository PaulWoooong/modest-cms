package cz.koroptev.mcms.services.hibernate;

import cz.koroptev.mcms.entities.Category;
import cz.koroptev.mcms.services.CategoryService;

public class CategoryServiceImpl extends
	SingleIdObjectHibernateServiceImpl<Category> implements CategoryService {

    @Override
    public Class<Category> getEntityClass() {
	return Category.class;
    }

}
