package cz.koroptev.mcms.services.impl;

import cz.koroptev.mcms.model.User;
import cz.koroptev.mcms.services.UserService;

/**
 * Testing version of users service.
 * 
 * @author jan
 * 
 */
public class UserArrayServiceImpl extends SingleIdObjectArrayServiceImpl<User> implements
	UserService {

    public UserArrayServiceImpl() {
	super();
	objects.add(new User(1, "a", "a"));
	objects.add(new User(2, "a@a.cz", "a"));
    }

    @Override
    public void update(User entity) {
	User user = getById(entity.getId());
	user.setEmail(entity.getEmail());
	user.setPassword(entity.getPassword());
    }

    public User signIn(String email, String password) {
	/**
	 * It's cheating
	 */
	return objects.get(0);
    }

    public void setId(User entity) {
	int id = objects.size();
	while (getById(id) != null) {
	    id++;
	}
	entity.setId(id);
    }
}
