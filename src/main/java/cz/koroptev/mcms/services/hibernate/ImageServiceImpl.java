package cz.koroptev.mcms.services.hibernate;

import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.tapestry5.upload.services.UploadedFile;

import com.jirout.common.FileUtils;

import cz.koroptev.mcms.model.Image;
import cz.koroptev.mcms.services.ImageService;
import cz.koroptev.mcms.util.ScmException;
import cz.koroptev.mcms.util.ServiceProvider;

public class ImageServiceImpl extends SingleIdObjectHibernateServiceImpl<Image>
	implements ImageService {

    private final static Logger logger = Logger
	    .getLogger(ImageServiceImpl.class);

    public Image createImage(UploadedFile file) {
	Image image = new Image();
	image.setDateInsert(new Date());
	image.setExtension(FileUtils.getExtension(file.getFileName()));
	image.setName(file.getFileName());
	create(image);
	try {
	    ServiceProvider.getImageFileStore().storeFile(image.getId(),
		    file.getStream(), image.getExtension());
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
	return image;
    }

    @Override
    public Class<Image> getEntityClass() {
	return Image.class;
    }

}
