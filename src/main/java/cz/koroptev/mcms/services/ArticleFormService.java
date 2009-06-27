package cz.koroptev.mcms.services;

import cz.koroptev.mcms.form.ArticleForm;

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
