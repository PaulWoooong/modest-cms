package cz.koroptev.mcms.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import cz.koroptev.mcms.entities.WelcomePage;

/**
 * 
 * @author jan
 * 
 */
public interface WelcomePageService extends SingleIdObjectService<WelcomePage> {

    @CommitAfter
    void create(WelcomePage entity);

    @CommitAfter
    void update(WelcomePage entity);

    @CommitAfter
    void createOrUpdate(WelcomePage entity);

    @CommitAfter
    void remove(Integer id);
}
