package cz.koroptev.mcms.services;

import java.util.List;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;

import com.jirout.common.SingleIdObject;

/**
 * 
 * @author jan
 * 
 * @param <T>
 *            object type managed by service
 */
public interface SingleIdObjectService<T extends SingleIdObject> {

    List<T> getAll();

    @CommitAfter
    void create(T entity);

    @CommitAfter
    void update(T entity);

    void createOrUpdate(T entity);

    @CommitAfter
    void remove(Integer id);

    T getById(Integer id);

}
