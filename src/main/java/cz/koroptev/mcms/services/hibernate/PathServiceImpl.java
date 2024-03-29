package cz.koroptev.mcms.services.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cz.koroptev.mcms.entities.AbstractPage;
import cz.koroptev.mcms.services.LocaleService;
import cz.koroptev.mcms.services.PathService;
import cz.koroptev.mcms.util.ScmException;

public class PathServiceImpl implements PathService {

    private final static Logger logger = Logger
	    .getLogger(PathServiceImpl.class);
    
    @Inject
    private Session session;

    @Inject
    private LocaleService localeService;

    public AbstractPage getByPath(final String pathOriginal) {
	final String path = localeService.removeLocale(pathOriginal);
	AbstractPage page = getByPathSimple(path);
	if (page == null) {
	    if (path.length() == 0) {
		page = getByPathSimple("/index");
	    } else if (page == null) {
		page = getByPathSimple("/error404");
	    }
	}
	return page;
    }

    public void update(AbstractPage abstractPage) {
	session.update(abstractPage);
    }

    public AbstractPage getById(Integer id) {
	try {
	    return (AbstractPage) session.get(AbstractPage.class, id);
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    @SuppressWarnings("unchecked")
    private AbstractPage getByPathSimple(String path) {
	if (path.startsWith("/")) {
	    path = path.substring(1);
	}
	try {
	    List<AbstractPage> list = session
		    .createCriteria(AbstractPage.class).add(
			    Restrictions.eq("url", path)).list();
	    if (list.size() == 1) {
		return list.get(0);
	    } else {
		return null;
	    }
	} catch (HibernateException e) {
	    throw new ScmException(e.getMessage(), e);
	}
    }

    @SuppressWarnings("unchecked")
    public List<AbstractPage> getAll() {
	try {
	    return session.createCriteria(AbstractPage.class).list();
	} catch (HibernateException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
    }

    public void delete(Integer id) {
	AbstractPage obj = getById(id);
	if (obj != null) {
	    try {
		session.delete(obj);
	    } catch (HibernateException e) {
		logger.error(e.getMessage(), e);
		throw new ScmException(e.getMessage(), e);
	    }
	}
    }

}
