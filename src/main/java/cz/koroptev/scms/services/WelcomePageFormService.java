package cz.koroptev.scms.services;

import cz.koroptev.scms.form.WelcomePageForm;

/**
 * Simplify manipulating with welcome page form during editing.
 * 
 * @author jan
 * 
 */
public interface WelcomePageFormService {

    void createOrUpdate(WelcomePageForm form);

    WelcomePageForm getById(Integer id);

}
