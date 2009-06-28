package cz.koroptev.mcms.services;

import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.upload.services.UploadedFile;

import cz.koroptev.mcms.entities.Image;

/**
 * 
 * @author jan
 * 
 */
public interface ImageService extends SingleIdObjectService<Image> {

    @CommitAfter
    void create(Image entity);

    @CommitAfter
    void update(Image entity);

    @CommitAfter
    void createOrUpdate(Image entity);

    @CommitAfter
    void remove(Integer id);
    
    Image createImage(UploadedFile file);

}
