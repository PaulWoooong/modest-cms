package cz.koroptev.mcms.services.hibernate;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cz.koroptev.mcms.entities.AbstractPage;
import cz.koroptev.mcms.services.PathService;
import cz.koroptev.mcms.util.ScmException;

public class PathServiceImpl implements PathService {

    @Inject
    private Session session;

    Logger logger = Logger.getLogger(PathServiceImpl.class);

    public AbstractPage getByPath(String path) {
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

}
