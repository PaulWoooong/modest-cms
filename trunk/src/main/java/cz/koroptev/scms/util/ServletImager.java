package cz.koroptev.scms.util;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.log4j.Logger;

import com.jirout.common.filestore.ImageFileStore;
import com.jirout.common.filestore.ImageFileStoreImpl;
import com.jirout.common.filestore.ImageResizerImpl;

/**
 * Extends seting up of {@link ServletImager}, for more details see there.
 * 
 * @author Jan Jirout
 * 
 */
public class ServletImager extends com.jirout.common.filestore.ServletImager {

    private static Logger logger = Logger.getLogger(ServletImager.class);

    private static final long serialVersionUID = 4801225454382530098L;

    @Override
    public void init() throws ServletException {
	super.init();
	logger.info("inicializing Servlet Imager");
	ImageFileStore fileStore = new ImageFileStoreImpl();
	fileStore.setRelativePath("WEB-INF/file-store");
	fileStore.setAbsolutePath(this.getServletContext().getRealPath("/"));
	fileStore.setImageResizer(new ImageResizerImpl());
	ServiceProvider.setImageFileStore(fileStore);
	try {
	    fileStore.init();
	} catch (IOException e) {
	    logger.error(e.getMessage(), e);
	    throw new ScmException(e.getMessage(), e);
	}
	super.setImageFileStore(fileStore);
	super.init();
    }
}
