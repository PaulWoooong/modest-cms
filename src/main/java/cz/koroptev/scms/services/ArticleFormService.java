package cz.koroptev.scms.services;

import cz.koroptev.scms.form.ArticleForm;

/**
 * Useful for editing article.
 * 
 * @author jan
 * 
 */
public interface ArticleFormService {

    ArticleForm getById(Integer id);

    void saveOrUpdate(ArticleForm form);

}
