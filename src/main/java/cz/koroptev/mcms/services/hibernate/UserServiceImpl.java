package cz.koroptev.mcms.services.hibernate;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

import cz.koroptev.mcms.model.User;
import cz.koroptev.mcms.services.UserService;
import cz.koroptev.mcms.util.ScmException;

public class UserServiceImpl extends SingleIdObjectHibernateServiceImpl<User>
	implements UserService {

    @Override
    public Class<User> getEntityClass() {
	return User.class;
    }

    @SuppressWarnings("unchecked")
    public User signIn(String email, String password) {
	try {
	    List<User> list = getSession().createCriteria(User.class).add(
		    Restrictions.and(Restrictions.eq("email", email),
			    Restrictions.eq("password", password))).list();
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
