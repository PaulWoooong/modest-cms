package cz.koroptev.mcms.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import cz.koroptev.mcms.entities.PageCategory;

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
