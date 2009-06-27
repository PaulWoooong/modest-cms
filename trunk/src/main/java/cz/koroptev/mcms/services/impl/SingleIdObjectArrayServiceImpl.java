package cz.koroptev.mcms.services.impl;

import java.util.ArrayList;
import java.util.List;

import com.jirout.common.SingleIdObject;

import cz.koroptev.mcms.services.SingleIdObjectService;

public abstract class SingleIdObjectArrayServiceImpl<T extends SingleIdObject>
	implements SingleIdObjectService<T> {

    protected final List<T> objects;

    public SingleIdObjectArrayServiceImpl() {
	objects = new ArrayList<T>();
    }

    public void create(T entity) {
	if (entity.getId() == null || entity.getId() == 0) {
	    setId(entity);
	}
	objects.add(entity);
    }

    public void createOrUpdate(T entity) {
	if (entity.getId() == null || getById(entity.getId()) == null) {
	    create(entity);
	} else {
	    update(entity);
	}
    }

    public List<T> getAll() {
	return objects;
    }

    public T getById(Integer id) {
	for (T entity : objects) {
	    if (entity.getId().equals(id)) {
		return entity;
	    }
	}
	return null;
    }

    public void remove(Integer id) {
	T entity = getById(id);
	if (entity != null) {
	    objects.remove(entity);
	}
    }

    public abstract void update(T entity);

    /**
     * Method should generate new id for given object.
     * 
     * @param entity
     */
    public abstract void setId(T entity);

}
