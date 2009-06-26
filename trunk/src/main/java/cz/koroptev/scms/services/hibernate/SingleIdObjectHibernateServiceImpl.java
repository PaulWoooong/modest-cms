package cz.koroptev.scms.services.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.jirout.common.SingleIdObject;

import cz.koroptev.scms.services.SingleIdObjectService;
import cz.koroptev.scms.util.ScmException;

public abstract class SingleIdObjectHibernateServiceImpl<T extends SingleIdObject>
	implements SingleIdObjectService<T> {

    Logger logger = Logger.getLogger(SingleIdObjectHibernateServiceImpl.class);

    @Inject
    private Session session;

    public void create(T entity) {
	try {
	    session.save(entity);
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    public void createOrUpdate(T entity) {
	try {
	    if (entity.getId() == null || entity.getId() == 0) {
		create(entity);
	    } else {
		update(entity);
	    }
	    session.flush();
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {
	try {
	    return session.createCriteria(getEntityClass()).list();
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    @SuppressWarnings("unchecked")
    public T getById(Integer id) {
	try {
	    return (T) session.get(getEntityClass(), id);
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    public void remove(Integer id) {
	T obj = getById(id);
	if (obj != null) {
	    try {
		session.delete(obj);
	    } catch (HibernateException e) {
		logger.error(e.getMessage(), e);
		throw new ScmException(e.getMessage(), e);
	    }
	}
    }

    public void update(T entity) {
	try {
	    session.update(entity);
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    public abstract Class<T> getEntityClass();

    /**
     * @return the session
     */
    protected Session getSession() {
	return session;
    }

}
