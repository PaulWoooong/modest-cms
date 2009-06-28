package cz.koroptev.mcms.services;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import cz.koroptev.mcms.entities.AbstractPage;

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

    List<AbstractPage> getAll();

    @CommitAfter
    void delete(Integer id);
}
