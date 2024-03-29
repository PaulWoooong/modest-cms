package cz.koroptev.mcms.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import cz.koroptev.mcms.entities.MenuItem;

/**
 * 
 * @author jan
 * 
 */
public interface MenuItemService extends SingleIdObjectService<MenuItem> {

    @CommitAfter
    void create(MenuItem entity);

    @CommitAfter
    void update(MenuItem entity);

    @CommitAfter
    void createOrUpdate(MenuItem entity);

    @CommitAfter
    void remove(Integer id);

    @CommitAfter
    void moveRight(Integer idMenuItem);

    @CommitAfter
    void moveLeft(Integer idMenuItem);

}
