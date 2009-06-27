package cz.koroptev.mcms.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import cz.koroptev.mcms.model.AbstractPage;

/**
 * Generic page.
 * 
 * @author jan
 * 
 */
public interface PathService {

    AbstractPage getByPath(String path);

    @CommitAfter
    void update(AbstractPage abstractPage);
    
    AbstractPage getById(Integer id);
}
