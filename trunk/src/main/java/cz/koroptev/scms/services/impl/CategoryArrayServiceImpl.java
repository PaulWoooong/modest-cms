package cz.koroptev.scms.services.impl;

import cz.koroptev.scms.model.Category;
import cz.koroptev.scms.services.CategoryService;

/**
 * Testing version of users service.
 * 
 * @author jan
 * 
 */
public class CategoryArrayServiceImpl extends SingleIdObjectArrayServiceImpl<Category>
	implements CategoryService {

    public CategoryArrayServiceImpl() {
	super();
	objects.add(new Category(1, "Cestovani"));
	objects.add(new Category(2, "Moje prace"));
	objects.add(new Category(3, "ruzne"));
    }

    @Override
    public void update(Category entity) {
	Category user = getById(entity.getId());
	user.setName(entity.getName());
    }

    public void setId(Category entity) {
	int id = objects.size();
	while (getById(id) != null) {
	    id++;
	}
	entity.setId(id);
    }
}
