package cz.koroptev.scms.services.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Order;

import cz.koroptev.scms.model.MenuItem;
import cz.koroptev.scms.services.MenuItemService;
import cz.koroptev.scms.util.ScmException;

public class MenuItemServiceImpl extends
	SingleIdObjectHibernateServiceImpl<MenuItem> implements MenuItemService {

    @Override
    public Class<MenuItem> getEntityClass() {
	return MenuItem.class;
    }

    @SuppressWarnings("unchecked")
    public List<MenuItem> getAll() {
	try {
	    return getSession().createCriteria(getEntityClass()).addOrder(
		    Order.asc("priority")).list();
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    public void create(MenuItem entity) {
	Integer max = (Integer) getSession().createQuery(
		"select max(priority) from MenuItem").list().get(0);
	max += 1;
	entity.setPriority(max);
	super.create(entity);
    }

    public void moveRight(Integer idMenuItem) {
	List<MenuItem> items = getAll();
	for (int i = 0; i < items.size(); i++) {
	    MenuItem item = items.get(i);
	    if (item.getId().equals(idMenuItem)) {
		if (i + 1 < items.size()) {
		    MenuItem tmp = items.get(i + 1);
		    int pos = tmp.getPriority();
		    tmp.setPriority(item.getPriority());
		    item.setPriority(pos);
		    update(tmp);
		    update(item);
		} else {
		    // it's last item
		}
	    }
	}
    }

    public void moveLeft(Integer idMenuItem) {
	List<MenuItem> items = getAll();
	for (int i = 0; i < items.size(); i++) {
	    MenuItem item = items.get(i);
	    if (item.getId().equals(idMenuItem)) {
		if (i > 0) {
		    MenuItem tmp = items.get(i - 1);
		    int pos = tmp.getPriority();
		    tmp.setPriority(item.getPriority());
		    item.setPriority(pos);
		    update(tmp);
		    update(item);
		} else {
		    // it's last item
		}
	    }
	}
    }

}
