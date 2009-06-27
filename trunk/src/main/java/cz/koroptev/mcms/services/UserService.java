package cz.koroptev.mcms.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import cz.koroptev.mcms.model.User;

/**
 * 
 * @author jan
 * 
 */
public interface UserService extends SingleIdObjectService<User> {

    User signIn(String email, String password);

    @CommitAfter
    void create(User entity);

    @CommitAfter
    void update(User entity);

    @CommitAfter
    void createOrUpdate(User entity);

    @CommitAfter
    void remove(Integer id);
}
