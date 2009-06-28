package cz.koroptev.mcms.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import cz.koroptev.mcms.entities.Category;

/**
 * 
 * @author jan
 * 
 */
public interface CategoryService extends SingleIdObjectService<Category> {
    
    @CommitAfter
    void create(Category entity);

    @CommitAfter
    void update(Category entity);

    @CommitAfter
    void createOrUpdate(Category entity);

    @CommitAfter
    void remove(Integer id);
    
}
