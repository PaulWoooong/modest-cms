package cz.koroptev.scms.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import cz.koroptev.scms.model.PageCategory;

public interface PageCategoryService extends
	SingleIdObjectService<PageCategory> {

    @CommitAfter
    void create(PageCategory entity);

    @CommitAfter
    void update(PageCategory entity);

    @CommitAfter
    void createOrUpdate(PageCategory entity);

    @CommitAfter
    void remove(Integer id);

}
